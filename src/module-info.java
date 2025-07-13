module LibraryManagementSystem {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml,  javafx.base;
}
