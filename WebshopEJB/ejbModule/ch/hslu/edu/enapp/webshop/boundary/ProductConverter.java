package ch.hslu.edu.enapp.webshop.boundary;

import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.webservices.enappdaemon.Item;

public class ProductConverter {

//    public static ProductDTO createDTOFromEntity(final Product product) {
//        final ProductDTO productDTOReturn = new ProductDTO();
//        
//        productDTOReturn.setId(String.valueOf(product.getProductid()));
//        productDTOReturn.setName(product.getName());
//        productDTOReturn.setDescription(product.getDescription());
//        productDTOReturn.setMediapath(product.getMediapath());
//        productDTOReturn.setUnitprice(product.getUnitprice());
//        
//        return productDTOReturn;
//    }
    
    public static ProductDTO createDTOFromWebservice(final Item item) {
        final ProductDTO productDTOReturn = new ProductDTO();
        
        productDTOReturn.setId(item.getNo());
//        productDTOReturn.setName(item.getMediafileName());
        productDTOReturn.setDescription(item.getDescription());
//      TODO:  productDTOReturn.setMediapath(item.getM)
        productDTOReturn.setUnitprice(item.getUnitPrice());
        
        return productDTOReturn;
    }
}
