package application;

public class Users {
	
	private String username;
	private String password;
	private int id;
	
	Users(String username, String password, int id){
		this.username = username;
		this.password = password;
		this.id = id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public int getId() {
		return this.id;
	}

}
