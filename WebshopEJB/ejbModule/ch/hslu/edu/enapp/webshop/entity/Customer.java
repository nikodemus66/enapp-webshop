package ch.hslu.edu.enapp.webshop.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the CUSTOMER database table.
 * 
 */
@Entity
@NamedQueries(value = { @NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c"),
                        @NamedQuery(name="Customer.findByName", query="SELECT c FROM Customer c WHERE c.username= :username") })
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customerid;

	@Column(name="\"ADDRESS\"")
	private String address;

	private String email;

	@Column(name="\"NAME\"")
	private String name;

	@Column(name="\"PASSWORD\"")
	private String password;

	private String username;

	//bi-directional many-to-one association to Purchase
	@OneToMany(mappedBy="customerBean")
	private List<Purchase> purchases;

	//bi-directional many-to-many association to Authgroup
	@ManyToMany
	@JoinTable(
		name="USERGROUP"
		, joinColumns={
			@JoinColumn(name="IDUSER")
			}
		, inverseJoinColumns={
			@JoinColumn(name="IDGROUP")
			}
		)
	private List<Authgroup> authgroups;

	public Customer() {
	}

	public int getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
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

	public List<Authgroup> getAuthgroups() {
		return this.authgroups;
	}

	public void setAuthgroups(List<Authgroup> authgroups) {
		this.authgroups = authgroups;
	}

}