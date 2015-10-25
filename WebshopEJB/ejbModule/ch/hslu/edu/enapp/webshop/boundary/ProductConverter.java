package ch.hslu.edu.enapp.webshop.boundary;

import ch.hslu.edu.enapp.webshop.common.dto.ProductDTO;
import ch.hslu.edu.enapp.webshop.entity.Product;

public class ProductConverter {

    public static ProductDTO createDTOFromEntity(final Product product) {
        final ProductDTO ProductDTOReturn = new ProductDTO();
        
        ProductDTOReturn.setId(product.getId());
        ProductDTOReturn.setName(product.getName());
        ProductDTOReturn.setDescription(product.getDescription());
        ProductDTOReturn.setMediapath(product.getMediapath());
        ProductDTOReturn.setUnitprice(product.getUnitprice());
        
        return ProductDTOReturn;
    }

}
