package ch.hslu.edu.enapp.webshop.boundary;

import ch.hslu.edu.enapp.webshop.common.LoginServiceLocal;
import ch.hslu.edu.enapp.webshop.common.LoginServiceRemote;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class LoginService
 */
@Stateless
@Local(LoginServiceLocal.class)
//@Remote(LoginServiceRemote.class)
public class CustomerManager implements LoginServiceRemote, LoginServiceLocal {

    /**
     * Default constructor. 
     */
    public CustomerManager() {
        // TODO Auto-generated constructor stub
    }

}
