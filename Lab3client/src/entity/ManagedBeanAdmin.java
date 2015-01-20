package entity;

public class ManagedBeanAdmin implements java.io.Serializable {
	private String firstName;
	private String lastName;
	private String emailid;
	private String password;
	private String loginTime;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	private String ename;
	private String edescription;
	private String eratings;
	private String ereviews;
	private String category;
//	private String mailid;
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEdescription() {
		return edescription;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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


}
