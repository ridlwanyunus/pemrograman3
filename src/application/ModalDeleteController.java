package application;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.config.DatabaseConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ModalDeleteController implements Initializable {
	
	@FXML
	private ChoiceBox<String> cbDelete;
	
	@FXML
	private TextField deleteTxtField;
	
	@FXML
	private JFXButton deleteBtnHapus;
	
	@FXML
	private JFXButton btnClose;
	
	private Controller parentController;
	
	
	private BarangRepository barangRepository = new BarangRepository();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbDelete.getItems().add("Begin With");
		cbDelete.setValue("Begin With");
		
	}
	
	
	public void show(Parent root) {
		try {
			Scene scene = new Scene(root);
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			Stage stage = new Stage();
			String imgPath = this.getClass().getResource("icon.png").toExternalForm();
			Image img = new Image(imgPath);
			stage.getIcons().add(img);
			stage.setTitle("Form Delete Bulk");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(ActionEvent event) {
		System.out.println("delete");
		barangRepository.deleteContainString(deleteTxtField.getText());
		this.parentController.refreshTable();
	}
	
	public void close() {
		System.out.println("close");
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
	}


	public Controller getParentController() {
		return parentController;
	}


	public void setParentController(Controller parentController) {
		this.parentController = parentController;
	}


}
