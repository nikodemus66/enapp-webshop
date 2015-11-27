package ch.hslu.edu.enapp.webshop.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the PURCHASE database table.
 * 
 */
@Entity
@NamedQuery(name="Purchase.findAll", query="SELECT p FROM Purchase p")
public class Purchase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int purchaseid;

	private Timestamp datetime;

	@Column(name="\"STATE\"")
	private String state;
	
	private String paymentid;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="CUSTOMER")
	private Customer customerBean;

	//bi-directional many-to-one association to Purchaseitem
	@OneToMany(mappedBy="purchaseBean")
	private List<Purchaseitem> purchaseitems;

	public Purchase() {
	}

	public int getPurchaseid() {
		return this.purchaseid;
	}

	public void setPurchaseid(int purchaseid) {
		this.purchaseid = purchaseid;
	}

	public Timestamp getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Customer getCustomerBean() {
		return this.customerBean;
	}

	public void setCustomerBean(Customer customerBean) {
		this.customerBean = customerBean;
	}

	public List<Purchaseitem> getPurchaseitems() {
		return this.purchaseitems;
	}

	public void setPurchaseitems(List<Purchaseitem> purchaseitems) {
		this.purchaseitems = purchaseitems;
	}

	public Purchaseitem addPurchaseitem(Purchaseitem purchaseitem) {
		getPurchaseitems().add(purchaseitem);
		purchaseitem.setPurchaseBean(this);

		return purchaseitem;
	}

	public Purchaseitem removePurchaseitem(Purchaseitem purchaseitem) {
		getPurchaseitems().remove(purchaseitem);
		purchaseitem.setPurchaseBean(null);

		return purchaseitem;
	}

    public String getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }

}