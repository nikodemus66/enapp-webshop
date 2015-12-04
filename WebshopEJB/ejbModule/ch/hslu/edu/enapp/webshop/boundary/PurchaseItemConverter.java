package ch.hslu.edu.enapp.webshop.boundary;

import ch.hslu.edu.enapp.webshop.common.dto.PurchaseitemDTO;
import ch.hslu.edu.enapp.webshop.entity.Purchaseitem;

public class PurchaseItemConverter {
    public static PurchaseitemDTO createDTOFromEntity(final Purchaseitem purchaseItem) {
        final PurchaseitemDTO purchaseItemDTOReturn = new PurchaseitemDTO();
      
        purchaseItemDTOReturn.setId(purchaseItem.getPurchaseitemid());
        purchaseItemDTOReturn.setDescription(purchaseItem.getDescription());
        purchaseItemDTOReturn.setProduct(purchaseItem.getProductBean());
        purchaseItemDTOReturn.setPurchase(purchaseItem.getPurchaseBean().getPurchaseid());
        purchaseItemDTOReturn.setQuantity(purchaseItem.getQuantity());
        purchaseItemDTOReturn.setUnitprice(purchaseItem.getUnitprice());
      
        return purchaseItemDTOReturn;
    }
}
