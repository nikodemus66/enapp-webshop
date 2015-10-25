package ch.hslu.edu.enapp.webshop.boundary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ch.hslu.edu.enapp.webshop.common.ItemManagerLocal;
import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.entity.Customer;
import ch.hslu.edu.enapp.webshop.entity.Product;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class ItemManager
 */
@Stateless
@Local(ItemManagerLocal.class)
public class ItemManager implements ItemManagerLocal {
    @PersistenceContext
    EntityManager entityManager;
    
    private ProductConverter productConverter;
    
    /**
     * Default constructor. 
     */
    public ItemManager() {
        
    }

    @Override
    public List<ProductDTO> getItems() {
        final List<Product> allProduct = entityManager.createNamedQuery(
                "Product.findAll", Product.class).getResultList();        
        
        Iterator productIterator = allProduct.iterator();
        List<ProductDTO> productDtos = new ArrayList();
        
        while(productIterator.hasNext()) {
            Product product = (Product)productIterator.next();
            
            productDtos.add(ProductConverter.createDTOFromEntity(product));
        }      
        
        return productDtos;
    }
}
