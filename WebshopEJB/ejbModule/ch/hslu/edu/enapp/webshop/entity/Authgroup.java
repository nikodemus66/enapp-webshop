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

	//bi-directional many-to-one association to Usergroup
	@OneToMany(mappedBy="authgroup")
	private List<Usergroup> usergroups;

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

	public List<Usergroup> getUsergroups() {
		return this.usergroups;
	}

	public void setUsergroups(List<Usergroup> usergroups) {
		this.usergroups = usergroups;
	}

	public Usergroup addUsergroup(Usergroup usergroup) {
		getUsergroups().add(usergroup);
		usergroup.setAuthgroup(this);

		return usergroup;
	}

	public Usergroup removeUsergroup(Usergroup usergroup) {
		getUsergroups().remove(usergroup);
		usergroup.setAuthgroup(null);

		return usergroup;
	}

}