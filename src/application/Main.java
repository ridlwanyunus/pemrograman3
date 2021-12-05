package application;
	
import java.sql.Connection;

import application.config.DatabaseConfig;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application implements EventHandler<ActionEvent>{
	
	@FXML
	Text txtTitle = new Text();
	
	@FXML
	TextField textField;
	
	BarangRepository barangRepository = new BarangRepository();
	
	@Override
	public void start(Stage primaryStage) {
//		barangRepository.dropDatabase();
//		barangRepository.createDatabase();
		barangRepository.dropTable();
		barangRepository.createTable();
		barangRepository.initializeTable();
		
		try {
			Font font = Font.loadFont(getClass().getResource("PTSans-Bold.ttf").toExternalForm(), 12);
			Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
			
			Scene scene = new Scene(root,815,566);
			
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);	
			String imgPath = this.getClass().getResource("icon.png").toExternalForm();
			Image img = new Image(imgPath);
			primaryStage.getIcons().add(img);
			primaryStage.setTitle("202043579115 Ridlwan Yunus");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
	}
}
