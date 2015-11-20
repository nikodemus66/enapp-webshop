package ch.hslu.edu.enapp.webshop.jsf;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;

@Named
@SessionScoped
public class AccountMBean implements Serializable {
    @Inject
    ch.hslu.edu.enapp.webshop.common.CustomerServiceLocal customerService;
    
    private static final long serialVersionUID = 1L;
    
    private CustomerDTO customer = new CustomerDTO();;
    private boolean loggedIn = false;
    
//    public void login(String username, String password) {
//        if (this.customerService.checkPasswordForUsername(username, password)) {
//            customer = customerService.getUser(username);
//            this.loggedIn = true;
//        }
//        else {
//            //TODO error handling
//        }
//    }
    
    public void logout() {
        this.loggedIn = false;
    }
    
    public CustomerDTO getCustomer() {
        return this.customer;
    }
    
    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
    
    public boolean isLoggedIn() {
        return this.loggedIn;
    }
    
    public String getUsername() {
        return customer.getUsername();
    }
    
//    public void setUsername(String username) {
//        customer.setUsername(username);
//    }
    
//    public void setPassword(String password) {
//        customer.setPassword(password);
//    }
    
    public String getName() {
        return customer.getName();
    }
    
    public String getAddress() {
        return customer.getAddress();
    }
    
    public String getPassword() {
        return customer.getPassword();
    }
       
    public String getEmail() {
        return customer.getEmail();
    }
    
    public void setEmail(String email) {
        customer.setEmail(email);
    }
}
