package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MembersController {

	@FXML
	private Button backButton;
	@FXML
	private TableView<Members> membersTableView;
	@FXML
	private TableColumn<Members, String> membersColumn;
	@FXML
	private TableColumn<Members, String> idColumn;
	@FXML
	private TableColumn<Members, String> addedColumn;
	@FXML
	private TableColumn<Members, String> borrowedColumn;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void initialize() {
		membersColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		addedColumn.setCellValueFactory(new PropertyValueFactory<>("added"));
		borrowedColumn.setCellValueFactory(new PropertyValueFactory<>("borrowed"));
	}
	
	public void loadTable() {
		if(LibraryData.getCreatedUser().isEmpty() || LibraryData.getCreatedUser().equals(null)) {
			System.out.println("Something went wrong");
		}
		
		ObservableList<Members> list = FXCollections.observableArrayList();
		
		for(Users i : LibraryData.getCreatedUser()) {
			Members users = new Members(i.getUsername(), "0" + Integer.toString(i.getId()), Integer.toString(i.getAdded()), Integer.toString(i.getBorrowed()));
			
			list.add(users);
		}
		
		membersTableView.setItems(list);
	}
	
	public void backButton(ActionEvent e) throws IOException {
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
