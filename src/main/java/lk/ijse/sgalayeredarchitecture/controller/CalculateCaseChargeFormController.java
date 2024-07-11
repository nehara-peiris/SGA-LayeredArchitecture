package lk.ijse.sgalayeredarchitecture.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CalculateCaseChargeFormController {
    public TableView tblPayCal;
    public TableColumn colChargeId;
    public TableColumn colDescription;
    public TableColumn colAmount;
    public TableColumn colAction;
    public TextField txtCaseId;
    public TextField txtLawyerId;
    public TextField txtClientId;
    public TextField txtChargeId;
    public JFXButton btnAddCart;
    public Label lblPayId;
    public Label lblDate;
    public Label lblClientName;
    public Label lblDescription;
    public Label lblAmount;
    public Label lblTotal;

    public void clientIDOnAction(ActionEvent event) {

    }
}
