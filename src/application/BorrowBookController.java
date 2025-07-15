package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	private TextField borrowFirstName;
	@FXML
	private TextField borrowLastName;
	@FXML
	private TextField borrowTitle;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void borrowBook(ActionEvent e) throws IOException {
		
		List<String> updatedLines = new ArrayList<>();
		
		for(Book i : LibraryData.getBooks()) {
			if(i.getFirstName().equals(borrowFirstName.getText()) && i.getLastName().equals(borrowLastName.getText()) && i.getTitle().equals(borrowTitle.getText())) {
				i.setAvailability("BORROWED");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
				root = loader.load();
				stage = (Stage)((Node)e.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
		}
		try(BufferedReader read = new BufferedReader(new FileReader(AddController.filePath))){
			String line;
			
			while((line = read.readLine()) != null) {
				String[] parts = line.split(",");
				
				if(parts.length == 6) {
					String title = parts[0];
					String firstName = parts[2];
					String lastName = parts[3];
					if(title.equalsIgnoreCase(borrowTitle.getText()) && 
					   firstName.equalsIgnoreCase(borrowFirstName.getText()) &&
						lastName.equalsIgnoreCase(borrowLastName.getText())) {
						parts[5] = "BORROWED";
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
