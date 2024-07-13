package lk.ijse.sgalayeredarchitecture.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.sgalayeredarchitecture.bo.BOFactory;
import lk.ijse.sgalayeredarchitecture.bo.custom.*;
import lk.ijse.sgalayeredarchitecture.dto.CaseChargeDTO;
import lk.ijse.sgalayeredarchitecture.dto.ChargeDTO;
import lk.ijse.sgalayeredarchitecture.dto.DeedChargeDTO;
import lk.ijse.sgalayeredarchitecture.entity.*;
import lk.ijse.sgalayeredarchitecture.view.tdm.ChargeCalculationTm;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CalculateChargesFormController<T> implements Initializable {

    @FXML
    private JFXButton btnAddCart;
    @FXML
    private Label lblTotal;
    @FXML
    private TextField txtLawyerId;
    @FXML
    private TextField txtClientId;
    @FXML
    private ChoiceBox<String> chbID;
    @FXML
    private Label lblClientName;
    @FXML
    private Label lblDate;
    @FXML
    private ComboBox<String> cmbIDs;
    @FXML
    private TextField txtChargeId;
    @FXML
    private Label lblDescription;
    @FXML
    private Label lblAmount;
    @FXML
    private Label lblPayId;
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


    PaymentBo paymentBo = (PaymentBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    ChargeBo chargeBo = (ChargeBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CHARGE);

    ClientBo clientBo = (ClientBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CLIENT);

    DeedBo deedBo = (DeedBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DEED);

    CasesBo casesBo = (CasesBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CASES);

    CalChargesBo<CaseCharge> calCaseCharge = (CalChargesBo<CaseCharge>) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CALCASECHARGE);

    CalChargesBo<DeedCharge> calDeedCharge = (CalChargesBo<DeedCharge>) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CALDEEDCHARGE);

    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDate();
        getCurrentPaymentID();
        setCellValueFactory();
        keyEventsHandling();
        setValuesToCombo();

        Validations();
    }

    private void Validations() {
        txtLawyerId.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[L0-9]")) {
                event.consume();
            }
        });

        txtClientId.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[C0-9]")) {
                event.consume();
            }
        });

        txtChargeId.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[CH0-9]")) {
                event.consume();
            }
        });
    }
    private void keyEventsHandling() {
        chbID.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                cmbIDs.requestFocus();
            }
        });

        cmbIDs.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtLawyerId.requestFocus();
            }
        });

        txtLawyerId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtClientId.requestFocus();
            }
        });
    }

    private void setCellValueFactory() {
        colChargeId.setCellValueFactory(new PropertyValueFactory<>("chargeId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    private void getCurrentPaymentID() {
        try {
            String currentId = paymentBo.getCurrentPaymentId();
            String nextOrderId = generateNextPayId(currentId);
            lblPayId.setText(nextOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextPayId(String currentId) {
        if (currentId == null) {
            return "P001"; // Starting ID if none exists
        } else {
            int id = Integer.parseInt(currentId.replace("P", ""));
            id++;
            return String.format("P%03d", id);
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
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
        double netTotal = 0;
        for (int i = 0; i < tblPayCal.getItems().size(); i++) {
            netTotal += (double) colAmount.getCellData(i);
        }
        lblTotal.setText(String.valueOf(netTotal));
    }

    public void txtClientIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String cid = txtClientId.getText();

        String name = clientBo.getClientName(cid);

        txtClientId.setOnKeyPressed(events -> {
            if (events.getCode() == KeyCode.ENTER) {
                lblClientName.setText(name);
                txtChargeId.requestFocus();
            }
        });
    }


    public void txtChargeIdOnAction(ActionEvent event) {
        String chargeID = txtChargeId.getText();
        System.out.println("Charge ID entered: " + chargeID);  // Debugging log

        try {
            ChargeDTO chargeDTO = chargeBo.getChargesData(chargeID);
            System.out.println("ChargeDTO: " + chargeDTO);  // Debugging log

            if (chargeDTO != null) {
                lblDescription.setText(chargeDTO.getDescription());
                lblAmount.setText(String.valueOf(chargeDTO.getAmount()));
                btnAddCart.requestFocus();
                System.out.println("Labels updated");  // Debugging log
            } else {
                System.out.println("ChargeDTO is null");  // Debugging log
            }
        } catch (SQLException e) {
            e.printStackTrace();  // More detailed error handling
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  // More detailed error handling
        }
    }


    @FXML
    void btnAddToDBOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String paymentID = lblPayId.getText();
        Date date = Date.valueOf(lblDate.getText());
        String id = cmbIDs.getValue();
        String lawyerID = txtLawyerId.getText();
        String clientID = txtClientId.getText();
        double total = Double.parseDouble(lblTotal.getText());

        var pay = new Payment(paymentID, lawyerID, date, total);

        ArrayList<Object> chargeList = new ArrayList<>();
        ObservableList<ChargeCalculationTm> tblPayCalItems = tblPayCal.getItems();

        if (id.startsWith("D")) {
            for (ChargeCalculationTm item : tblPayCalItems) {
                DeedChargeDTO deedCharge = new DeedChargeDTO(
                        paymentID,
                        id,
                        lawyerID,
                        item.getChargeId(),
                        date,
                        item.getAmount(),
                        clientID
                );
                chargeList.add(deedCharge);
            }

            CalCharges calCharges = new CalCharges(pay, chargeList);
            try {
                boolean isSaved = calDeedCharge.calCharge(calCharges);
                new Alert(Alert.AlertType.INFORMATION, isSaved ? "saved" : "error").show();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            for (ChargeCalculationTm item : tblPayCalItems) {
                CaseChargeDTO caseCharge = new CaseChargeDTO(
                        paymentID,
                        id,
                        lawyerID,
                        item.getChargeId(),
                        date,
                        item.getAmount(),
                        clientID
                );
                chargeList.add(caseCharge);
            }

            CalCharges calCharges = new CalCharges(pay, chargeList);
            try {
                boolean isSaved = calCaseCharge.calCharge(calCharges);
                new Alert(Alert.AlertType.INFORMATION, isSaved ? "saved" : "error").show();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void cmbIDsOnAction(ActionEvent event) {}

    private void setValuesToCombo() {
        chbID.getItems().add("DeedId");
        chbID.getItems().add("CaseId");

        chbID.setOnAction(event -> {
            String selectedType = chbID.getValue();  // Get the selected value

            if (selectedType.equals("DeedId")) {
                try {
                    ObservableList<String> obList = FXCollections.observableArrayList();
                    ArrayList<String> deedIds = deedBo.getAllDeedIds();

                    for (String id : deedIds) {
                        obList.add(id);
                        System.out.println(id);
                    }
                    cmbIDs.setItems(obList);

                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (selectedType.equals("CaseId")) {
                try {
                    ObservableList<String> obList = FXCollections.observableArrayList();
                    ArrayList<String> caseIds = casesBo.getAllCaseIds();

                    for (String id : caseIds) {
                        obList.add(id);
                        System.out.println(id);
                    }

                    cmbIDs.setItems(obList);

                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
