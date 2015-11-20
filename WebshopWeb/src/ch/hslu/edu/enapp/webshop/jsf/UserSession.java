package ch.hslu.edu.enapp.webshop.jsf;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class UserSession implements Serializable{
    @Inject
    ch.hslu.edu.enapp.webshop.common.CustomerServiceLocal customerService;
    
    @Inject
    ch.hslu.edu.enapp.webshop.jsf.AccountMBean account;
    
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String username;
//    private String newEmail;

    @PostConstruct
    public void init() {
        this.username = FacesContext.getCurrentInstance()
    .getExternalContext().getUserPrincipal().getName();
        
        account.setCustomer(customerService.getUser(this.username));
        
    }
    
    public void logout(final ActionEvent event) throws IOException {
        final ExternalContext externalContext = FacesContext
        .getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath()
        + "/ibm_security_logout?logoutExitPage=/index.xhtml");
    }
    
//    public String getUsername() {
//        return this.username;
//    }
    
    public void updateCustomer() {
        customerService.updateUser(account.getCustomer());
    }
    
    public void createCustomer() {
        customerService.addUser(account.getCustomer());
        
//        account.login(account.getCustomer().getUsername(), account.getCustomer().getPassword());
    }
}
