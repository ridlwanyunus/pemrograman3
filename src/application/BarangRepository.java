package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.config.DatabaseConfig;
import application.model.Barang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BarangRepository {
	
	private Connection conn = new DatabaseConfig().connect();
	
	private static boolean isHargaColumnCreated;
	
	public ObservableList<Barang> findAllBarang() {
		ObservableList<Barang> list = FXCollections.observableArrayList();
		String sql = "SELECT * FROM barang order by kode_barang asc";
		Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet hasil = stat.executeQuery(sql);
			while(hasil.next()) {
				Barang barang = new Barang(
					hasil.getString(1),
					hasil.getString(2),
					hasil.getInt(3),
					0
				);
				list.add(barang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			this.isHargaColumnCreated = false;
		}
		
		return list;
	}

	public boolean isHargaColumnCreated() {
		return isHargaColumnCreated;
	}

	public void setHargaColumnCreated(boolean isHargaColumnCreated) {
		this.isHargaColumnCreated = isHargaColumnCreated;
	}
	
}
