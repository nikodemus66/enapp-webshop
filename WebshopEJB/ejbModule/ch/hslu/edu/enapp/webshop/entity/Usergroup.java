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

	private String idgroup;

	//bi-directional many-to-one association to Authgroup
	@ManyToOne
	@JoinColumn(name="IDAUTHGROUP")
	private Authgroup authgroup;

	public Usergroup() {
	}

	public UsergroupPK getId() {
		return this.id;
	}

	public void setId(UsergroupPK id) {
		this.id = id;
	}

	public String getIdgroup() {
		return this.idgroup;
	}

	public void setIdgroup(String idgroup) {
		this.idgroup = idgroup;
	}

	public Authgroup getAuthgroup() {
		return this.authgroup;
	}

	public void setAuthgroup(Authgroup authgroup) {
		this.authgroup = authgroup;
	}

}