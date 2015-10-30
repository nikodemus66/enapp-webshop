package ch.hslu.edu.enapp.webshop.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.hslu.edu.enapp.webshop.common.dto.*;

@Named
@SessionScoped
public class ItemMBean implements Serializable {
    
    private List<ProductDTO> productList = new ArrayList<ProductDTO>();
    
    @Inject
    ch.hslu.edu.enapp.webshop.common.ItemManagerLocal items;
    
    @PostConstruct
    public void init() {
        getItems();
    }
    
    private static final long serialVersionUID = 1L;
    public List<ProductDTO> getItems() {
        if (productList.isEmpty())
            productList = items.getItems();
        
        return productList;
    }
    
    public int getItemCount() {
        return productList.size();
    }
}
