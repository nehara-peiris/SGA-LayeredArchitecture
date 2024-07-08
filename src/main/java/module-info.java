module lk.ijse.sgalayeredarchitecture {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;

    opens lk.ijse.sgalayeredarchitecture to javafx.fxml;
    opens lk.ijse.sgalayeredarchitecture.controller to javafx.fxml;
    opens lk.ijse.sgalayeredarchitecture.view.tdm to java.base;

    exports lk.ijse.sgalayeredarchitecture;
    exports lk.ijse.sgalayeredarchitecture.controller;
}