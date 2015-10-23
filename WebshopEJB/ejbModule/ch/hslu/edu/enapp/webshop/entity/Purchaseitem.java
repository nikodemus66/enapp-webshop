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

	//bi-directional many-to-one association to Product
	   @ManyToOne
	   @JoinColumn(name="PRODUCT")
	   private Product productBean;

	   //bi-directional many-to-one association to Purchase
	   @ManyToOne
	   @JoinColumn(name="PURCHASE")
	   private Purchase purchaseBean;
	
	@Id
	@Column(name="\"ID\"")
	private int id;

	private String description;

	private int quantity;

	private BigDecimal unitprice;

	public Purchaseitem() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	 public Product getProductBean() {
	      return this.productBean;
	   }

	   public void setProductBean(Product productBean) {
	      this.productBean = productBean;
	   }

	   public Purchase getPurchaseBean() {
	      return this.purchaseBean;
	   }

	   public void setPurchaseBean(Purchase purchaseBean) {
	      this.purchaseBean = purchaseBean;
	   }
	
}