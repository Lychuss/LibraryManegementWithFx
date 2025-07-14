package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewAllBooksController {

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
	
	/**
	 * 
	 */
	public void loadTable() {
	    if (LibraryData.getBooks() == null) {
	        System.out.println("Books list is null. Cannot load table.");
	        return;
	    }
		System.out.println(LibraryData.getBooks());
		
		 int num = 0;
		
		for(Book i : LibraryData.getBooks()) {
			num++;
		 ObservableList<ViewAllBooks> data = FXCollections.observableArrayList(
		            new ViewAllBooks(Integer.toString(num), i.getTitle(), "0"+ i.getId()  , i.getFirstName() + i.getLastName(), i.getDatePublished(), i.getAvailability())
		        );

		        tableView.setItems(data);
		}
	}
}
