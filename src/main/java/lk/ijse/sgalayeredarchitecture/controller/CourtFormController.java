package lk.ijse.sgalayeredarchitecture.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import lk.ijse.sgalayeredarchitecture.bo.custom.CourtBo;
import lk.ijse.sgalayeredarchitecture.dto.CourtDTO;
import lk.ijse.sgalayeredarchitecture.dto.SummonDTO;
import lk.ijse.sgalayeredarchitecture.view.tdm.CourtTm;
import lk.ijse.sgalayeredarchitecture.view.tdm.SummonTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourtFormController {
    public JFXButton btnSave;
    @FXML
    private AnchorPane rootNode;
    @FXML
    private TextField txtCourtId;
    @FXML
    private TextField txtLocation;
    @FXML
    private TableView<CourtTm> tblCourt;
    @FXML
    private TableColumn<?, ?> colCourtId;
    @FXML
    private TableColumn<?, ?> colLocation;


    CourtBo courtBo = (CourtBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURT);

    public void initialize(){
        setCellValueFactory();
        loadAllCourts();

        keyEventsHandling();

        Validations();
        addTextChangeListener(txtCourtId);
        addTextChangeListener(txtLocation);

    }

    private void keyEventsHandling() {
        txtCourtId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtLocation.requestFocus();
            }
        });

        txtLocation.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnSave.requestFocus();
            }
        });

    }

    private void addTextChangeListener(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (textField == txtLocation) {
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
        });
    }

    private void Validations() {
        txtCourtId.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[CT0-9]")) {
                event.consume();
            }
        });
    }

    private void setCellValueFactory() {
        colCourtId.setCellValueFactory(new PropertyValueFactory<>("courtId"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
    }

    private void loadAllCourts() {
        tblCourt.getItems().clear();
        try {
            ArrayList<CourtDTO> allCourts = courtBo.getAllCourts();

            for (CourtDTO c : allCourts) {
                tblCourt.getItems().add(new CourtTm(c.getCourtId(), c.getLocation()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction (ActionEvent event){
        String courtId = txtCourtId.getText();
        String location = txtLocation.getText();

        if (courtId.isEmpty() || location.isEmpty()) {

            if (courtId.isEmpty()) {
                txtCourtId.requestFocus();
                txtCourtId.setStyle("-fx-border-color: red;");
            } else {
                txtLocation.requestFocus();
                txtLocation.setStyle("-fx-border-color: red;");
            }
            return;
        }

        try {
            courtBo.saveCourt(new CourtDTO(courtId, location));
            tblCourt.getItems().add(new CourtTm(courtId, location));
            clearFields();
            loadAllCourts();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the court " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtCourtId.setText("");
        txtLocation.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event){
        String courtId = txtCourtId.getText();
        String location = txtLocation.getText();

        if (courtId.isEmpty() || location.isEmpty()) {

            if (courtId.isEmpty()) {
                txtCourtId.requestFocus();
                txtCourtId.setStyle("-fx-border-color: red;");
            } else {
                txtLocation.requestFocus();
                txtLocation.setStyle("-fx-border-color: red;");
            }
            return;
        }

        try {
            courtBo.updateCourt(new CourtDTO(courtId, location));
            clearFields();
            loadAllCourts();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the court " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = tblCourt.getSelectionModel().getSelectedItem().getCourtId();
        try {
            courtBo.deleteCourt(id);

            tblCourt.getItems().remove(tblCourt.getSelectionModel().getSelectedItem());
            tblCourt.getSelectionModel().clearSelection();
            clearFields();
            loadAllCourts();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the court " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void TableOnClick(MouseEvent mouseEvent) {
        CourtTm courtTm = tblCourt.getSelectionModel().getSelectedItem();
        txtCourtId.setText(courtTm.getCourtId());
        txtLocation.setText(courtTm.getLocation());
    }
}
