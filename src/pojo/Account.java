package pojo;

// Table Account
public class Account {
	// no key
	private String userName;  // cpk
	private String password;  // cpk
	
	public Account() {
		
	}
	public Account(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
