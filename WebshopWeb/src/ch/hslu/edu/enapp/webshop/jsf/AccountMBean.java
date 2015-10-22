package ch.hslu.edu.enapp.webshop.jsf;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class AccountMBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String testMe = "Hello World 2";
    
    public String getTestMe() {
        return testMe;
    }
}
