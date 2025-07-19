package application;

public class Users {
	
	private String username;
	private String password;
	private int id;
	private int added;
	private int borrowed;
	
	Users(String username, String password, int id){
		this.username = username;
		this.password = password;
		this.id = id;
		this.added = 0;
		this.borrowed = 0;
	}
	
	public int getAdded() {
		return added;
	}
	
	public int getBorrowed() {
		return borrowed;
	}
	
	public void setAdded(int added) {
		this.added = added;
	}
	
	public void setBorrowed(int borrowed) {
		this.borrowed = borrowed;
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
