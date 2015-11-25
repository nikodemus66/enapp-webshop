package ch.hslu.edu.enapp.webshop.boundary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.hslu.edu.enapp.webshop.common.PurchaseManagerLocal;
import ch.hslu.edu.enapp.webshop.common.PurchaseManagerRemote;
import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.enappdeamon.CustomerDeamon;
import ch.hslu.edu.enapp.webshop.enappdeamon.LineDeamon;
import ch.hslu.edu.enapp.webshop.enappdeamon.PurchaseMessageDeamon;
import ch.hslu.edu.enapp.webshop.entity.Customer;
import ch.hslu.edu.enapp.webshop.entity.Product;
import ch.hslu.edu.enapp.webshop.entity.Purchase;
import ch.hslu.edu.enapp.webshop.entity.Purchaseitem;
import ch.hslu.edu.enapp.webshop.common.exception.PurchaseException;

import java.sql.Timestamp;

/**
 * Session Bean implementation class PurchaseManager
 */
@Stateless
@Local(PurchaseManagerLocal.class)
//@Remote(PurchaseManagerRemote.class)
public class PurchaseManager implements PurchaseManagerRemote, PurchaseManagerLocal {
    @PersistenceContext
    EntityManager entityManager;
    
    @Resource(name = "EnappQueue")
    Queue enappQueue;

    @Resource(name = "EnappQueueConnectionFactory")
    ConnectionFactory enappQueueConnectionFactory;
    
    /**
     * Default constructor. 
     */
    public PurchaseManager() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void purchase(String customerName, List<ProductDTO> basket) throws PurchaseException {
        if (!basket.isEmpty()) {
            Iterator<ProductDTO> productIterator = basket.iterator();
           try {                        
                
                Purchase purchase = new Purchase();
                List<Purchaseitem> purchaseItems = new ArrayList<Purchaseitem>();
                List<LineDeamon> lineDeamons = new ArrayList<LineDeamon>();
                
                Customer customer = (Customer) entityManager.createNamedQuery(
                        "Customer.findByName", Customer.class).setParameter("username", customerName).getSingleResult();
                
                CustomerDeamon customerDeamon = createCustomerDeamon(customer);
                
//              Set purchase fields
                purchase.setCustomerBean(customer);
                purchase.setDatetime(new Timestamp(System.currentTimeMillis()));
                purchase.setState("FINISHED");
                
//              set purchase items
                while(productIterator.hasNext()) {
                    ProductDTO productDto = (ProductDTO)productIterator.next();
                    
                    Purchaseitem purchaseItem = new Purchaseitem();
                    entityManager.persist(purchaseItem);
                    
                    Product product = (Product) entityManager.createNamedQuery(
                            "Product.findById", Product.class).setParameter("productid", productDto.getId()).getSingleResult();
                    
                    entityManager.persist(product);
                    
//                  Set purchase item fields
                    purchaseItem.setProductBean(product);
                    purchaseItem.setPurchaseBean(purchase);
                    purchaseItem.setQuantity(1);
                    purchaseItem.setUnitprice(product.getUnitprice());
                    purchaseItem.setDescription(product.getDescription());
                    
                    lineDeamons.add(createLineDeamon(purchaseItem));
                    
                    purchaseItems.add(purchaseItem);
                }
                
                purchase.setPurchaseitems(purchaseItems);
                
                PurchaseMessageDeamon purchaseMessageDeamon = createPurchaseMessageDeamon(purchase, customerDeamon, lineDeamons);
                
                //TODO: send JMS-Message
                
                entityManager.persist(purchase);
                
        } catch(final Exception e) {
            throw new PurchaseException();
        }
     }
  }
    
    public CustomerDeamon createCustomerDeamon(Customer customer) {
        CustomerDeamon customerDeamon = new CustomerDeamon();
        
        customerDeamon.setUsername(customer.getUsername());
        customerDeamon.setFullName(customer.getName());
        customerDeamon.setAddress(customer.getAddress());
        customerDeamon.setExternalCustomerId(customer.getDynNavUserId());
        
        return customerDeamon;
    }
    
    public PurchaseMessageDeamon createPurchaseMessageDeamon(Purchase purchase, CustomerDeamon customerDeamon, List<LineDeamon> lineDeamons) {
        PurchaseMessageDeamon purchaseMessageDeamon = new PurchaseMessageDeamon();
        
        purchaseMessageDeamon.setCustomer(customerDeamon);
        purchaseMessageDeamon.setLines(lineDeamons);
        
        purchaseMessageDeamon.setDate(purchase.getDatetime());
        purchaseMessageDeamon.setPurchaseId(purchase.getPurchaseid());
        //TODO
//        purchaseMessageDeamon.setTotalAmount(totalAmount);
//        purchaseMessageDeamon.setPaymentId(purchase.get);
        
        return purchaseMessageDeamon;
    }
    
    public LineDeamon createLineDeamon(Purchaseitem purchaseItem) {
        LineDeamon lineDeamon = new LineDeamon();        
        
        //TODO
//        lineDeamon.setProductId(purchaseItem.getPurchaseitemid());
        lineDeamon.setAmount(purchaseItem.getUnitprice().longValue());
        lineDeamon.setDescription(purchaseItem.getDescription());
        lineDeamon.setQuantity(purchaseItem.getQuantity());
        
        return lineDeamon;
    }    
    
}
