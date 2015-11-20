package ch.hslu.edu.enapp.webshop.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USERGROUP database table.
 * 
 */
@Entity
@NamedQuery(name="Usergroup.findAll", query="SELECT u FROM Usergroup u")
public class Usergroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsergroupPK id;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="IDUSER")
	private Customer customer;

	public Usergroup() {
	}

	public UsergroupPK getId() {
		return this.id;
	}

	public void setId(UsergroupPK id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}