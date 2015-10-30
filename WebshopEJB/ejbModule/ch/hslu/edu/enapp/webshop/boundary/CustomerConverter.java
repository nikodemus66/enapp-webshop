package ch.hslu.edu.enapp.webshop.boundary;

import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.entity.Customer;

public class CustomerConverter {

    public static CustomerDTO createDTOFromEntity(final Customer customer) {
        final CustomerDTO customerDTOReturn = new CustomerDTO();
        
        customerDTOReturn.setId(customer.getCustomerid());
        customerDTOReturn.setName(customer.getName());
        customerDTOReturn.setAddress(customer.getAddress());
        customerDTOReturn.setEmail(customer.getEmail());
        
        customerDTOReturn.setUsername(customer.getUsername());
        customerDTOReturn.setPassword(customer.getPassword());
        
        return customerDTOReturn;
    }
    
    public static Customer createEntityFromDTO(final CustomerDTO customer) {
        final Customer customerReturn = new Customer();
        
        customerReturn.setCustomerid(customer.getId());
        customerReturn.setName(customer.getName());
        customerReturn.setAddress(customer.getAddress());
        customerReturn.setEmail(customer.getEmail());
        
        customerReturn.setUsername(customer.getUsername());
        customerReturn.setPassword(customer.getPassword());
        
        return customerReturn;
    }
}
