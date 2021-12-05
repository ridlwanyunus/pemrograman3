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
	
	private boolean isHargaColumnCreated = false;
	
	public ObservableList<Barang> find(String sql) {
		ObservableList<Barang> list = FXCollections.observableArrayList();
		Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet hasil = stat.executeQuery(sql);
			if(this.isHargaColumnCreated() == true) {
				while(hasil.next()) {
					Barang barang = new Barang(
						hasil.getString(1),
						hasil.getString(2),
						hasil.getInt(3),
						hasil.getInt(4)
					);
					list.add(barang);
				}
			} else {
				while(hasil.next()) {
					Barang barang = new Barang(
						hasil.getString(1),
						hasil.getString(2),
						hasil.getInt(3),
						0
					);
					list.add(barang);
				}
			}
			hasil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public ObservableList<Barang> findAllBarang() {
		ObservableList<Barang> list = FXCollections.observableArrayList();
		String sql = "SELECT * FROM barang order by kode_barang asc";
		list = this.find(sql);
		return list;
	}
	
	public ObservableList<Barang> findBarangBeginWith(String key) {
		ObservableList<Barang> list = FXCollections.observableArrayList();
		String sql = "SELECT * FROM barang WHERE nama_barang LIKE '"+key+"%' order by kode_barang asc";
		list = this.find(sql);
		return list;
	}
	
	public ObservableList<Barang> findBarangEndWith(String key) {
		ObservableList<Barang> list = FXCollections.observableArrayList();
		String sql = "SELECT * FROM barang WHERE nama_barang LIKE '%"+key+"' order by kode_barang asc";
		list = this.find(sql);
		return list;
	}
	
	public ObservableList<Barang> findBarangStockGreaterThan(String key) {
		ObservableList<Barang> list = FXCollections.observableArrayList();
		String sql = "SELECT * FROM barang WHERE harga > "+key+" order by kode_barang asc";
		list = this.find(sql);
		return list;
	}
	
	public ObservableList<Barang> findBarangStockLessThan(String key) {
		ObservableList<Barang> list = FXCollections.observableArrayList();
		String sql = "SELECT * FROM barang WHERE harga < "+key+" order by kode_barang asc";
		list = this.find(sql);
		return list;
	}


	public boolean isHargaColumnCreated() {
		String sql = "SELECT * FROM barang order by kode_barang asc";
		Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet hasil = stat.executeQuery(sql);
			int countColumn = hasil.findColumn("harga");
			System.out.println("jumlah kolom : " + countColumn);
			if(countColumn == 4) {
				return true;
			}
			hasil.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void setHargaColumnCreated(boolean isHargaColumnCreated) {
		this.isHargaColumnCreated = isHargaColumnCreated;
	}
	
	public void operation(String sql) {
		Statement stat;
		try {
			stat = conn.createStatement();
			int hasil = stat.executeUpdate(sql);
			System.out.println(hasil);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void addHargaColumn() {
		String sql = "ALTER TABLE barang ADD harga int(10)";
		operation(sql);
		this.isHargaColumnCreated = true;
	}
	
	public void dropTable() {
		String sql = "DROP TABLE IF EXISTS barang";
		operation(sql);
	}
	
	public void createTable() {
		String sql = "CREATE TABLE barang (kode_barang VARCHAR(4) NOT NULL, nama_barang VARCHAR(255) NOT NULL, stok int(10) NOT NULL, PRIMARY KEY(kode_barang))";
		operation(sql);
	}
	
	public void initializeTable() {
		String sql = "INSERT INTO barang (kode_barang, nama_barang, stok) VALUES ('F239', 'Flashdisk', 21), ('H789', 'Harddisk', 5), ('K142', 'Keyboard', 18), ('M123', 'Mouse', 35), ('M753', 'Monitor', 3)";
		operation(sql);
	}
	
	public void updateStok(String kodeBarang, String key) {
		String sql = "UPDATE barang SET stok = "+key+" WHERE kode_barang IN ('"+kodeBarang+"')";
		operation(sql);
	}
	
	public void updateHarga(String kodeBarang, String key) {
		String sql = "UPDATE barang SET harga = "+key+" WHERE kode_barang IN ('"+kodeBarang+"')";
		operation(sql);
	}
	
	public void updateStokLessThan(String min, String addition) {
		String sql = "UPDATE barang SET stok = stok + "+addition+" WHERE stok < "+min;
		operation(sql);
	}
	
	public void deleteContainString(String key) {
		String sql = "DELETE FROM barang WHERE nama_barang LIKE '%"+key+"%'";
		operation(sql);
	}
	
}
