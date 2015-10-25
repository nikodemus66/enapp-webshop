package ch.hslu.edu.enapp.webshop.common;

import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;

public interface CustomerServiceLocal {
    public boolean checkPasswordForUsername(String username, String password);
    
    /**
     * Adds a user to the database
     *
     * @param user The new User
     */
    void addUser(CustomerDTO user);
    
    void deleteUser(CustomerDTO user);

}
