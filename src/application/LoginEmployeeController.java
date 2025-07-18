package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import javafx.stage.Stage;

public class LoginEmployeeController {

		@FXML
		private Button loginEmployeeButton;
		@FXML
		private TextField loginEmployeeUsername;
		@FXML
		private PasswordField loginEmployeePassword;
		@FXML
		private Button loginCancelButton;
		
		private Stage stage;
		private Parent root;
		private Scene scene;
		
		static String filePath = "C:\\Users\\Test\\Downloads\\Employee.txt";
		
		public static void employeeAccounts() throws FileNotFoundException, IOException {
			try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
				String line;
				
				while((line = reader.readLine()) != null) {
					String[] parts = line.split(",");
					
					if(parts.length == 2) {
						String username = parts[0];
						String password = parts[1];
						LibraryData.getEmployeeAccounts().put(username, password);			
					}
				}
			}
		}

		public void loginEmployee(ActionEvent e) throws IOException {
			for(String key : LibraryData.getEmployeeAccounts().keySet()) {
				if(key.equals(loginEmployeeUsername.getText()) && 
				   LibraryData.getEmployeeAccounts().get(key).equals(loginEmployeePassword.getText())) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeLogin.fxml"));
					root = loader.load();
					stage = (Stage)((Node)e.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.centerOnScreen();
					stage.setResizable(false);
					stage.show();
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

