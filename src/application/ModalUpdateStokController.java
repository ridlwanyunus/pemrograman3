package application;

import java.sql.Connection;

import com.jfoenix.controls.JFXButton;

import application.config.DatabaseConfig;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ModalUpdateStokController {
	
	@FXML
	private JFXButton btnSave;
	
	@FXML
	private JFXButton btnClose;
	
	@FXML
	private TextField txtMin, txtAkhir;
	
	public Controller parentController;
	
	private BarangRepository barangRepository = new BarangRepository();
	
	public void show(Parent root) {
		try {
			Scene scene = new Scene(root);
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			Stage stage = new Stage();
			String imgPath = this.getClass().getResource("icon.png").toExternalForm();
			Image img = new Image(imgPath);
			stage.getIcons().add(img);
			stage.setTitle("Form Update Stok");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setParentController(Controller parentController) {
		this.parentController = parentController;
	}
	
	public void save() {
		
		barangRepository.updateStokLessThan(txtMin.getText(), txtAkhir.getText());
		this.parentController.refreshTable();
		System.out.println("save");
	}
	
	public void close() {
		System.out.println("save");
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
	}

}
