module pertemuan12 {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.jfoenix;
	requires de.jensd.fx.glyphs.fontawesome;
	requires javafx.base;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml, com.jfoenix, de.jensd.fx.glyphs.fontawesome, java.sql;
}
