package ch.hslu.edu.enapp.webshop.common;

import java.util.List;

import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseDTO;
import ch.hslu.edu.enapp.webshop.common.exception.PurchaseException;

public interface PurchaseManagerLocal {
    String purchase(String customerName, List<ProductDTO> basket) throws Exception;
    
    List<PurchaseDTO> getPurchases(String customerName) throws Exception;
    
    void setPurchaseStateFromJMS(String correlationId);

}
