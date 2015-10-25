package ch.hslu.edu.enapp.webshop.jsf;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.hslu.edu.enapp.webshop.common.*;
import ch.hslu.edu.enapp.webshop.common.dto.*;

@Named
@SessionScoped
public class ItemMBean implements Serializable {
    
    @Inject
    ch.hslu.edu.enapp.webshop.common.ItemManagerLocal items;
    
    private static final long serialVersionUID = 1L;
    private final String testMe = "Hello World";
    
    public List<ProductDTO> getItems() {
        return items.getItems();
    }
    
}
