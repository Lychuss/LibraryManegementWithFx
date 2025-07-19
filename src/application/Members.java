package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

public class Members {

	@FXML
	private SimpleStringProperty username;
	@FXML
	private SimpleStringProperty id;
	@FXML
	private SimpleStringProperty added;
	@FXML
	private SimpleStringProperty borrowed;
	
	public Members(String username, String id, String added, String borrowed) {
		this.username = new SimpleStringProperty(username);
		this.id = new SimpleStringProperty(id);
		this.added = new SimpleStringProperty(added);
		this.borrowed = new SimpleStringProperty(borrowed);
	}
	
	public Members() {
		
	}
	
	public String getUsername() {
		return this.username.get();
	}
	
	public String getId() {
		return this.id.get();
	}
	
	public String getAdded() {
		return this.added.get();
	}
	
	public String getBorrowed() {
		return this.borrowed.get();
	}
}
