package ch.hslu.edu.enapp.webshop.boundary;

import java.util.ArrayList;
import java.util.List;

import ch.hslu.edu.enapp.webshop.common.dto.PurchaseDTO;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseitemDTO;
import ch.hslu.edu.enapp.webshop.entity.Purchase;
import ch.hslu.edu.enapp.webshop.entity.Purchaseitem;

public class PurchaseConverter {
    public static PurchaseDTO createDTOFromEntity(final Purchase purchase) {
        final PurchaseDTO purchaseDTOReturn = new PurchaseDTO();
      
        purchaseDTOReturn.setCustomer(purchase.getCustomerBean().getCustomerid());
        purchaseDTOReturn.setDatetime(purchase.getDatetime());
        purchaseDTOReturn.setId(purchase.getPurchaseid());
        purchaseDTOReturn.setState(purchase.getState());
        purchaseDTOReturn.setCorrelationid(purchase.getCorrelationid());
        purchaseDTOReturn.setPaymentid(purchase.getPaymentid());
        
        List<PurchaseitemDTO> purchaseItems = new ArrayList<>();
        
        for(Purchaseitem purchaseItem:purchase.getPurchaseitems()) {
            purchaseItems.add(PurchaseItemConverter.createDTOFromEntity(purchaseItem));
        }
        
        return purchaseDTOReturn;
    }
}
