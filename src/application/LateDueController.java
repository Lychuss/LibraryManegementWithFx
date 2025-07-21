package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

public class LateDueController {
	
	@FXML
	private Button backButton;
	@FXML
	private TableView<LateDute> tableViewDue;
	@FXML
	private TableColumn<LateDute, String> idColumn;
	@FXML
	private TableColumn<LateDute, String> nameColumn;
	@FXML
	private TableColumn<LateDute, String> bookColumn;
	@FXML
	private TableColumn<LateDute, String> borrowColumn;
	@FXML
	private TableColumn<LateDute, String> lateDueColumn;
	
	private Stage stage;
	private Parent root;
	private Scene scene;
	
	public static void lateDueBuilt() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(AddController.filePath))) {
			String line;
			
			while((line = reader.readLine()) != null){
				String[] parts = line.split(",");
				if(parts.length == 6) {
					String id = parts[1];
					String book = parts[0];
					String date = parts[4];
					String borrowed = parts[5];
					String[] parts1 = date.split("-");
					int year = Integer.parseInt(parts1[0]);
					int month = Integer.parseInt(parts1[1]);
					int date1 = Integer.parseInt(parts1[2]);
					int date2 = date1 + 5;
					if(borrowed.equals("BORROWED")) {
					LocalDate borrowedDate = LocalDate.of(year, month, date2);
					for(Users m : LibraryData.getCreatedUser()) {
						if(id.equals(Integer.toString(m.getId()))) {
							System.out.println(LibraryData.getLateDue());
							LateDue due = new LateDue(id, m.getUsername(), book, borrowedDate);
							LibraryData.getLateDue().add(due);
							System.out.println(LibraryData.getLateDue());
						}
					 }
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
	public void initialize() {
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		bookColumn.setCellValueFactory(new PropertyValueFactory<>("book"));
		borrowColumn.setCellValueFactory(new PropertyValueFactory<>("due"));
		lateDueColumn.setCellValueFactory(new PropertyValueFactory<>("lateDue"));
	}
	
	public void loadTable() {
		ObservableList<LateDute> booksBuilt = FXCollections.observableArrayList();
		for(LateDue i : LibraryData.getLateDue()) {
			System.out.println(LibraryData.getLateDue());
			LocalDate due = i.getDue();
			String dueDate = due.toString();
			LocalDate today = LocalDate.now();
			long daysBetween = ChronoUnit.DAYS.between(due, today);
			i.setlateDue(daysBetween);
			String dueDays = String.valueOf(i.getLateDue());
				if(i.getLateDue() > 0) {
					System.out.println(i.getBook());
					LateDute data = new LateDute(i.getId(), i.getName(), i.getBook(), dueDate, dueDays + " Days Late");
					booksBuilt.add(data);
				}
		}
		tableViewDue.setItems(booksBuilt);
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
