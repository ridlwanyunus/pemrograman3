package application;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.config.DatabaseConfig;
import application.model.Barang;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ModalUpdateBarangController implements Initializable {
	
	@FXML
	private TextField tfKodeBarang;
	
	@FXML
	private TextField tfNamaBarang;
	
	@FXML
	private TextField tfStok;
	
	@FXML
	private TextField tfHarga;
	
	@FXML
	private JFXButton btnSave;
	
	@FXML
	private JFXButton btnClose;
	
	@FXML
	private Label lblHarga;
	
	public Controller parentController;
	
	private BarangRepository barangRepository = new BarangRepository();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tfKodeBarang.setEditable(false);
	}
	
	public void show(Parent root, Barang barang) {
		try {
			Scene scene = new Scene(root);
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			Stage stage = new Stage();
			String imgPath = this.getClass().getResource("icon.png").toExternalForm();
			Image img = new Image(imgPath);
			stage.getIcons().add(img);
			stage.setTitle("Form Update Barang");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tfKodeBarang.setText(barang.getKodeBarang().getValue());
		tfNamaBarang.setText(barang.getNamaBarang().getValue());
		tfStok.setText(barang.getStok().getValue().toString());
		tfHarga.setText(barang.getHarga().getValue().toString());
		System.out.println("Harga is created : " + barangRepository.isHargaColumnCreated());
		if(barangRepository.isHargaColumnCreated() == false) {
			lblHarga.setVisible(false);
			tfHarga.setVisible(false);
		} else {
			lblHarga.setVisible(true);
			tfHarga.setVisible(true);
		}
	}
	
	public void save() {
		barangRepository.updateStok(tfKodeBarang.getText(), tfStok.getText());
		if(barangRepository.isHargaColumnCreated()) {
			barangRepository.updateHarga(tfKodeBarang.getText(), tfHarga.getText());
		}
		this.parentController.refreshTable();
		System.out.println("save");
	}
	
	public void close() {
		System.out.println("close");
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
	}
	
	public void setParentController(Controller parentController) {
		this.parentController = parentController;
	}
}
