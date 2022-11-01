package application;
	
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@FXML
	private AnchorPane main;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/CalculatorView.fxml"));
            main = (AnchorPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(main);
            primaryStage.setScene(scene);
            primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
