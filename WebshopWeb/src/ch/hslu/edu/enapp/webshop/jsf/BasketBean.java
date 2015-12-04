package ch.hslu.edu.enapp.webshop.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ch.hslu.edu.enapp.webshop.common.CustomerServiceLocal;
import ch.hslu.edu.enapp.webshop.common.PurchaseManagerLocal;
import ch.hslu.edu.enapp.webshop.common.PurchaseManagerRemote;
import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.common.exception.PurchaseException;

@Named
@SessionScoped
public class BasketBean implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

//    @EJB
    @Inject
    PurchaseManagerLocal purchaseManager;
    
    @Inject
    CustomerServiceLocal customerManager;
    
    private List<ProductDTO> basket;
    
    public BasketBean() {
        basket = new ArrayList<ProductDTO>();
    }
    
    public void doPurchase() throws Exception {           
        String correlationId = purchaseManager.purchase(getUsername(), basket);
        
        customerManager.setDynNavIdFromJMS(getUsername(), correlationId);
        
        purchaseManager.setPurchaseStateFromJMS(correlationId);
        
        clearBasket();

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
    
    private String getUsername() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    }
}
