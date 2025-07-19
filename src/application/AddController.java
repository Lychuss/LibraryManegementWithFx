package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

	private Parent root;
	private Stage stage;
	private Scene scene;
	@FXML
	private Button addButton;
	@FXML
	private Button addCancelButton;
	@FXML
	private TextField addFirstName;
	@FXML
	private TextField addLastName;
	@FXML
	private TextField addTitle;
	@FXML
	private DatePicker addPublished;

	public AddController() {

	}
	
	static String filePath = "C:\\Users\\Test\\Downloads\\Books.txt";
	
	public void addBooks(ActionEvent e) throws IOException {
		
		if(!addTitle.getText().equals(null)) {
		String key = LibraryData.getUser().keySet().iterator().next();
		
		String bookDetails = addTitle.getText()  + "," + LibraryData.getUser().get(key)+ "," +addFirstName.getText() + "," + addLastName.getText() + "," + addPublished.getValue().toString() + "," + "AVAILABLE";
	
		Book addBook = new Book(addFirstName.getText(), addLastName.getText(), addTitle.getText(), addPublished.getValue().toString(), "AVAILABLE", LibraryData.getUser().get(key) ); 
		LibraryData.getBooks().add(addBook);
		for(Users i : LibraryData.getCreatedUser()) {
			if(i.getId() == LibraryData.getUser().get(key)) {
				i.setAdded(i.getAdded() + 1);
			}
		}
		
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
	
	public static void storedBooks() throws FileNotFoundException {
		try(BufferedReader read = new BufferedReader(new FileReader(filePath))){
			String line;
			
			while((line = read.readLine()) != null) {
				String[] parts = line.split(",");
				
				if(parts.length == 6) {
					String title = parts[0];
					String id = parts[1];
					String firstName = parts[2];
					String lastName = parts[3];
					String datePublished = parts[4];
					String availability = parts[5];
					int idMember = Integer.parseInt(id);
					
					Book booksBuilt = new Book(firstName, lastName, title, datePublished, availability, idMember);
					LibraryData.getBooks().add(booksBuilt);
				}
			}
		} catch(IOException e) {
			System.out.print("something went wrong!");
		}
	}
	
	public void cancelButton(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
		root = loader.load();
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
	}
}