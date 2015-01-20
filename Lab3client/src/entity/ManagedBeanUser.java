package entity;

import java.util.List;

public class ManagedBeanUser implements java.io.Serializable{

	private String firstname;
	private String lastname;
	private String emailid;
	private String password;
	private String edescription;
	private String eratings;
	private String ereviews;
	private String ename;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEdescription() {
		return edescription;
	}
	public void setEdescription(String edescription) {
		this.edescription = edescription;
	}
	public String getEratings() {
		return eratings;
	}
	public void setEratings(String eratings) {
		this.eratings = eratings;
	}
	public String getEreviews() {
		return ereviews;
	}
	public void setEreviews(String ereviews) {
		this.ereviews = ereviews;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	private String logintime;
	private String returnmsg;

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	public String getReturnmsg() {
		return returnmsg;
	}
	public void setReturnmsg(String returnmsg) {
		this.returnmsg = returnmsg;
	}
	
	
	
}
