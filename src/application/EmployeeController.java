package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EmployeeController {
	
	@FXML
	private Button backButton;
	@FXML
	private Button booksEmployee;
	@FXML
	private Button memberEmployee;
	@FXML
	private Button borrowsEmployee;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void addViewAllBooks(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAllBook.fxml"));
	    root = loader.load();
	    
	    ViewAllBooksController controller = loader.getController();
	    controller.loadTable();
	    controller.setBackAction(event -> {
			FXMLLoader load = new FXMLLoader(getClass().getResource("EmployeeLogin.fxml"));
			try {
				root = load.load();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.centerOnScreen();
			stage.show();
	    });
	    
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
	}
	

}
