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

import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

import ch.hslu.edu.enapp.webshop.common.CustomerServiceLocal;
import ch.hslu.edu.enapp.webshop.common.PurchaseManagerLocal;
import ch.hslu.edu.enapp.webshop.common.PurchaseManagerRemote;
import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
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
    
    @Inject PaymentManager paymentManager;
    
    /**
     * Default constructor. 
     */
    public PurchaseManager() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void purchase(String customerName, List<ProductDTO> basket) throws Exception {
        if (!basket.isEmpty()) {
            Iterator<ProductDTO> productIterator = basket.iterator();                      
                
            Purchase purchase = new Purchase();
            List<Purchaseitem> purchaseItems = new ArrayList<Purchaseitem>();
            List<LineDaemon> lineDeamons = new ArrayList<LineDaemon>();
            
            Customer customer = (Customer) entityManager.createNamedQuery(
                    "Customer.findByName", Customer.class).setParameter("username", customerName).getSingleResult();
            
            CustomerDaemon customerDeamon = createCustomerDeamon(customer);
            
//          Set purchase fields
            purchase.setCustomerBean(customer);
            purchase.setDatetime(new Timestamp(System.currentTimeMillis()));
            purchase.setState("000");
            
            entityManager.persist(purchase);
            entityManager.flush();
            
//          set purchase items
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
                
                lineDeamons.add(createLineDeamon(productDto));
                purchaseItems.add(purchaseItem);
            }
            
            purchase.setPurchaseitems(purchaseItems);
            
            entityManager.persist(purchase);
            
            String correlationId = UUID.randomUUID().toString();
            
            NcResponse response = paymentManager.execPayment(correlationId, 12.99, CustomerConverter.createDTOFromEntity(customer));
            purchase.setPaymentid(response.getPayId());
            
            PurchaseMessageDaemon purchaseMessageDeamon = createPurchaseMessageDeamon(correlationId, purchase, customerDeamon, lineDeamons);
            enappQueueHandler.sendPurchaseMessage(correlationId, XmlToString(purchaseMessageDeamon));
            
//          Set DynNavUserId if not exists
            if (customer.getDynNavUserId() == null) {
                CustomerDTO customerDto = CustomerConverter.createDTOFromEntity(customer);
                SalesOrderDaemon salesOrder = getOrderState(correlationId);
                
                if (salesOrder != null) {
                    customerDto.setDynNavUserId(salesOrder.getExternalCustomerId());
                    customerManager.updateUser(customerDto);
                }
            }
     }
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
    
    private PurchaseMessageDaemon createPurchaseMessageDeamon(String correlationId, Purchase purchase, CustomerDaemon customerDeamon, List<LineDaemon> lineDeamons) {
        PurchaseMessageDaemon purchaseMessageDeamon = new PurchaseMessageDaemon();
        
        purchaseMessageDeamon.setCustomer(customerDeamon);
        purchaseMessageDeamon.setStudent("thkeller");
        purchaseMessageDeamon.setLines(lineDeamons);
        purchaseMessageDeamon.setPaymentId("52819897");
//        purchaseMessageDeamon.setPaymentId(purchase.getPaymentid());
        
        purchaseMessageDeamon.setDate(purchase.getDatetime());
        purchaseMessageDeamon.setPurchaseId(correlationId);
        
        //TODO
//        purchaseMessageDeamon.setTotalAmount(totalAmount);
//        purchaseMessageDeamon.setPaymentId(purchase.get);
        
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
    
    private SalesOrderDaemon getOrderState(final String correlationId) {
        final String requestURI = "http://enapp-daemons.el.eee.intern:9080/EnappDaemonWeb/rest/salesorder/corr/" + correlationId;

        final RestClient client = new RestClient();
        final Resource webResource = client.resource(requestURI);

        SalesOrderDaemon salesOrderStatus = new SalesOrderDaemon();
        salesOrderStatus.setOrderStatus("error");
        try {
            final ClientResponse response = webResource.get();
            salesOrderStatus = response.getEntity(SalesOrderDaemon.class);
        } catch (final Exception e) {
            // EnAPP Daemon not yet up-to-date... ignore :)
        }

        return salesOrderStatus;
    }
    
}
