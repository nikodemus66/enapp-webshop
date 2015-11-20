package ch.hslu.edu.enapp.webshop.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the USERGROUP database table.
 * 
 */
@Embeddable
public class UsergroupPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//@Column(insertable=false, updatable=false)
	private int iduser;

	//@Column(insertable=false, updatable=false)
	private int idgroup;

	public UsergroupPK() {
	}
	public int getIduser() {
		return this.iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public int getIdgroup() {
		return this.idgroup;
	}
	public void setIdgroup(int idgroup) {
		this.idgroup = idgroup;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsergroupPK)) {
			return false;
		}
		UsergroupPK castOther = (UsergroupPK)other;
		return 
			(this.iduser == castOther.iduser)
			&& (this.idgroup == castOther.idgroup);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.iduser;
		hash = hash * prime + this.idgroup;
		
		return hash;
	}
}