module lk.ijse.sgalayeredarchitecture {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;
    requires java.desktop;
    requires jasperreports;

    opens lk.ijse.sgalayeredarchitecture to javafx.fxml;
    opens lk.ijse.sgalayeredarchitecture.controller to javafx.fxml;
    opens lk.ijse.sgalayeredarchitecture.view.tdm to javafx.base;

    exports lk.ijse.sgalayeredarchitecture;
    exports lk.ijse.sgalayeredarchitecture.controller;

}
