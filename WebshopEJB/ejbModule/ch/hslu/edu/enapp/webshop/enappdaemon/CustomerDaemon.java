package ch.hslu.edu.enapp.webshop.enappdaemon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerDaemon {

    @XmlElement(name = "dynNavCustNo")
    private String externalCustomerId;

    @XmlElement(name = "name")
    private String fullName;

    @XmlElement(name = "address")
    private String address;

    @XmlElement(name = "postCode")
    private String postCode;

    @XmlElement(name = "city")
    private String city;

    @XmlElement(name = "shopLoginname")
    private String username;

    public String getExternalCustomerId() {
        return externalCustomerId;
    }

    public void setExternalCustomerId(final String externalCustomerId) {
        this.externalCustomerId = externalCustomerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(final String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
}
