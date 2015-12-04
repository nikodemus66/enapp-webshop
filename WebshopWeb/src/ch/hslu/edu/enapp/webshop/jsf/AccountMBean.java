package ch.hslu.edu.enapp.webshop.jsf;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ch.hslu.edu.enapp.webshop.common.CustomerServiceLocal;
import ch.hslu.edu.enapp.webshop.common.PurchaseManagerLocal;
import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;

@Named
@SessionScoped
public class AccountMBean implements Serializable {
    @Inject
    CustomerServiceLocal customerService;
    
    private static final long serialVersionUID = 1L;
    
    private CustomerDTO customer = new CustomerDTO();;
    private boolean loggedIn = false;
    
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
    
    public void setUsername(String username) {
        customer.setUsername(username);
    }
    
    public void setPassword(String password) {
        customer.setPassword(password);
    }
    
    public String getName() {
        return customer.getName();
    }
    
    public void setName(String name) {
        customer.setName(name);
    }
    
    public String getAddress() {
        return customer.getAddress();
    }
    
    public void setAddress(String address) {
        customer.setAddress(address);
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
    
    public void createCustomer() throws IOException {
        customerService.addUser(customer);
        
        FacesMessage fm = new FacesMessage("User " + customer.getUsername() + " wurde erstellt!");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage("User " + customer.getUsername() + " wurde erstellt!", fm);
        
        customer = new CustomerDTO();
    }
}
