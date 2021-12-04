module pertemuan12 {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.jfoenix;
	requires de.jensd.fx.glyphs.fontawesome;
	
	opens application to javafx.graphics, javafx.fxml, com.jfoenix, de.jensd.fx.glyphs.fontawesome;
}
