package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private Button loginButton;
	@FXML
	private TextField userUsername;
	@FXML
	private PasswordField userPassword;
	private Parent root;
	private Stage stage;
	private Scene scene;
	private HashMap<String, String> users = new HashMap<>();
	private ViewAllBooksController controller = new ViewAllBooksController();
	private String id1;
	
	public void setId(String id) {
		this.id1 = id;
	}
	
	String filePath1 = "C:\\Users\\Test\\Downloads\\Passwords.txt";

	public void builtIn(){
		
	try(BufferedReader reader = new BufferedReader(new FileReader(filePath1))){
		
		String line;
		
		while((line = reader.readLine()) != null) {
			String[] parts = line.split(",");
			
			if(parts.length == 2) {
				String username = parts[0].trim();
				String password = parts[1].trim();
				String id = id1;
				users.put(username, password);
				
				if(username == userUsername.getText()) {
					controller.setId(id);
				}
			}
		}
	}
	catch (IOException e) {
		System.out.println("Something went wrong!");
	}
}
	
	
	public void loginComplete(ActionEvent event) throws IOException {
		builtIn();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
		root = loader.load();
		
		String username = userUsername.getText();
		String password = userPassword.getText();
		
		for(Map.Entry<String, String> i : users.entrySet()) {
			if(i.getKey().equals(username) && i.getValue().equals(password)){
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				System.out.print("Login Successfully");
				break;
			}
	}
  }
	
	public void useHashMap(String key, String value) {
		users.put(key, value);
	}
	
	public void signUp(MouseEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAccount.fxml"));
		root = loader.load();
		
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
}

