package ch.hslu.edu.enapp.webshop.enappdaemon;

import java.util.Collection;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "purchaseMessage")
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseMessageDaemon {

    @XmlElement(name = "payId")
    private int paymentId;

    @XmlElement(name = "purchaseId")
    private int purchaseId;

    @XmlElement(name = "student")
    private String student;

    @XmlElement(name = "totalPrice")
    private double totalAmount;

    @XmlElement(name = "date")
    private Date date;

    @XmlElement(name = "customer")
    private CustomerDaemon customer;

    @XmlElementWrapper(name = "lines")
    @XmlElement(name = "line")
    private Collection<LineDaemon> lines;

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(final int paymentId) {
        this.paymentId = paymentId;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(final int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(final String student) {
        this.student = student;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(final double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public CustomerDaemon getCustomer() {
        return customer;
    }

    public void setCustomer(final CustomerDaemon customer) {
        this.customer = customer;
    }

    public Collection<LineDaemon> getLines() {
        return lines;
    }

    public void setLines(final Collection<LineDaemon> lines) {
        this.lines = lines;
    }
}
