package ch.hslu.edu.enapp.webshop.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the CUSTOMER database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"ID\"")
	private int id;

	@Column(name="\"ADDRESS\"")
	private String address;

	private String email;

	@Column(name="\"NAME\"")
	private String name;

	@Column(name="\"PASSWORD\"")
	private String password;

	private String username;

	public Customer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	   //bi-directional many-to-one association to Purchase
	   @OneToMany(mappedBy="customerBean")
	   private List<Purchase> purchases;
	//...
	   public List<Purchase> getPurchases() {
	      return this.purchases;
	   }

	   public void setPurchases(List<Purchase> purchases) {
	      this.purchases = purchases;
	   }

	   public Purchase addPurchas(Purchase purchas) {
	      getPurchases().add(purchas);
	      purchas.setCustomerBean(this);

	      return purchas;
	   }

	   public Purchase removePurchas(Purchase purchas) {
	      getPurchases().remove(purchas);
	      purchas.setCustomerBean(null);

	      return purchas;
	   }
	
}