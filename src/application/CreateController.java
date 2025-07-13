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
	private TextField createUsername;
	@FXML
	private PasswordField createPassword;
	
	private Scene scene;
	private Stage stage;
	private Parent root;
	
	private String username;
	private String password;
	private int id;
	private LoginController controller;
	
	String filePath = "C:\\Users\\Test\\Downloads\\Passwords.txt";
	
	public CreateController() {

	}
	
	CreateController(String username, String password, int id){
		this.username = username;
		this.password = password;
		this.id = id;
	}
	
	public void createAccount(ActionEvent e) {
		String username = createUsername.getText();
		String password = createPassword.getText();
		int id = (int) (Math.random() * 3000 + 1);
		controller.setId(Integer.toString(id));
	
		try(FileWriter writer = new FileWriter(filePath, true)){
			writer.write(username + "," + password + "," + System.lineSeparator());
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
		
		controller.builtIn();
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public int getId() {
		return this.id;
	}
}
