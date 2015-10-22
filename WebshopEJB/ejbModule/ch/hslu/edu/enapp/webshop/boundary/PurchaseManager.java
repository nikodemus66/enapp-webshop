package ch.hslu.edu.enapp.webshop.boundary;

import ch.hslu.edu.enapp.webshop.common.PurchaseManagerLocal;
import ch.hslu.edu.enapp.webshop.common.PurchaseManagerRemote;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

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
