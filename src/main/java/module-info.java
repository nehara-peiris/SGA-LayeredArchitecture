module lk.ijse.sgalayeredarchitecture {
    requires javafx.controls;
    requires javafx.fxml;


    opens lk.ijse.sgalayeredarchitecture to javafx.fxml;
    exports lk.ijse.sgalayeredarchitecture;
}