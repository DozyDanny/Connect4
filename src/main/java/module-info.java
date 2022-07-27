module connect4.connect4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens connect4.connect4 to javafx.fxml;
    exports connect4.connect4;
}