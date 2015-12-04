package ch.hslu.edu.enapp.webshop.boundary;

import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import ch.hslu.edu.enapp.webshop.common.CustomerServiceLocal;
import ch.hslu.edu.enapp.webshop.common.PurchaseManagerLocal;
import ch.hslu.edu.enapp.webshop.common.PurchaseManagerRemote;
import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseDTO;
import ch.hslu.edu.enapp.webshop.enappdaemon.CustomerDaemon;
import ch.hslu.edu.enapp.webshop.enappdaemon.LineDaemon;
import ch.hslu.edu.enapp.webshop.enappdaemon.NcResponse;
import ch.hslu.edu.enapp.webshop.enappdaemon.PurchaseMessageDaemon;
import ch.hslu.edu.enapp.webshop.enappdaemon.SalesOrderDaemon;
import ch.hslu.edu.enapp.webshop.entity.Customer;
import ch.hslu.edu.enapp.webshop.entity.Purchase;
import ch.hslu.edu.enapp.webshop.entity.Purchaseitem;

/**
 * Session Bean implementation class PurchaseManager
 */
@Stateless
@Local(PurchaseManagerLocal.class)
@Remote(PurchaseManagerRemote.class)
public class PurchaseManager implements PurchaseManagerRemote, PurchaseManagerLocal {
    @PersistenceContext
    EntityManager entityManager;
    
    @Inject
    CustomerServiceLocal customerManager;
    
    @Inject
    EnappQueueHandler enappQueueHandler;
//    EnappQueueHandler enappQueueHandler = new EnappQueueHandler();
    
    @Inject 
    PaymentManager paymentManager;
    
    /**
     * Default constructor. 
     */
    public PurchaseManager() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public String purchase(String customerName, List<ProductDTO> basket) throws Exception {
        String correlationId = UUID.randomUUID().toString();
        
        if (!basket.isEmpty()) {                
            Customer customer = (Customer) entityManager.createNamedQuery(
                    "Customer.findByName", Customer.class).setParameter("username", customerName).getSingleResult();
            
            CustomerDaemon customerDeamon = createCustomerDeamon(customer);
            
            Purchase purchase = new Purchase();
            List<Purchaseitem> purchaseItems = new ArrayList<Purchaseitem>();
            List<LineDaemon> lineDeamons = new ArrayList<LineDaemon>();
            
            purchase.setCustomerBean(customer);
            purchase.setDatetime(new Timestamp(System.currentTimeMillis()));
            purchase.setState("created");
            purchase.setCorrelationid(correlationId);
            
            entityManager.persist(purchase);
            entityManager.flush();

            double totalAmount = 0;
            
            Iterator<ProductDTO> productIterator = basket.iterator();  
            
            while(productIterator.hasNext()) {
                ProductDTO productDto = (ProductDTO)productIterator.next();
                
                Purchaseitem purchaseItem = new Purchaseitem();
                entityManager.persist(purchaseItem);
                
//              Set purchase item fields
                purchaseItem.setProduct(productDto.getId());
                purchaseItem.setPurchaseBean(purchase);
                purchaseItem.setQuantity(1);
                purchaseItem.setUnitprice(productDto.getUnitprice());
                purchaseItem.setDescription(productDto.getDescription());
                
                totalAmount += productDto.getUnitprice().doubleValue();
                
                lineDeamons.add(createLineDeamon(productDto));
                purchaseItems.add(purchaseItem);
            }
            
            purchase.setPurchaseitems(purchaseItems);
            
            NcResponse response = paymentManager.execPayment(correlationId, totalAmount, CustomerConverter.createDTOFromEntity(customer));
            purchase.setPaymentid(response.getPayId());
            
            PurchaseMessageDaemon purchaseMessageDeamon = createPurchaseMessageDeamon(correlationId, totalAmount, purchase, customerDeamon, lineDeamons);
            enappQueueHandler.sendPurchaseMessage(correlationId, XmlToString(purchaseMessageDeamon));
            
//          Set DynNavUserId if not exists
//            if (customer.getDynNavUserId() == null) {
//                SalesOrderDaemon salesOrder = enappQueueHandler.getOrderState(correlationId);
//                
//                if (salesOrder != null) {
//                    purchase.setState(salesOrder.getOrderStatus());
//                    
//                    System.out.print("External Customer ID" + salesOrder.getExternalCustomerId());
//                    
//                    CustomerDTO customerDto = CustomerConverter.createDTOFromEntity(customer);
//                    customerDto.setDynNavUserId(salesOrder.getExternalCustomerId());
//                    
//                    customerManager.updateUser(customerDto);
//                }
//            }
     }
        
        return correlationId;
  }
    
