package ch.hslu.edu.enapp.webshop.jsf;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;

@Named
@SessionScoped
public class AdminBean implements Serializable
{

    @Inject
    ch.hslu.edu.enapp.webshop.common.CustomerServiceLocal customerService;
    
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public List<CustomerDTO> getCustomers() {
        return customerService.getUsers();
    }
    
    public void changeCustomer(CustomerDTO customer) {
        customerService.updateUser(customer);
    }
}
