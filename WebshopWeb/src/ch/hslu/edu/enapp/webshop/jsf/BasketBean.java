package ch.hslu.edu.enapp.webshop.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.common.exception.PurchaseException;

@Named
@SessionScoped
public class BasketBean implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Inject
    ch.hslu.edu.enapp.webshop.common.PurchaseManagerLocal purchaseManager;
    
    @Inject
    ch.hslu.edu.enapp.webshop.jsf.UserSession userSession;
    
    private List<ProductDTO> basket;
    
    public BasketBean() {
        basket = new ArrayList<ProductDTO>();
    }
    
    public List<ProductDTO> getAllItems() {
        return basket;
    }
    
    public void addItem(ProductDTO product) {
        basket.add(product);
    }
    
    public void removeItem(ProductDTO product) {
        basket.remove(product);
    }
    
    public void clearBasket() {
        basket.clear();
    }
    
    public int getProductCount() {
        return basket.size();
    }
    
    public void purchase() {
        
//        TODO userSession.getUsername()
        
        try {
            purchaseManager.purchase("TestCustomer", basket);
            clearBasket();
        } catch (PurchaseException e) {
            e.printStackTrace();
        }
    }
}
