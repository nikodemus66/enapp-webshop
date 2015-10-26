package ch.hslu.edu.enapp.webshop.common;

import java.util.List;

import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;

public interface PurchaseManagerRemote {
    public void purchase(CustomerDTO customer, List<ProductDTO> basket);

}
