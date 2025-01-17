package lk.ijse.sgalayeredarchitecture.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
import lk.ijse.sgalayeredarchitecture.bo.custom.CasesBo;
import lk.ijse.sgalayeredarchitecture.dto.CaseDTO;
import lk.ijse.sgalayeredarchitecture.view.tdm.CasesTm;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class CasesFormController implements Initializable {
    @FXML
    private TextField txtLawyerId;
    @FXML
    private TextField txtCaseId;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtClientId;
    @FXML
    private TextField txtDate;
    @FXML
    private TableView<CasesTm> tblCase;
    @FXML
    private TableColumn<?,?> colClientId;
    @FXML
    private TableColumn<?,?> colCaseId;
    @FXML
    private TableColumn<?,?> colDescription;
    @FXML
    private TableColumn<?,?> colDate;
    @FXML
    private TableColumn<?,?> colType;
    @FXML
    private AnchorPane rootNode;
    @FXML
    private BarChart<String, Number> chartCase;
    @FXML
    private CategoryAxis axisCases;
    @FXML
    private NumberAxis axisNoOfCases;
    public JFXButton btnSave;


    CasesBo casesBo = (CasesBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CASES);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllCases();
        setDate();

        keyEventsHandling();
        setCasesBarchart();

        Validations();
        addTextChangeListener(txtDescription);
        addTextChangeListener(txtType);

    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDate.setText(String.valueOf(now));
    }

    private void keyEventsHandling() {
        txtCaseId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtClientId.requestFocus();
            }
        });

        txtClientId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtDescription.requestFocus();
            }
        });

        txtDescription.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtType.requestFocus();
            }
        });

        txtType.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtDate.requestFocus();
            }
        });

        txtDate.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtLawyerId.requestFocus();
            }
        });

        txtLawyerId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnSave.requestFocus();
            }
        });

    }

    private void setCasesBarchart() {
        axisCases.setLabel("Case Type");
        axisNoOfCases.setLabel("Number of Cases");

        try {
            populateBarChart();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void populateBarChart() throws SQLException, ClassNotFoundException {
        chartCase.getData().clear();

        Map<String, Integer> caseTypeCounts = casesBo.getAllCasesToChart();
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        caseTypeCounts.forEach((type, count) -> {
            XYChart.Data<String, Number> data = new XYChart.Data<>(type, count);
            series.getData().add(data);
            data.nodeProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    newValue.setStyle("-fx-bar-fill:#1c7850;");
                }
            });
        });

        chartCase.getData().add(series);
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

            if (textField == txtType) {
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
        txtCaseId.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[CA0-9]")) {
                event.consume();
            }
        });

        txtClientId.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[C0-9]")) {
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
        colCaseId.setCellValueFactory(new PropertyValueFactory<>("caseId"));
        colClientId.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));

    }

    private void loadAllCases() {
        tblCase.getItems().clear();
        try {
            ArrayList<CaseDTO> allCases = casesBo.getAllCases();

            for (CaseDTO c : allCases) {
                tblCase.getItems().add(new CasesTm(c.getCaseId(), c.getDescription(), c.getDate(), c.getType(), c.getClientId()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String caseId = txtCaseId.getText();
        String clientId = txtClientId.getText();
        String description = txtDescription.getText();
        String type = txtType.getText();
        String dateOfCase = txtDate.getText();
        String lawyerId = txtLawyerId.getText();

        if (caseId.isEmpty() || clientId.isEmpty() || description.isEmpty() || type.isEmpty() || dateOfCase.isEmpty() || lawyerId.isEmpty()) {
            if (caseId.isEmpty()) {
                txtCaseId.requestFocus();
                txtCaseId.setStyle("-fx-border-color: red;");
            } else if (clientId.isEmpty()) {
                txtClientId.requestFocus();
                txtClientId.setStyle("-fx-border-color: red;");
            } else if (description.isEmpty()) {
                txtDescription.requestFocus();
                txtDescription.setStyle("-fx-border-color: red;");
            } else if (type.isEmpty()) {
                txtType.requestFocus();
                txtType.setStyle("-fx-border-color: red;");
            } else if (dateOfCase.isEmpty()) {
                txtDate.requestFocus();
                txtDate.setStyle("-fx-border-color: red;");
            } else {
                txtLawyerId.requestFocus();
                txtLawyerId.setStyle("-fx-border-color: red;");
            }
            return;
        }

        Date date = Date.valueOf(dateOfCase);

        try {
            casesBo.saveCase(new CaseDTO(caseId, description, type, date, clientId));
            tblCase.getItems().add(new CasesTm(caseId, description, date, type, clientId));
            setCasesBarchart();
            clearFields();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the case " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtCaseId.clear();
        txtDescription.clear();
        txtDate.clear();
        txtClientId.clear();
        txtType.clear();
        txtLawyerId.clear();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String caseId = txtCaseId.getText();
        String clientId = txtClientId.getText();
        String description = txtDescription.getText();
        String type = txtType.getText();
        String dateOfCase = txtDate.getText();
        String lawyerId = txtLawyerId.getText();

        if (caseId.isEmpty() || clientId.isEmpty() || description.isEmpty() || type.isEmpty() || dateOfCase.isEmpty() || lawyerId.isEmpty()) {
            if (caseId.isEmpty()) {
                txtCaseId.requestFocus();
                txtCaseId.setStyle("-fx-border-color: red;");
            } else if (clientId.isEmpty()) {
                txtClientId.requestFocus();
                txtClientId.setStyle("-fx-border-color: red;");
            } else if (description.isEmpty()) {
                txtDescription.requestFocus();
                txtDescription.setStyle("-fx-border-color: red;");
            } else if (type.isEmpty()) {
                txtType.requestFocus();
                txtType.setStyle("-fx-border-color: red;");
            } else if (dateOfCase.isEmpty()) {
                txtDate.requestFocus();
                txtDate.setStyle("-fx-border-color: red;");
            } else {
                txtLawyerId.requestFocus();
                txtLawyerId.setStyle("-fx-border-color: red;");
            }
            return;
        }

        Date date = Date.valueOf(dateOfCase);

        try {
            casesBo.updateCase(new CaseDTO(caseId, description, type, date, clientId));
            setCasesBarchart();
            clearFields();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the case " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = tblCase.getSelectionModel().getSelectedItem().getCaseId();
        try {
            casesBo.deleteCase(id);

            tblCase.getItems().remove(tblCase.getSelectionModel().getSelectedItem());
            tblCase.getSelectionModel().clearSelection();
            setCasesBarchart();
            clearFields();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the case " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void TableOnClick(MouseEvent mouseEvent) {
        CasesTm casesTm = tblCase.getSelectionModel().getSelectedItem();
        txtCaseId.setText(casesTm.getCaseId());
        txtDescription.setText(casesTm.getDescription());
        txtDate.setText(String.valueOf(casesTm.getDate()));
        txtType.setText(String.valueOf(casesTm.getType()));
        txtClientId.setText(String.valueOf(casesTm.getClientId()));
    }
}
