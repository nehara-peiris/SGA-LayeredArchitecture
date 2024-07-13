package lk.ijse.sgalayeredarchitecture.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import lk.ijse.sgalayeredarchitecture.bo.custom.SummonBo;
import lk.ijse.sgalayeredarchitecture.bo.custom.impl.SummonBoImpl;
import lk.ijse.sgalayeredarchitecture.dao.custom.SummonDAO;
import lk.ijse.sgalayeredarchitecture.dao.custom.impl.SummonDAOImpl;
import lk.ijse.sgalayeredarchitecture.dto.SummonDTO;
import lk.ijse.sgalayeredarchitecture.entity.Summon;
import lk.ijse.sgalayeredarchitecture.view.tdm.SummonTm;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SummonFormController implements Initializable {

    public JFXButton btnSave;
    @FXML
    private AnchorPane rootNode;
    @FXML
    private TextField txtSummonId;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtDefendant;
    @FXML
    private TextField txtLawyerId;
    @FXML
    private TextField txtDate;
    @FXML
    private TableView<SummonTm> tblSummon;
    @FXML
    private TableColumn<?, ?> colSummonId;
    @FXML
    private TableColumn<?, ?> colDescription;
    @FXML
    private TableColumn<?, ?> colDefendant;
    @FXML
    private TableColumn<?, ?> colLawyerId;
    @FXML
    private TableColumn<?, ?> colDate;


    SummonBo summonBo = (SummonBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUMMON);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDate();
        setCellValueFactory();
        loadAllSummons();
        keyEventsHandling();

        Validations();
        addTextChangeListener(txtDescription);
        addTextChangeListener(txtDefendant);

    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDate.setText(String.valueOf(now));
    }

    private void keyEventsHandling() {
        txtSummonId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtDescription.requestFocus();
            }
        });

        txtDescription.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtDefendant.requestFocus();
            }
        });

        txtDefendant.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtLawyerId.requestFocus();
            }
        });

        txtLawyerId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtDate.requestFocus();
            }
        });

        txtDate.setOnKeyPressed(event -> {
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

            if (textField == txtDefendant) {
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
        txtSummonId.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[S0-9]")) {
                event.consume();
            }
        });

        txtLawyerId.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[L0-9]")) {
                event.consume();
            }
        });
    }

    private void setCellValueFactory() {
        colSummonId.setCellValueFactory(new PropertyValueFactory<>("summonId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDefendant.setCellValueFactory(new PropertyValueFactory<>("defendant"));
        colLawyerId.setCellValueFactory(new PropertyValueFactory<>("lawyerId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void loadAllSummons() {
        tblSummon.getItems().clear();
        try {
            ArrayList<SummonDTO> allSummons = summonBo.getAllSummons();

            for (SummonDTO s : allSummons) {
                tblSummon.getItems().add(new SummonTm(s.getSummonId(), s.getDescription(), s.getDefendant(), s.getLawyerId(), s.getDate()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction (ActionEvent event) {
        String summonId = txtSummonId.getText();
        String description = txtDescription.getText();
        String defendant = txtDefendant.getText();
        String lawyerId = txtLawyerId.getText();
        String sDate = txtDate.getText();

        if (summonId.isEmpty() || description.isEmpty() || defendant.isEmpty() || lawyerId.isEmpty() || sDate.isEmpty()) {
            if (summonId.isEmpty()) {
                txtSummonId.requestFocus();
                txtSummonId.setStyle("-fx-border-color: red;");
            } else if (description.isEmpty()) {
                txtDescription.requestFocus();
                txtDescription.setStyle("-fx-border-color: red;");
            } else if (defendant.isEmpty()) {
                txtDefendant.requestFocus();
                txtDefendant.setStyle("-fx-border-color: red;");
            } else if (lawyerId.isEmpty()) {
                txtLawyerId.requestFocus();
                txtLawyerId.setStyle("-fx-border-color: red;");
            } else {
                txtDate.requestFocus();
                txtDate.setStyle("-fx-border-color: red;");
            }
            return;
        }

        Date date = Date.valueOf(sDate);

        try {
            summonBo.saveSummon(new SummonDTO(summonId, description, defendant, lawyerId, date));
            tblSummon.getItems().add(new SummonTm(summonId, description, defendant, lawyerId, date));
            loadAllSummons();
            clearFields();
            setDate();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the summon " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtSummonId.clear();
        txtDescription.clear();
        txtDefendant.clear();
        txtLawyerId.clear();
        txtDate.clear();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event){
        String summonId = txtSummonId.getText();
        String description = txtDescription.getText();
        String defendant = txtDefendant.getText();
        String lawyerId = txtLawyerId.getText();
        Date date = Date.valueOf(txtDate.getText());

        try {
            summonBo.updateSummon(new SummonDTO(summonId, description, defendant, lawyerId, date));
            clearFields();
            loadAllSummons();
            setDate();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the summon " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = tblSummon.getSelectionModel().getSelectedItem().getSummonId();
        try {
            summonBo.deleteSummon(id);

            tblSummon.getItems().remove(tblSummon.getSelectionModel().getSelectedItem());
            tblSummon.getSelectionModel().clearSelection();
            clearFields();
            loadAllSummons();
            setDate();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the summon " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void TableOnClick(MouseEvent mouseEvent) {
        SummonTm summonTm = tblSummon.getSelectionModel().getSelectedItem();
        txtSummonId.setText(summonTm.getSummonId());
        txtDescription.setText(summonTm.getDescription());
        txtDefendant.setText(String.valueOf(summonTm.getDefendant()));
        txtDate.setText(String.valueOf(summonTm.getDate()));
        txtLawyerId.setText(String.valueOf(summonTm.getLawyerId()));
    }
}
