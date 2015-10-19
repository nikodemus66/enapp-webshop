package ch.hslu.edu.enapp.webshop.common;

import java.util.List;

import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;

public interface ShoppingBasketServiceRemote {

    void addItem(final ProductDTO product);

    void removeItem(ProductDTO product);
    
    List<ProductDTO> getAllItems();
    
}
