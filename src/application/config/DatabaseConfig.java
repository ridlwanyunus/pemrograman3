package application.config;

import java.sql.*;

public class DatabaseConfig {
	
	private Connection koneksi;
	
	public Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Koneksi berhasil");
		} catch (Exception e) {
			System.out.println("Koneksi gagal: " + e);
		}
		String url = "jdbc:mysql://localhost/pemrograman3db";
		try {
			koneksi = DriverManager.getConnection(url, "root", "");
			System.out.println("Berhasil koneksi ke database.");
		} catch (SQLException ex) {
			System.out.println("Gagal koneksi database " + ex);
		}
		return koneksi;
	}
}
