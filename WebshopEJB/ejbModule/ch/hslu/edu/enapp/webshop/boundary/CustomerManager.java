package ch.hslu.edu.enapp.webshop.boundary;

import java.util.List;

import ch.hslu.edu.enapp.webshop.common.CustomerServiceLocal;
import ch.hslu.edu.enapp.webshop.common.CustomerServiceRemote;
import ch.hslu.edu.enapp.webshop.common.dto.CustomerDTO;
import ch.hslu.edu.enapp.webshop.entity.Customer;
import ch.hslu.edu.enapp.webshop.entity.Usergroup;
import ch.hslu.edu.enapp.webshop.entity.UsergroupPK;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class LoginService
 */
@Stateless
@Local(CustomerServiceLocal.class)
@Remote(CustomerServiceRemote.class)
public class CustomerManager implements CustomerServiceRemote, CustomerServiceLocal {

    /**
     * Default constructor. 
     */
    public CustomerManager() {
        // TODO Auto-generated constructor stub
    }

    @PersistenceContext
    EntityManager entityManager;
    @Inject
    CustomerConverter customerConverter;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkPasswordForUsername(final String username,
            final String password) {
        final List<Customer> allCustomer = entityManager.createNamedQuery(
                "Customer.findAll", Customer.class).getResultList();
        for (final Customer u : allCustomer) {
            if (u.getUsername().equals(username)) {
                return u.getPassword().equals(password);
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addUser(final CustomerDTO user) {
        final Customer newUser = CustomerConverter.createEntityFromDTO(user);
        entityManager.persist(newUser);
        entityManager.flush();

        final UsergroupPK usergrpoupPk = new UsergroupPK();
        usergrpoupPk.setIdgroup(1);
        usergrpoupPk.setIduser(newUser.getCustomerid());
        final Usergroup permission = new Usergroup();
        permission.setId(usergrpoupPk);

        entityManager.persist(permission);
    }

    @Override
    public void deleteUser(final CustomerDTO user) {
 /*       final Student deleteUser = entityManager
                .createNamedQuery("getStudentByUsername", Customer.class)
                .setParameter("username", user.getUsername()).getSingleResult();
        entityManager.remove(deleteUser);*/
    }

    @Override
    public void updateUser(final CustomerDTO user) {
        
        final Customer customer = entityManager.createNamedQuery(
                "Customer.findByName", Customer.class).setParameter("username", user.getUsername()).getSingleResult();
        
        if (customer != null) {
            customer.setUsername(user.getUsername());
            customer.setPassword(user.getPassword());
            
            customer.setName(user.getName());
            customer.setAddress(user.getAddress());
            customer.setEmail(user.getEmail());
        }
    }

    @Override
    public CustomerDTO getUser(String username) {
        final Customer customer = entityManager.createNamedQuery(
                "Customer.findByName", Customer.class).setParameter("username", username).getSingleResult();
        
        return CustomerConverter.createDTOFromEntity(customer);
    }

}
