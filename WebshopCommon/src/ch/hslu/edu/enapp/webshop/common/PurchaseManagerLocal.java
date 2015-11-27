package ch.hslu.edu.enapp.webshop.common;

import java.util.List;

import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.common.exception.PurchaseException;

public interface PurchaseManagerLocal {
    public void purchase(String customerName, List<ProductDTO> basket) throws Exception;

}
