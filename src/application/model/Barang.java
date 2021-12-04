package application.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Barang extends RecursiveTreeObject<Barang>{

	private StringProperty kodeBarang;
	private StringProperty namaBarang;
	private IntegerProperty stok;
	private IntegerProperty harga;
	
	public Barang() {
		
	}

	public Barang(String kodeBarang, String namaBarang, Integer stok, Integer harga) {
		super();
		this.kodeBarang = new SimpleStringProperty(kodeBarang);
		this.namaBarang = new SimpleStringProperty(namaBarang);
		this.stok = new SimpleIntegerProperty(stok);
		this.harga = new SimpleIntegerProperty(harga);
	}

	public StringProperty getKodeBarang() {
		return kodeBarang;
	}

	public void setKodeBarang(String kodeBarang) {
		this.kodeBarang = new SimpleStringProperty(kodeBarang);
	}

	public StringProperty getNamaBarang() {
		return namaBarang;
	}

	public void setNamaBarang(String namaBarang) {
		this.namaBarang = new SimpleStringProperty(namaBarang);
	}

	public IntegerProperty getStok() {
		return stok;
	}

	public void setStok(IntegerProperty stok) {
		this.stok = stok;
	}

	public IntegerProperty getHarga() {
		return harga;
	}

	public void setHarga(IntegerProperty harga) {
		this.harga = harga;
	}

}
