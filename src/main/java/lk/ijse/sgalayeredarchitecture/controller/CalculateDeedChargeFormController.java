package lk.ijse.sgalayeredarchitecture.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import lk.ijse.sgalayeredarchitecture.bo.BOFactory;
import lk.ijse.sgalayeredarchitecture.bo.custom.ChargeBo;
import lk.ijse.sgalayeredarchitecture.bo.custom.ClientBo;
import lk.ijse.sgalayeredarchitecture.dto.ChargeDTO;
import lk.ijse.sgalayeredarchitecture.view.tdm.ChargeCalculationTm;

import java.sql.SQLException;
import java.util.Optional;

public class CalculateDeedChargeFormController {
    public JFXButton btnAddCart;
    @FXML
    private Label lblTotal;
    @FXML
    private Label lblPayId;
    @FXML
    private Label lblDate;
    @FXML
    private TextField txtDeedId;
    @FXML
    private TextField txtLawyerId;
    @FXML
    private TextField txtClientId;
    @FXML
    private Label lblClientName;
    @FXML
    private TextField txtChargeId;
    @FXML
    private Label lblDescription;
    @FXML
    private Label lblAmount;
    @FXML
    private TableView<ChargeCalculationTm> tblPayCal;
    @FXML
    private TableColumn<?, ?> colChargeId;
    @FXML
    private TableColumn<?, ?> colDescription;
    @FXML
    private TableColumn<?, ?> colAmount;
    @FXML
    private TableColumn<?, ?> colAction;

    private ObservableList<ChargeCalculationTm> obList = FXCollections.observableArrayList();

    ClientBo clientBo = (ClientBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CLIENT);

    ChargeBo chargeBo = (ChargeBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CHARGE);


    public void clientIDOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String cid = txtClientId.getText();

        String name = clientBo.getClientName(cid);

        txtClientId.setOnKeyPressed(events -> {
            if (events.getCode() == KeyCode.ENTER) {
                lblClientName.setText(name);
                txtChargeId.requestFocus();
            }
        });
    }

    public void chargetxtOnAction(ActionEvent event) throws SQLException {
        String chargeID = txtChargeId.getText();

        try {
            ChargeDTO data = chargeBo.getChargesData(chargeID);
            System.out.println(data);

            txtChargeId.setOnKeyPressed(events -> {
                if (events.getCode() == KeyCode.ENTER) {
                    lblDescription.setText(data.getDescription());
                    lblAmount.setText(String.valueOf(data.getAmount()));
                    btnAddCart.requestFocus();
                }
            });
        }catch (SQLException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {


        }
    }

    public void btnAddOnAction(ActionEvent event) {
        String chargeId = txtChargeId.getText();
        String description = lblDescription.getText();
        double amount = Double.parseDouble(lblAmount.getText());

        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = tblPayCal.getSelectionModel().getSelectedIndex();
                obList.remove(selectedIndex);

                tblPayCal.refresh();
                calculateNetTotal();
            }
        });

        for (int i = 0; i < tblPayCal.getItems().size(); i++) {
            if (txtChargeId.equals(colChargeId.getCellData(i))) {


                ChargeCalculationTm tm = obList.get(i);
                tblPayCal.refresh();

                calculateNetTotal();
                return;

            }
        }

        ChargeCalculationTm tm = new ChargeCalculationTm(chargeId, description, amount, btnRemove);
        obList.add(tm);

        tblPayCal.setItems(obList);
        System.out.println(obList);
        calculateNetTotal();

        txtChargeId.requestFocus();
    }

    private void calculateNetTotal() {

    }
}
