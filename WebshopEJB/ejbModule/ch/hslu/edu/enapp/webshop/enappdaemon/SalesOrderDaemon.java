package ch.hslu.edu.enapp.webshop.enappdaemon;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "salesorder")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesOrderDaemon {

    @XmlElement(name = "correlationId")
    private String correlationId;

    @XmlElement(name = "dynNAVCustomerNo")
    private String externalCustomerId;

    @XmlElement(name = "dynNAVSalesOrderNo")
    private String externalOrderId;

    @XmlElement(name = "lastUpdate")
    private Date modificationDate;

    @XmlElement(name = "postFinancePayId")
    private int paymentId;

    @XmlElement(name = "purchaseDateTime")
    private Date purchaseDateTime;

    @XmlElement(name = "purchaseId")
    private int purchaseId;

    @XmlElement(name = "purchaseItemCount")
    private int itemCount;

    @XmlElement(name = "purchaseTotalCost")
    private long totalAmount;

    @XmlElement(name = "status")
    private String orderStatus;

    @XmlElement(name = "studentName")
    private String studentName;

    public String getCorrelationId() {
        return correlationId;
    }

    public String getExternalCustomerId() {
        return externalCustomerId;
    }

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public Date getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setOrderStatus(final String newStatus) {
        orderStatus = newStatus;
    }
}
