package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import application.model.Barang;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class Controller implements Initializable {

	@FXML
	private JFXTreeTableView<Barang> treeView;
	
	@FXML
	Text txtTitle = new Text();
	
	@FXML
	TextField textField;
	
	@FXML
	ChoiceBox<String> cbNamaFilter;
	
	@FXML
	JFXButton btnUpdateStok;
	
	private ModalUpdateStokController modalUpdateStokController;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		cbNamaFilter.getItems().add("Begin with");
		cbNamaFilter.getItems().add("End with");
		
		cbNamaFilter.setValue("Begin with");
		
		System.out.println(cbNamaFilter.getValue());
		
		Font font = Font.loadFont(this.getClass().getResourceAsStream("Poppins-Medium.ttf"), 12);
		textField.setText("heh");
		textField.setFont(font);
		
		JFXTreeTableColumn<Barang, String> kodeBarang = new JFXTreeTableColumn<Barang, String>("Kode Barang");
		kodeBarang.setPrefWidth(180);
		kodeBarang.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Barang,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Barang, String> param) {
				return param.getValue().getValue().getKodeBarang();
			}
		});
//		kodeBarang.setCellFactory(new Callback<TreeTableColumn<Barang,String>, TreeTableCell<Barang,String>>() {
//
//			@Override
//			public TreeTableCell<Barang, String> call(TreeTableColumn<Barang, String> arg0) {
//				// TODO Auto-generated method stub
//				setF
//				return null;
//			}
//		});
		JFXTreeTableColumn<Barang, String> namaBarang = new JFXTreeTableColumn<Barang, String>("Nama Barang");
		namaBarang.setPrefWidth(180);
		namaBarang.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Barang,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Barang, String> param) {
				return param.getValue().getValue().getNamaBarang();
			}
		});
		JFXTreeTableColumn<Barang, Integer> stok = new JFXTreeTableColumn<Barang, Integer>("Stok");
		stok.setPrefWidth(180);
		stok.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Barang,Integer>, ObservableValue<Integer>>() {
			
			@Override
			public ObservableValue<Integer> call(TreeTableColumn.CellDataFeatures<Barang, Integer> param) {
				return param.getValue().getValue().getStok().asObject();
			}
		});
		JFXTreeTableColumn<Barang, Integer> harga = new JFXTreeTableColumn<Barang, Integer>("Harga");
		harga.setPrefWidth(200);
		harga.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Barang,Integer>, ObservableValue<Integer>>() {
			
			@Override
			public ObservableValue<Integer> call(TreeTableColumn.CellDataFeatures<Barang, Integer> param) {
				return param.getValue().getValue().getHarga().asObject();
			}
		});
		
		ObservableList<Barang> listBarang = FXCollections.observableArrayList();
		listBarang.add(new Barang("K0001", "Mouse", 5, 150000));
		listBarang.add(new Barang("K0002", "Keyboard", 15, 250000));
		listBarang.add(new Barang("K0003", "Laptop", 10, 15000000));
		
		TreeItem<Barang> root = new RecursiveTreeItem<Barang>(listBarang, RecursiveTreeObject::getChildren);
		treeView.getColumns().setAll(kodeBarang, namaBarang, stok, harga);
		treeView.setRoot(root);
		treeView.setShowRoot(false);
		
	}
	
	public void showModalUpdateStok() {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("modal_update_stok.fxml"));
			Parent root = loader.load();
			this.modalUpdateStokController = loader.getController();
			this.modalUpdateStokController.show(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
