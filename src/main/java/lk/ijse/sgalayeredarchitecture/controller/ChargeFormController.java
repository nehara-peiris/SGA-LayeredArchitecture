package lk.ijse.sgalayeredarchitecture.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sgalayeredarchitecture.bo.BOFactory;
import lk.ijse.sgalayeredarchitecture.bo.custom.ChargeBo;
import lk.ijse.sgalayeredarchitecture.dto.ChargeDTO;
import lk.ijse.sgalayeredarchitecture.view.tdm.ChargeTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ChargeFormController implements Initializable {
    public JFXButton btnSave;
    @FXML
    private AnchorPane rootNode;
    @FXML
    private TableView<ChargeTm> tblCharge;
    @FXML
    private TextField txtChargeId;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtAmount;
    @FXML
    private TableColumn<?, ?> colChargeId;
    @FXML
    private TableColumn<?, ?> colDescription;
    @FXML
    private TableColumn<?, ?> colAmount;


    ChargeBo chargeBo = (ChargeBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CHARGE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllCharges();

        keyEventsHandling();

        Validations();
        addTextChangeListener(txtDescription);
        addTextChangeListener(txtAmount);

    }

    private void keyEventsHandling() {
        txtChargeId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtDescription.requestFocus();
            }
        });

        txtDescription.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtAmount.requestFocus();
            }
        });

        txtAmount.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnSave.requestFocus();
            }
        });
    }

    private void addTextChangeListener(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (textField == txtDescription) {
                if (!newValue.isEmpty()) {
                    if (!Character.isUpperCase(newValue.charAt(0))) {
                        textField.setText(oldValue != null ? oldValue : "");
                    } else {
                        String correctedValue = Character.toUpperCase(newValue.charAt(0)) + newValue.substring(1);
                        if (!newValue.equals(correctedValue)) {
                            textField.setText(correctedValue);
                        }
                    }
                }
            }

            if (textField == txtAmount && !newValue.matches("^-?\\d+(\\.\\d+)?$")) {
            }

        });
    }

    private void Validations() {
        txtChargeId.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[CH0-9]")) {
                event.consume();
            }
        });
    }


    private void setCellValueFactory() {
        colChargeId.setCellValueFactory(new PropertyValueFactory<>("ChargeId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
    }

    private void loadAllCharges() {
        ObservableList<ChargeTm> obList = FXCollections.observableArrayList();

        try {
            List<ChargeDTO> chargeList = chargeBo.getAllCharges();
            for (ChargeDTO charge : chargeList) {
                ChargeTm tm = new ChargeTm(
                        charge.getChargeId(),
                        charge.getDescription(),
                        charge.getAmount()
                );

                obList.add(tm);
            }

            tblCharge.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String chargeId = txtChargeId.getText();
        String description = txtDescription.getText();
        String crgAmount = txtAmount.getText();

        if (chargeId.isEmpty() || description.isEmpty() || crgAmount.isEmpty()) {
            if (chargeId.isEmpty()) {
                txtChargeId.requestFocus();
                txtChargeId.setStyle("-fx-border-color: red;");
            } else if (description.isEmpty()) {
                txtDescription.requestFocus();
                txtDescription.setStyle("-fx-border-color: red;");
            } else {
                txtAmount.requestFocus();
                txtAmount.setStyle("-fx-border-color: red;");
            }
            return;
        }

        double amount = Double.parseDouble(crgAmount);

        ChargeDTO charge = new ChargeDTO(chargeId, description, amount);

        try {
            boolean isSaved = chargeBo.saveCharge(charge);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "charge saved!").show();
                loadAllCharges();
                clearFields();
                txtChargeId.requestFocus();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtChargeId.clear();
        txtDescription.clear();
        txtAmount.clear();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String chargeId = txtChargeId.getText();
        String description = txtDescription.getText();
        String crgAmount = txtAmount.getText();

        if (chargeId.isEmpty() || description.isEmpty() || crgAmount.isEmpty()) {
            if (chargeId.isEmpty()) {
                txtChargeId.requestFocus();
                txtChargeId.setStyle("-fx-border-color: red;");
            } else if (description.isEmpty()) {
                txtDescription.requestFocus();
                txtDescription.setStyle("-fx-border-color: red;");
            } else {
                txtAmount.requestFocus();
                txtAmount.setStyle("-fx-border-color: red;");
            }
            return;
        }

        double amount = Double.parseDouble(crgAmount);

        ChargeDTO charge = new ChargeDTO(chargeId, description, amount);

        try {
            boolean isSaved = chargeBo.updateCharge(charge);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "charge updated!").show();
                loadAllCharges();
                clearFields();
                txtChargeId.requestFocus();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtChargeId.getText();

        try {
            boolean isDeleted = chargeBo.deleteCharge(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "charge deleted!").show();
                loadAllCharges();
                clearFields();
                txtChargeId.requestFocus();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCasesOnAction(ActionEvent event) throws IOException {
        try {
            AnchorPane caseCharge = FXMLLoader.load(getClass().getResource("/lk/ijse/sgalayeredarchitecture/CaseChargeForm.fxml"));
            rootNode.getChildren().add(caseCharge);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnDeedsOnAction(ActionEvent event) throws IOException {
        try {
            AnchorPane deedCharge = FXMLLoader.load(getClass().getResource("/lk/ijse/sgalayeredarchitecture/DeedChargeForm.fxml"));
            rootNode.getChildren().add(deedCharge);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void TableOnClick(MouseEvent mouseEvent) {
        ChargeTm chargeTm = tblCharge.getSelectionModel().getSelectedItem();
        txtChargeId.setText(chargeTm.getChargeId());
        txtDescription.setText(chargeTm.getDescription());
        txtAmount.setText(String.valueOf(chargeTm.getAmount()));
    }

    public void btnCalChargeOnAction(ActionEvent event) throws IOException {
        try {
            AnchorPane calDeedCharge = FXMLLoader.load(getClass().getResource("/lk/ijse/sgalayeredarchitecture/CalculateChargesForm.fxml"));
            rootNode.getChildren().add(calDeedCharge);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}