package ch.hslu.edu.enapp.webshop.boundary;

import javax.ejb.Local;
import javax.ejb.Stateless;

import ch.hslu.edu.enapp.webshop.common.PurchaseManagerLocal;
import ch.hslu.edu.enapp.webshop.common.PurchaseManagerRemote;

/**
 * Session Bean implementation class PurchaseManager
 */
@Stateless
@Local(PurchaseManagerLocal.class)
//@Remote(PurchaseManagerRemote.class)
public class PurchaseManager implements PurchaseManagerRemote, PurchaseManagerLocal {

    /**
     * Default constructor. 
     */
    public PurchaseManager() {
        // TODO Auto-generated constructor stub
    }

}
