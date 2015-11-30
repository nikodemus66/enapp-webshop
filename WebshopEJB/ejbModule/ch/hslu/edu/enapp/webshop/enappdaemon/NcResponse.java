package ch.hslu.edu.enapp.webshop.enappdaemon;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ncresponse")
public class NcResponse implements Serializable{

    @XmlAttribute(name="orderID")
    private String orderId;

    @XmlAttribute(name="PAYID")
    private String payId;
    
    @XmlAttribute(name="NCSTATUS")
    private String ncStatus;
    
    @XmlAttribute(name="NCERROR")
    private String ncError;
    
    @XmlAttribute(name="NCERRORPLUS ")
    private String ncErrorPlus; 
    
    
    public String getOrderId() {
        return orderId;
    }
    
//    public void setOrderId(String oderId) {
//        this.orderId = orderId;
//    }
    
    public String getNcStatus() {
        return ncStatus;
    }
    
//    public void setNcStatus(String ncStatus) {
//        this.ncStatus = ncStatus;
//    }
    
    public String getPayId() {
        return payId;
    }
    
//    public void setpayId(String payId) {
//        this.payId = payId;
//    }
    
    public String getNcError() {
        return ncError;
    }
    
//    public void setncError(String ncError) {
//        this.ncError = ncError;
//    }
    
    public String getNcErrorPlus() {
        return ncErrorPlus;
    }
    
//    public void setncErrorPlus(String ncErrorPlus) {
//        this.ncErrorPlus = ncErrorPlus;
//    }
}
