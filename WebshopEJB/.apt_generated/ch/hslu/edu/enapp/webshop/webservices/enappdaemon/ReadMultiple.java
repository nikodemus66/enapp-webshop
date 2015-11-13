//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package ch.hslu.edu.enapp.webshop.webservices.enappdaemon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReadMultiple complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReadMultiple">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="filter" type="{urn:microsoft-dynamics-schemas/page/item}Item_Filter" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="bookmarkKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="setSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReadMultiple", propOrder = {
    "filter",
    "bookmarkKey",
    "setSize"
})
public class ReadMultiple
    implements Serializable
{

    protected List<ItemFilter> filter;
    protected String bookmarkKey;
    protected int setSize;

    /**
     * Gets the value of the filter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemFilter }
     * 
     * 
     */
    public List<ItemFilter> getFilter() {
        if (filter == null) {
            filter = new ArrayList<ItemFilter>();
        }
        return this.filter;
    }

    /**
     * Gets the value of the bookmarkKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookmarkKey() {
        return bookmarkKey;
    }

    /**
     * Sets the value of the bookmarkKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookmarkKey(String value) {
        this.bookmarkKey = value;
    }

    /**
     * Gets the value of the setSize property.
     * 
     */
    public int getSetSize() {
        return setSize;
    }

    /**
     * Sets the value of the setSize property.
     * 
     */
    public void setSetSize(int value) {
        this.setSize = value;
    }

}
