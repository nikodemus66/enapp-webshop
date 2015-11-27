package ch.hslu.edu.enapp.webshop.common.dto;

import java.io.Serializable;

public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;

    private String address;

    private String email;

    private String name;

    private String password;

    private String username;
    
    private String dynNavUserId;

    public CustomerDTO() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getDynNavUserId() {
        return this.dynNavUserId;
    }

    public void setDynNavUserId(String dynNavUserId) {
        this.dynNavUserId = dynNavUserId;
    }

}
