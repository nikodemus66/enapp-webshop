package ch.hslu.edu.enapp.webshop.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PurchaseitemDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String description;

	private int product;

	private int purchase;

	private int quantity;

	private BigDecimal unitprice;

	public PurchaseitemDTO() {
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

	public int getProduct() {
		return this.product;
	}

	public void setProduct(int product) {
		this.product = product;
	}

	public int getPurchase() {
		return this.purchase;
	}

	public void setPurchase(int purchase) {
		this.purchase = purchase;
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

}