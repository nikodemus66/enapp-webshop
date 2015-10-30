package ch.hslu.edu.enapp.webshop.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
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
    
//    @Inject
//    ch.hslu.edu.enapp.webshop.jsf.AccountMBean account;
    
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
    
    public void purchase() {
        
//        if (account.isLoggedIn())
//        {
//            // TODO the whole purchase process has to be here
//        }
//        else {
//            //TODO Redirect to Login page
//        }
        
        // Test
//        CustomerDTO customer = new CustomerDTO();
//        customer.setId(1);
//        customer.setName("Test");
        
        try {
            purchaseManager.purchase(userSession.getUsername(), basket);
            clearBasket();
        } catch (PurchaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
