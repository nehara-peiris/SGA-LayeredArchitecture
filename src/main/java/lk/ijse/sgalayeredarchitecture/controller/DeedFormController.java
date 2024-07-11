package lk.ijse.sgalayeredarchitecture.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
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
import lk.ijse.sgalayeredarchitecture.bo.custom.DeedBo;
import lk.ijse.sgalayeredarchitecture.dto.DeedDTO;
import lk.ijse.sgalayeredarchitecture.view.tdm.DeedTm;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeedFormController implements Initializable {

    public JFXButton btnSave;
    @FXML
    private AnchorPane rootNode;
    @FXML
    private TableView<DeedTm> tblDeed;
    @FXML
    private TableColumn<?,?> colDeedId;
    @FXML
    private TableColumn<?,?> colClientId;
    @FXML
    private TableColumn<?, ?> colDescription;
    @FXML
    private TableColumn<?, ?> colType;
    @FXML
    private TableColumn<?, ?> colLawyerId;
    @FXML
    private TableColumn<?, ?> colDate;
    @FXML
    private TextField txtDeedId;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtLawyerId;
    @FXML
    private TextField txtClientId;
    @FXML
    private TextField txtDate;
    @FXML
    private BarChart<String, Number> chartDeeds;
    @FXML
    private CategoryAxis axisDeeds;
    @FXML
    private NumberAxis axisNoOfDeeds;

    DeedBo deedBo = (DeedBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DEED);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllDeeds();
        setDate();

        keyEventsHandling();
        setDeedBarchart();

        Validations();
        addTextChangeListener(txtDeedId);
        addTextChangeListener(txtDescription);
        addTextChangeListener(txtType);

    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDate.setText(String.valueOf(now));
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

    }

    private void addTextChangeListener(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (textField == txtDeedId) {
                if (!newValue.isEmpty()) {
                    if (newValue.charAt(0) != 'D' || !newValue.substring(1).matches("\\d*")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "The deed ID should start with 'D' followed by numbers only");
                        alert.setHeaderText(null);
                        alert.setTitle("Input Error");
                        alert.showAndWait();

                        textField.setText(oldValue != null ? oldValue : "");
                    } else {
                        String correctedValue = 'D' + newValue.substring(1);
                        if (!newValue.equals(correctedValue)) {
                            textField.setText(correctedValue);
                        }
                    }
                }
            }

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

    private void keyEventsHandling() {
        txtDeedId.setOnKeyPressed(event -> {
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

    private void setDeedBarchart() {
        axisDeeds.setLabel("Deed Type");
        axisNoOfDeeds.setLabel("Number of Deeds");

        try {
            populateBarChart();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void populateBarChart() throws SQLException, ClassNotFoundException {
        /*chartDeeds.getData().clear();

        Map<String, Integer> deedTypeCounts = deedBo.getAllToChart();
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        deedTypeCounts.forEach((type, count) -> {
            XYChart.Data<String, Number> data = new XYChart.Data<>(type, count);
            series.getData().add(data);
            data.nodeProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    newValue.setStyle("-fx-bar-fill: #1c7850;");
                }
            });
        });

        chartDeeds.getData().add(series);*/
    }

    private void setCellValueFactory() {
        colDeedId.setCellValueFactory(new PropertyValueFactory<>("deedId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colLawyerId.setCellValueFactory(new PropertyValueFactory<>("lawyerId"));
        colClientId.setCellValueFactory(new PropertyValueFactory<>("clientId"));
    }

    private void loadAllDeeds() {
        tblDeed.getItems().clear();
        try{
            ArrayList<DeedDTO> allDeeds = deedBo.getAllDeeds();

            for (DeedDTO d : allDeeds) {
                tblDeed.getItems().add(new DeedTm(d.getDeedId(), d.getDescription(), d.getType(), d.getDate(), d.getLawyerId(), d.getClientId()));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction (ActionEvent event){
        String deedId = txtDeedId.getText();
        String description = txtDescription.getText();
        String type = txtType.getText();
        String dateOfDeed = txtDate.getText();
        String lawyerId = txtLawyerId.getText();
        String clientId = txtClientId.getText();

        if (deedId.isEmpty() || clientId.isEmpty() || description.isEmpty() || type.isEmpty() || dateOfDeed.isEmpty() || lawyerId.isEmpty()) {
            if (deedId.isEmpty()) {
                txtDeedId.requestFocus();
                txtDeedId.setStyle("-fx-border-color: red;");
            } else if (clientId.isEmpty()) {
                txtClientId.requestFocus();
                txtClientId.setStyle("-fx-border-color: red;");
            } else if (description.isEmpty()) {
                txtDescription.requestFocus();
                txtDescription.setStyle("-fx-border-color: red;");
            } else if (type.isEmpty()) {
                txtType.requestFocus();
                txtType.setStyle("-fx-border-color: red;");
            } else if (dateOfDeed.isEmpty()) {
                txtDate.requestFocus();
                txtDate.setStyle("-fx-border-color: red;");
            } else {
                txtLawyerId.requestFocus();
                txtLawyerId.setStyle("-fx-border-color: red;");
            }
            return;
        }

        Date date = Date.valueOf(dateOfDeed);

        try{
            deedBo.saveDeed(new DeedDTO(deedId, description, type, date, lawyerId, clientId));
            tblDeed.getItems().add(new DeedTm(deedId, description, type, date, lawyerId, clientId));
            clearFields();
            loadAllDeeds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtDeedId.clear();
        txtDescription.clear();
        txtType.clear();
        txtDate.clear();
        txtClientId.clear();
        txtLawyerId.clear();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event){
        String deedId = txtDeedId.getText();
        String description = txtDescription.getText();
        String type = txtType.getText();
        String dateOfDeed = txtDate.getText();
        String lawyerId = txtLawyerId.getText();
        String clientId = txtClientId.getText();

        if (deedId.isEmpty() || clientId.isEmpty() || description.isEmpty() || type.isEmpty() || dateOfDeed.isEmpty() || lawyerId.isEmpty()) {
            if (deedId.isEmpty()) {
                txtDeedId.requestFocus();
                txtDeedId.setStyle("-fx-border-color: red;");
            } else if (clientId.isEmpty()) {
                txtClientId.requestFocus();
                txtClientId.setStyle("-fx-border-color: red;");
            } else if (description.isEmpty()) {
                txtDescription.requestFocus();
                txtDescription.setStyle("-fx-border-color: red;");
            } else if (type.isEmpty()) {
                txtType.requestFocus();
                txtType.setStyle("-fx-border-color: red;");
            } else if (dateOfDeed.isEmpty()) {
                txtDate.requestFocus();
                txtDate.setStyle("-fx-border-color: red;");
            } else {
                txtLawyerId.requestFocus();
                txtLawyerId.setStyle("-fx-border-color: red;");
            }
            return;
        }

        Date date = Date.valueOf(dateOfDeed);

        try {
            deedBo.updateDeed(new DeedDTO(deedId, description, type, date, lawyerId, clientId));
            clearFields();
            loadAllDeeds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = tblDeed.getSelectionModel().getSelectedItem().getDeedId();
        try {
            deedBo.deleteDeed(id);

            tblDeed.getItems().remove(tblDeed.getSelectionModel().getSelectedItem());
            tblDeed.getSelectionModel().clearSelection();
            clearFields();
            loadAllDeeds();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the deed " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void TableOnClick(MouseEvent mouseEvent) {
        DeedTm deedTm = tblDeed.getSelectionModel().getSelectedItem();
        txtDeedId.setText(deedTm.getDeedId());
        txtDescription.setText(deedTm.getDescription());
        txtType.setText(deedTm.getType());
        txtLawyerId.setText(deedTm.getLawyerId());
        txtDate.setText(String.valueOf(deedTm.getDate()));
        txtClientId.setText(deedTm.getClientId());
    }
}

