package ch.hslu.edu.enapp.webshop.enappdaemon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class LineDaemon {

    @XmlElement(name = "msDynNAVItemNo")
    private String itemNo;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "quantity")
    private long quantity;

    @XmlElement(name = "totalLinePrice")
    private long amount;

    public String getItemNo() {
        return itemNo;
    }

    public void setProductId(final String itemNo) {
        this.itemNo = itemNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(final long quantity) {
        this.quantity = quantity;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(final long amount) {
        this.amount = amount;
    }
}
