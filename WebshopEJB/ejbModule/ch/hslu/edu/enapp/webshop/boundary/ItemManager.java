package ch.hslu.edu.enapp.webshop.boundary;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ch.hslu.edu.enapp.webshop.common.ItemManagerLocal;
import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.webservices.enappdaemon.Item;
import ch.hslu.edu.enapp.webshop.webservices.enappdaemon.ItemFields;
import ch.hslu.edu.enapp.webshop.webservices.enappdaemon.ItemFilter;
import ch.hslu.edu.enapp.webshop.webservices.enappdaemon.ItemList;
import ch.hslu.edu.enapp.webshop.webservices.enappdaemon.ItemService;

/**
 * Session Bean implementation class ItemManager
 */
@Stateless
@Local(ItemManagerLocal.class)
public class ItemManager implements ItemManagerLocal {
    @PersistenceContext
    EntityManager entityManager;
    
    private ItemService itemService = new ItemService();
    
    /**
     * Default constructor. 
     */
    public ItemManager() {
        
    }

    @Override
    public List<ProductDTO> getItems() {       
        List<ProductDTO> productDtos = new ArrayList<ProductDTO>();
       
        ItemFilter itemFilter = new ItemFilter();
        
        itemFilter.setField(ItemFields.PRODUCT_GROUP_CODE);
        itemFilter.setCriteria("MP3");
        
        List<ItemFilter> itemFilterList = new ArrayList<ItemFilter>();
        itemFilterList.add(itemFilter);
        
        ItemList itemList = itemService.getItemPort().readMultiple(itemFilterList, null, 0);
        
        for(Item item: itemList.getItem()) {
            productDtos.add(ProductConverter.createDTOFromWebservice(item));
        }
        
        return productDtos;
    }   
}
