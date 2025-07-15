package application;

public class Book {
	
	private String firstName;
	private String lastName;
	private String title;
	private String datePublished;
	private String availability;
	private int id;
	
	public Book(String firstName, String lastName, String title, String datePublished, String availability, int id){
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.datePublished = datePublished;
		this.availability = availability;
		this.id = id;
	}

	String getFirstName() {
		return this.firstName;
	}
	
	String getLastName() {
		return this.lastName;
	}
	
	String getTitle() {
		return this.title;
	}
	
	String getDatePublished() {
		return this.datePublished;
	}
	
	String getAvailability() {
		return this.availability;
	}
	
	int getId() {
		return this.id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	public void setDatePublished(String datePublished) {
		this.datePublished = datePublished;
	}
}
