package ch.hslu.edu.enapp.webshop.boundary;

import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.entity.Product;

public class ProductConverter {

    public static ProductDTO createDTOFromEntity(final Product product) {
        final ProductDTO productDTOReturn = new ProductDTO();
        
        productDTOReturn.setId(product.getProductid());
        productDTOReturn.setName(product.getName());
        productDTOReturn.setDescription(product.getDescription());
        productDTOReturn.setMediapath(product.getMediapath());
        productDTOReturn.setUnitprice(product.getUnitprice());
        
        return productDTOReturn;
    }
}
