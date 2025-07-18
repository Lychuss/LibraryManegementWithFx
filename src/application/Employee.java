package application;

public class Employee {
	private String employeeUsername;
	private String employeePassword;
	
	Employee(String username, String password){
		this.employeePassword = password;
		this.employeeUsername = username;
	}
	
	public String getEmployeeUsername() {
		return employeeUsername;
	}
	
	public void setEmployeeUsername(String username) {
		this.employeeUsername = username;
	}
	
	public String getEmployeePassword() {
		return employeePassword;
	}
	
	public void setEmployeePassword(String password) {
		this.employeeUsername = password;
	}
}
