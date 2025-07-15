package application;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ReturnBookController {
	
	@FXML
	private Button backButton;

	@FXML
	private TableView<ReturnBooks> borrowTableView;
	@FXML
	private TableColumn<ReturnBooks, String> borrowNumColumn;
	@FXML
	private TableColumn<ReturnBooks, String> borrowTitleColumn;
	@FXML
	private TableColumn<ReturnBooks, String> borrowIdColumn;
	@FXML
	private TableColumn<ReturnBooks, String> borrowAuthorColumn;
	@FXML
	private TableColumn<ReturnBooks, String> borrowDateColumn;
	@FXML
	private TableColumn<ReturnBooks, String> borrowStatusColumn;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void initialize() {
		borrowNumColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		borrowTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		borrowIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		borrowAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		borrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateAdd"));
		borrowStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
	}
}
