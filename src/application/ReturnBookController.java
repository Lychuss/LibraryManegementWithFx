package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ReturnBookController {
	
	@FXML
	private Button returnBackButton;

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
	@FXML
	private TableColumn<ReturnBooks, Void> borrowReturnColumn;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private static ArrayList<Book> listBorrows = new ArrayList<>();
	
    public static ArrayList<Book> getListBorrows() {
        return listBorrows;
    }
	
	public void initialize() {
	
		borrowNumColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		borrowTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		borrowIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		borrowAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		borrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateAdd"));
		borrowStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		borrowReturnColumn.setCellFactory(col -> new TableCell<>() {
			private final Button btn = new Button("Return");
		{	
			btn.setOnAction(event -> {
				List<String> updatedLines = new ArrayList<>();
				ReturnBooks data = getTableView().getItems().get(getIndex());
				getTableView().getItems().remove(data);
				
				try(BufferedReader read = new BufferedReader(new FileReader(AddController.filePath))){
					String line;
					
					while((line = read.readLine()) != null) {
						String[] parts = line.split(",");
						String[] authors = data.getAuthor().split(" ");
						String authorsFirstName = authors[0];
						if(parts.length == 6) {
							String title = parts[0];
							String firstName = parts[2];
							String lastName = parts[3];
							String author = firstName + " " + lastName;
							if(data.getTitle().equals(title) &&
							   data.getAuthor().equals(author)) {
								System.out.print(author);
								parts[5] = "AVAILABLE";
								for(Map.Entry<Integer, ArrayList<Book>> r : LibraryData.getBorrows().entrySet()) {
									if(Integer.parseInt(data.getId()) == (r.getKey())) {
										Iterator<Book> books = LibraryData.getBorrows().get(r.getKey()).iterator();
										while(books.hasNext()) {
											Book book = books.next();
											if(book.getTitle().equalsIgnoreCase(data.getTitle()) && 
											   book.getFirstName().equalsIgnoreCase(authorsFirstName)) {
												books.remove();
												for(Book i : LibraryData.getBooks()) {
													if(i.getTitle().equals(data.getTitle())) {
														i.setAvailability("AVAILABLE");
													}
												}
											}
										}
										}
								
									}
								}
							}
						updatedLines.add(String.join(",", parts));
					}
						
				} catch (FileNotFoundException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
				
				try(BufferedWriter writer = new BufferedWriter(new FileWriter(AddController.filePath))){
					for(String i : updatedLines) {
						writer.write(i);
						writer.newLine();
					}
					
				} catch (IOException error) {
					System.out.println("Something went Wrong");
				}
			});
		}
		
		@Override
		protected void updateItem(Void item, boolean empty) {
			super.updateItem(item, empty);
			if(empty) {
				setGraphic(null);
			} else {
				setGraphic(btn);
			}
		}
		});
	}
	
	public static void currentlyBorrow() throws FileNotFoundException, IOException {
		try(BufferedReader reader = new BufferedReader(new FileReader(AddController.filePath))){
			String line;
			
			while((line = reader.readLine()) != null) {
				
				String[] parts = line.split(",");
				
				if(parts.length == 6) {
					String status = parts[5];
					if(status.equalsIgnoreCase("BORROWED")) {
						Book borrow = new Book(parts[2], parts[3], parts[0], parts[4], parts[5], Integer.parseInt(parts[1]));
						LibraryData.getBorrows().putIfAbsent(Integer.parseInt(parts[1]), new ArrayList<>());
						LibraryData.getBorrows().get(Integer.parseInt(parts[1])).add(borrow);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Something went wrong!");
		}
	}
	
	public void loadTable() {
		if(LibraryData.getBorrows() == null) {
			System.out.println("There is no object inside");
		}
		
		int num = 0;
		
		ObservableList<ReturnBooks> returnBook = FXCollections.observableArrayList();
		
		String key = LibraryData.getUser().keySet().iterator().next();
		
		for(Map.Entry<Integer, ArrayList<Book>> i : LibraryData.getBorrows().entrySet()) {
			
			if(i.getKey().equals(LibraryData.getUser().get(key))) {
			for(Book m : i.getValue())	{
				num++;
				ReturnBooks data = new ReturnBooks(Integer.toString(num), m.getTitle(), 0 + Integer.toString(i.getKey()), m.getFirstName() + " " 
						           + m.getLastName(), m.getDatePublished(), m.getAvailability());   
				
				returnBook.add(data);
				System.out.print(i.getValue().size());
			}
			}
		}
		
		borrowTableView.setItems(returnBook);
	}
	
	public void backButton(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
		root = loader.load();
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
}
