package application;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddController {

	private String firstName;
	private String lastName;
	private String title;
	private String datePublished;
	private String availability;
	private Parent root;
	private Stage stage;
	private Scene scene;
	@FXML
	private Button addButton;
	@FXML
	private TextField addFirstName;
	@FXML
	private TextField addLastName;
	@FXML
	private TextField addTitle;
	@FXML
	private DatePicker addPublished;
	
	private ArrayList<AddController> books = new ArrayList<>();
	
	public AddController() {

	}
	
	public ArrayList<AddController> getBooks(){
		return books;
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
	
	String filePath = "C:\\Users\\Test\\Downloads\\Books.txt";
	
	public AddController(String firstName, String lastName, String title, String datePublished, String availability){
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.datePublished = datePublished;
		this.availability = availability;
	}
	
	public void addBooks(ActionEvent e) throws IOException {
		
		String bookDetails = addFirstName.getText() + "," + addLastName.getText() + "," + addTitle.getText() + "," + addPublished.getValue().toString() + "," + "AVAILABLE";
		
		AddController addBook = new AddController(addFirstName.getText(), addLastName.getText(), addTitle.getText(), addPublished.getValue().toString(), "AVAILABLE" ); 
		books.add(addBook);
		
		ViewAllBooksController control1 = new ViewAllBooksController();
		control1.setBooks(getBooks());
		MenuController control = new MenuController();
		control.setBooks(getBooks());;
		
		System.out.print(books.isEmpty());
		
		List<String> booksCheck = Files.readAllLines(Paths.get(filePath));
			
		if(!booksCheck.contains(bookDetails)) {
			try(FileWriter write = new FileWriter(filePath, true)){
				write.write(bookDetails + System.lineSeparator());
			} catch(IOException error) {
				System.out.println("Something went wrong");
			}
		} else {
			System.out.println("It is already stored");
		}

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
		root = loader.load();
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
			
	}
}