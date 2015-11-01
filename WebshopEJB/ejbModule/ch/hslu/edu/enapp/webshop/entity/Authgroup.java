package ch.hslu.edu.enapp.webshop.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the AUTHGROUP database table.
 * 
 */
@Entity
@NamedQuery(name="Authgroup.findAll", query="SELECT a FROM Authgroup a")
public class Authgroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idauthgroup;

	private String groupname;

	//bi-directional many-to-many association to Customer
	@ManyToMany(mappedBy="authgroups")
	private List<Customer> customers;

	public Authgroup() {
	}

	public int getIdauthgroup() {
		return this.idauthgroup;
	}

	public void setIdauthgroup(int idauthgroup) {
		this.idauthgroup = idauthgroup;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

}