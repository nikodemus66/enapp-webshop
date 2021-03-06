package ch.hslu.edu.enapp.webshop.jsf;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ch.hslu.edu.enapp.webshop.common.CustomerServiceLocal;
import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.common.PurchaseManagerLocal;
import ch.hslu.edu.enapp.webshop.common.dto.PurchaseDTO;

@Named
@SessionScoped
public class UserSession implements Serializable{
    @Inject
    CustomerServiceLocal customerService;
    
    @Inject
    AccountMBean account;
    
    @Inject
    PurchaseManagerLocal purchaseManager;
    
//    AccountMBean account = new AccountMBean();
    
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
        
        if (this.username != null)
            account.setCustomer(customerService.getUser(this.username));
    }
    
    public void logout(final ActionEvent event) throws IOException {
        final ExternalContext externalContext = FacesContext
        .getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath()
        + "/ibm_security_logout?logoutExitPage=/index.xhtml");
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void updateCustomer() {
        customerService.updateUser(account.getCustomer());
        
        FacesMessage fm = new FacesMessage("User " + account.getCustomer().getUsername() + " wurde aktualisiert!");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage("User " + account.getCustomer().getUsername() + " wurde aktualisiert!", fm);
    }
    
    public List<PurchaseDTO> getPurchases() throws Exception{
        return purchaseManager.getPurchases(this.username);
    }
}
