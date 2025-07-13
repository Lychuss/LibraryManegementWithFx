package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

	@FXML
	private Button button;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<AddController> books;
	
	public void setBooks(ArrayList<AddController> books) {
		this.books = books;
	}
	
	public void addMenu(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddBooks.fxml"));
		root = loader.load();
		
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void addViewAllBooks(ActionEvent e) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAllBook.fxml"));
	    root = loader.load();

		ViewAllBooksController controller = loader.getController();
		controller.setBooks(books);
		controller.loadTable();

		
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
