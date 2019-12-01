package pojo;

// Table Account
public class Account {
	// no key
	private String userName;  // cpk
	private String passward;  // cpk
	
	public Account(String userName, String passward) {
		super();
		this.userName = userName;
		this.passward = passward;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassward() {
		return passward;
	}
	public void setPassward(String passward) {
		this.passward = passward;
	}
}
