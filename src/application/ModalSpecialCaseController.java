package application;

import java.io.IOException;
import java.sql.Connection;

import application.config.DatabaseConfig;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ModalSpecialCaseController {

	private ModalUpdateStokController modalUpdateStokController;
	
	private ModalDeleteController modalDeleteController;
	
	private Controller mainController;
	
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
			stage.setTitle("Form Special Case");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showModalUpdateStok() {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("modal_update_stok.fxml"));
			Parent root = loader.load();
			this.modalUpdateStokController = loader.getController();
			this.modalUpdateStokController.setParentController(this.mainController);
			this.modalUpdateStokController.show(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showModalDelete() {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("modal_delete.fxml"));
			Parent root = loader.load();
			this.modalDeleteController = loader.getController();
			this.modalDeleteController.setParentController(mainController);
			this.modalDeleteController.show(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void btnAddHargaColumn() {
		
		barangRepository.addHargaColumn();
		barangRepository.setHargaColumnCreated(true);
		this.mainController.refreshTable();
	}
	
	public void setMainController(Controller mainController) {
		this.mainController = mainController;
	}
	
}
