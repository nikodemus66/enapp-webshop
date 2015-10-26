package ch.hslu.edu.enapp.webshop.boundary;

import java.util.Iterator;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.hslu.edu.enapp.webshop.common.PurchaseManagerLocal;
import ch.hslu.edu.enapp.webshop.common.PurchaseManagerRemote;
import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.entity.Purchase;
import ch.hslu.edu.enapp.webshop.entity.Purchaseitem;

/**
 * Session Bean implementation class PurchaseManager
 */
@Stateless
@Local(PurchaseManagerLocal.class)
//@Remote(PurchaseManagerRemote.class)
public class PurchaseManager implements PurchaseManagerRemote, PurchaseManagerLocal {
    @PersistenceContext
    EntityManager entityManager;
    
    /**
     * Default constructor. 
     */
    public PurchaseManager() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void purchase(CustomerDTO customerDto, List<ProductDTO> basket) {
        if (!basket.isEmpty()) {
            Iterator<ProductDTO> productIterator = basket.iterator();
           try {                        
                entityManager.getTransaction().begin();
                
                Purchase purchase = new Purchase();
                //TODO purchase.setCustomerBean(customer); CustomerConverter...
                
                entityManager.persist(purchase);
                
                while(productIterator.hasNext()) {
                    ProductDTO productDto = (ProductDTO)productIterator.next();
                    
                    Purchaseitem purchaseItem = new Purchaseitem();
                    entityManager.persist(purchaseItem);
                    
                    purchaseItem.setProductBean(ProductConverter.createEntityFromDTO(productDto));
                    purchaseItem.setPurchaseBean(purchase);
                    
                    purchase.addPurchaseitem(purchaseItem);
                }
                
                entityManager.getTransaction().commit();
                
        } catch(final Exception e) {
            entityManager.getTransaction().rollback();
        }
     }
  }
}
