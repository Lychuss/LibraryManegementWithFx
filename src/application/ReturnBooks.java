package application;

import javafx.beans.property.SimpleStringProperty;

public class ReturnBooks {
	
	private SimpleStringProperty number;
	private SimpleStringProperty title;
	private SimpleStringProperty id;
	private SimpleStringProperty author;
	private SimpleStringProperty dateAdd;
	private SimpleStringProperty status;
	
	public ReturnBooks(String number, String title, String id, String author, String dateAdd, String status){
		this.author = new SimpleStringProperty(author);
		this.number = new SimpleStringProperty(number);
		this.title = new SimpleStringProperty(title);
		this.id = new SimpleStringProperty(id);
		this.status = new SimpleStringProperty(status);
		this.dateAdd = new SimpleStringProperty(dateAdd);
	}
	
	public ReturnBooks() {
		
	}

	public String getNumber() {
		return number.get();
	}
	
	public String getTitle() {
		return title.get();
	}
	
	public String getId() {
		return id.get();
	}
	
	public String getAuthor() {
		return author.get();
	}
	
	public String getDateAdd() {
		return dateAdd.get();
	}
	
	public String getStatus() {
		return status.get();
	}
	
}
