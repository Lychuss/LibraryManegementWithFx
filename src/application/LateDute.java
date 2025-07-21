package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

public class LateDute {
	
	@FXML
	private SimpleStringProperty id;
	@FXML
	private SimpleStringProperty name;
	@FXML
	private SimpleStringProperty due;
	@FXML
	private SimpleStringProperty book;
	@FXML
	private SimpleStringProperty lateDue;
	
	public LateDute(String id, String name, String due, String book, String lateDue) {
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.due = new SimpleStringProperty(due);
		this.lateDue = new SimpleStringProperty(lateDue);
		this.book = new SimpleStringProperty(book);
	}
	
	public LateDute() {
		
	}
	
	public String getId() {
		return id.get();
	}
	
	public String getName() {
		return name.get();
	}
	
	public String getDue() {
		return due.get();
	}
	
	public String getLateDue() {
		return lateDue.get();
	}
	
	public String getBook() {
		return book.get();
	}
}
