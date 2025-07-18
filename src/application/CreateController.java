package application;

import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateController {

	@FXML
	private Button createButton;
	@FXML
	private Button createCancelButton;
	@FXML
	private TextField createUsername;
	@FXML
	private PasswordField createPassword;
	
	private Scene scene;
	private Stage stage;
	private Parent root;

	
	String filePath = "C:\\Users\\Test\\Downloads\\Passwords.txt";
	
	public CreateController() {

	}
	
	public void createAccount(ActionEvent e) {
		String username = createUsername.getText();
		String password = createPassword.getText();
		int id = (int) (Math.random() * 3000 + 1);
		String userId = Integer.toString(id);
		
		Users createdUser = new Users(username, password, id);
		LibraryData.getCreatedUser().add(createdUser);
		
		LibraryData.getUser().put(username, id);
	
		try(FileWriter writer = new FileWriter(filePath, true)){
			writer.write(username + "," + password + "," + userId + System.lineSeparator());
			if(!username.isEmpty() && !password.isEmpty()) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
				root = loader.load();
				
				stage = (Stage)((Node)e.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
			
		} catch(IOException event) {
			System.out.println("Something went wrong!");
		}
	}
	
	public void cancelButton(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
		root = loader.load();
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
	}
}
