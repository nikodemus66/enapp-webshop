package ch.hslu.edu.enapp.webshop.enappdaemon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class NcResponse {

    @XmlAttribute
    private String orderId;

    @XmlAttribute
    private String payId;
    
    @XmlAttribute
    private String ncStatus;
    
    
    public String getOrderId() {
        return orderId;
    }
    
    public void setOrderId(String oderId) {
        this.orderId = orderId;
    }
    
    public String getNcStatus() {
        return ncStatus;
    }
    
    public void setNcStatus(String ncStatus) {
        this.ncStatus = ncStatus;
    }
    
    public String getPayId() {
        return payId;
    }
    
    public void setpayId(String payId) {
        this.payId = payId;
    }
}
