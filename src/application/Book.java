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
}