    private CustomerDaemon createCustomerDeamon(Customer customer) {
        CustomerDaemon customerDeamon = new CustomerDaemon();
        
        customerDeamon.setUsername(customer.getUsername());
        customerDeamon.setFullName(customer.getName());
        customerDeamon.setAddress(customer.getAddress());
        customerDeamon.setCity("Horw");
        customerDeamon.setPostCode("6048");
        customerDeamon.setExternalCustomerId(customer.getDynNavUserId());
        
        return customerDeamon;
    }
    
    private PurchaseMessageDaemon createPurchaseMessageDeamon(String correlationId, double totalAmount, Purchase purchase, CustomerDaemon customerDeamon, List<LineDaemon> lineDeamons) {
        PurchaseMessageDaemon purchaseMessageDeamon = new PurchaseMessageDaemon();
        
        purchaseMessageDeamon.setCustomer(customerDeamon);
        purchaseMessageDeamon.setStudent("thkeller");
        purchaseMessageDeamon.setLines(lineDeamons);
        purchaseMessageDeamon.setPaymentId(purchase.getPaymentid());
        purchaseMessageDeamon.setDate(purchase.getDatetime());
        purchaseMessageDeamon.setPurchaseId(correlationId);
        
        purchaseMessageDeamon.setTotalAmount(totalAmount);
        
        return purchaseMessageDeamon;
    }
    
    private LineDaemon createLineDeamon(ProductDTO product) {
        LineDaemon lineDeamon = new LineDaemon();        
        
        lineDeamon.setProductId(product.getId());
        lineDeamon.setAmount(product.getUnitprice().longValue());
        lineDeamon.setDescription(product.getDescription());
        lineDeamon.setQuantity(1);
        
        return lineDeamon;
    }    
    
    private String XmlToString(final PurchaseMessageDaemon message) {
        String textMessage = null;
        try {
            final JAXBContext context = JAXBContext.newInstance(PurchaseMessageDaemon.class);
            final StringWriter writer = new StringWriter();
            final Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(message, writer);
            textMessage = writer.toString();
        } catch (final Exception e) {
            System.out.println("Error MarshalMessage: " + e.getMessage());
        }
        return textMessage;
    }

    @Override
    public List<PurchaseDTO> getPurchases(String customerName) throws Exception {
        Customer customer = (Customer) entityManager.createNamedQuery(
                "Customer.findByName", Customer.class).setParameter("username", customerName).getSingleResult();
        
        List<Purchase> purchases = entityManager.createNamedQuery(
                "Purchase.getPurchaseByCustomerid", Purchase.class).setParameter("customerid", customer.getCustomerid()).getResultList();
        
        List<PurchaseDTO> purchaseList = new ArrayList<>();
        for(Purchase purchase: purchases) {
            purchaseList.add(PurchaseConverter.createDTOFromEntity(purchase));
        }
        
        return purchaseList;
    }
    
    @Override
    public void setPurchaseStateFromJMS(String correlationId) {
        Purchase purchase = (Purchase)entityManager.createNamedQuery(
                "Purchase.getPurchaseByCorrelationid", Purchase.class).setParameter("correlationid", correlationId).getSingleResult();
        
        SalesOrderDaemon salesOrder = enappQueueHandler.getOrderState(correlationId);
        
        purchase.setState(salesOrder.getOrderStatus());
        
        entityManager.persist(purchase);
        entityManager.flush();
    }
}
