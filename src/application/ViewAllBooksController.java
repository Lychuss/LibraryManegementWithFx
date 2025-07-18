package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class ViewAllBooksController {
	
	private Stage stage;
	private Parent root;
	private Scene scene;
	
	@FXML
	private Button backButton;
	@FXML
	private TableView<ViewAllBooks> tableView;
	@FXML
	private TableColumn<ViewAllBooks, String> numberColumn;
	@FXML
	private TableColumn<ViewAllBooks, String> titleColumn;
	@FXML
	private TableColumn<ViewAllBooks, String> idColumn;
	@FXML
	private TableColumn<ViewAllBooks, String> authorColumn;
	@FXML
	private TableColumn<ViewAllBooks, String> dateColumn;
	@FXML
	private TableColumn<ViewAllBooks, String> statusColumn;

	
	public void initialize() {
		numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateAdd"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
	}
	
	public void loadTable() {
	    if (LibraryData.getBooks() == null) {
	        System.out.println("Books list is null. Cannot load table.");
	        return;
	    }
	    
	    ObservableList<ViewAllBooks> booksBuilt = FXCollections.observableArrayList();
		
		 int num = 0;
		
		for(Book i : LibraryData.getBooks()) {
			num++;
		
		     ViewAllBooks data = new ViewAllBooks(Integer.toString(num), i.getTitle(), "0"+ i.getId()  , i.getFirstName()+ " " + i.getLastName(), i.getDatePublished(), i.getAvailability());
		     booksBuilt.add(data);
		}
		tableView.setItems(booksBuilt);
	}
	
    public void setBackAction(EventHandler<ActionEvent> handler) {
        backButton.setOnAction(handler);
    }
}
