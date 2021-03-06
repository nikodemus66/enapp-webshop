package ch.hslu.edu.enapp.webshop.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PURCHASEITEM database table.
 * 
 */
@Entity
@NamedQuery(name="Purchaseitem.findAll", query="SELECT p FROM Purchaseitem p")
public class Purchaseitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int purchaseitemid;

	private String description;

	private int quantity;

	private BigDecimal unitprice;

	private String product;

	//bi-directional many-to-one association to Purchase
	@ManyToOne
	@JoinColumn(name="PURCHASE")
	private Purchase purchaseBean;

	public Purchaseitem() {
	}

	public int getPurchaseitemid() {
		return this.purchaseitemid;
	}

	public void setPurchaseitemid(int purchaseitemid) {
		this.purchaseitemid = purchaseitemid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitprice() {
		return this.unitprice;
	}

	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	public String getProductBean() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Purchase getPurchaseBean() {
		return this.purchaseBean;
	}

	public void setPurchaseBean(Purchase purchaseBean) {
		this.purchaseBean = purchaseBean;
	}

}