package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import application.config.DatabaseConfig;
import application.model.Barang;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseButton;
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
	ChoiceBox<String> cbFieldFilter;
	
	@FXML
	ChoiceBox<String> cbNamaFilter;
	
	@FXML
	JFXButton btnUpdateStok;
	

	private BarangRepository barangRepository = new BarangRepository();
	
	private ModalSpecialCaseController modalSpecialCaseController;
	private ModalUpdateBarangController modalUpdateBarangController;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		cbFieldFilter.getItems().add("Nama Barang");
		cbFieldFilter.getItems().add("Harga");
		
		cbFieldFilter.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				System.out.println(cbFieldFilter.getItems().get((Integer) newValue));
				int choice = (Integer) newValue;
				cbNamaFilter.getSelectionModel().clearSelection();
				cbNamaFilter.getItems().clear();
				if(choice == 0) {
					cbNamaFilter.getItems().add("Start With");
					cbNamaFilter.getItems().add("End With");

				} else {
					cbNamaFilter.getItems().add("Greater Than");
					cbNamaFilter.getItems().add("Less Than");
				}
			}
		});
		
		
		
		
		System.out.println(cbNamaFilter.getValue());
		
		Font font = Font.loadFont(this.getClass().getResourceAsStream("Poppins-Medium.ttf"), 12);
		textField.setFont(font);
		
		JFXTreeTableColumn<Barang, String> kodeBarang = new JFXTreeTableColumn<Barang, String>("Kode Barang");
		kodeBarang.setPrefWidth(180);
		kodeBarang.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Barang,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Barang, String> param) {
				return param.getValue().getValue().getKodeBarang();
			}
		});
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
		System.out.println(barangRepository.isHargaColumnCreated());
		if(barangRepository.isHargaColumnCreated() == true) {
			JFXTreeTableColumn<Barang, Integer> harga = new JFXTreeTableColumn<Barang, Integer>("Harga");
			harga.setPrefWidth(200);
			harga.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Barang,Integer>, ObservableValue<Integer>>() {
				
				@Override
				public ObservableValue<Integer> call(TreeTableColumn.CellDataFeatures<Barang, Integer> param) {
					return param.getValue().getValue().getHarga().asObject();
				}
			});
			treeView.getColumns().setAll(kodeBarang, namaBarang, stok, harga);
		} else {
			treeView.getColumns().setAll(kodeBarang, namaBarang, stok);
		}
		
		
		ObservableList<Barang> listBarang = FXCollections.observableArrayList();
		listBarang = barangRepository.findAllBarang();

		
		TreeItem<Barang> root = new RecursiveTreeItem<Barang>(listBarang, RecursiveTreeObject::getChildren);

		treeView.setRoot(root);
		treeView.setShowRoot(false);
		
		treeView.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				Barang barang = treeView.getSelectionModel().getSelectedItem().getValue();
				showModalUpdateBarang(barang);
			}
		});
	}
	
	public void showModalSpecialCase() {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("modal_special_case.fxml"));
			Parent root = loader.load();
			this.modalSpecialCaseController = loader.getController();
			this.modalSpecialCaseController.setMainController(this);
			this.modalSpecialCaseController.show(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showModalUpdateBarang(Barang data) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("modal_update_barang.fxml"));
			Parent root = loader.load();
			this.modalUpdateBarangController = loader.getController();
			this.modalUpdateBarangController.show(root, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
