package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BorrowBookController {
	
	@FXML
	private Button borrowButton;
	@FXML
	private Button borrowCancelButton;
	@FXML
	private TextField borrowFirstName;
	@FXML
	private TextField borrowLastName;
	@FXML
	private TextField borrowTitle;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void borrowBook(ActionEvent e) throws IOException {
		boolean borrow = false;
		boolean borrow1 = false;
		
		String key = LibraryData.getUser().keySet().iterator().next();
		
		List<String> updatedLines = new ArrayList<>();
		
		LocalDate currentDate = LocalDate.now();
		
		for(Book i : LibraryData.getBooks()) {
			if(i.getFirstName().equals(borrowFirstName.getText()) && 
				i.getLastName().equals(borrowLastName.getText()) && 
				i.getTitle().equals(borrowTitle.getText()) &&
				!i.getAvailability().equals("BORROWED")) {
				i.setDatePublished(currentDate.toString());
				i.setAvailability("BORROWED");
				borrow = true;
				borrow1 = true;
				Book books = new Book(i.getFirstName(), i.getLastName(), i.getTitle(), i.getDatePublished(), i.getAvailability(), i.getId());
				LibraryData.getBorrows().putIfAbsent(LibraryData.getUser().get(key), new ArrayList<>());
				LibraryData.getBorrows().get(LibraryData.getUser().get(key)).add(books);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
				root = loader.load();
				stage = (Stage)((Node)e.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} 
		}
	 
		   if(!borrow1) {
				System.out.println("There is no stocks or currently Borrowed");
			}
		   
	if(borrow) {
		try(BufferedReader read = new BufferedReader(new FileReader(AddController.filePath))){
			String line;
			
			while((line = read.readLine()) != null) {
				String[] parts = line.split(",");
				
				if(parts.length == 6) {
					String id = parts[1];
					String title = parts[0];
					String firstName = parts[2];
					String lastName = parts[3];
					if(title.equalsIgnoreCase(borrowTitle.getText()) && 
					   firstName.equalsIgnoreCase(borrowFirstName.getText()) &&
						lastName.equalsIgnoreCase(borrowLastName.getText())) {
						parts[5] = "BORROWED";
						parts[1] = LibraryData.getUser().get(key).toString();
					}
			}
				updatedLines.add(String.join(",", parts));
		}
			try(BufferedWriter writer = new BufferedWriter(new FileWriter(AddController.filePath))){
				for(String i : updatedLines) {
					writer.write(i);
					writer.newLine();
				}
				
			} catch (IOException error) {
				System.out.println("Something went Wrong");
			}
		} catch(IOException error) {
			System.out.print("something went wrong!");
		}
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
