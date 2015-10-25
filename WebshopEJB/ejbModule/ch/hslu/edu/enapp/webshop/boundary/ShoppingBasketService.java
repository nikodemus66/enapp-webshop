package ch.hslu.edu.enapp.webshop.boundary;

import java.util.ArrayList;
import java.util.List;

import ch.hslu.edu.enapp.webshop.common.ShoppingBasketServiceLocal;
import ch.hslu.edu.enapp.webshop.common.ShoppingBasketServiceRemote;
import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class ShoppingBasketService
 */
@Stateless
@Local(ShoppingBasketServiceLocal.class)
public class ShoppingBasketService implements ShoppingBasketServiceRemote, ShoppingBasketServiceLocal {

    private ArrayList<ProductDTO> shoppingBasket;
    
    /**
     * Default constructor. 
     */
    public ShoppingBasketService() {
        shoppingBasket = new ArrayList();
    }

    @Override
    public void addItem(ProductDTO product) {
        shoppingBasket.add(product);
        
    }

    @Override
    public void removeItem(ProductDTO product) {
        shoppingBasket.remove(product);
        
    }

    @Override
    public List<ProductDTO> getAllItems() {
        return this.shoppingBasket;
    }

}
