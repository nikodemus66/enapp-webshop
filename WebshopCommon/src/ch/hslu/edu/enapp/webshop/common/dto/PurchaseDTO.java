package ch.hslu.edu.enapp.webshop.common.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class PurchaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private int customer;

	private Timestamp datetime;

	private String state;

	public PurchaseDTO() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomer() {
		return this.customer;
	}

	public void setCustomer(int customer) {
		this.customer = customer;
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

}