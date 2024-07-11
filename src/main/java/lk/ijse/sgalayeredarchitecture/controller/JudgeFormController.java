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
import lk.ijse.sgalayeredarchitecture.bo.custom.JudgeBo;
import lk.ijse.sgalayeredarchitecture.dto.DeedDTO;
import lk.ijse.sgalayeredarchitecture.dto.JudgeDTO;
import lk.ijse.sgalayeredarchitecture.view.tdm.DeedTm;
import lk.ijse.sgalayeredarchitecture.view.tdm.JudgeTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JudgeFormController {
    public JFXButton btnSave;
    @FXML
    private TextField txtJudgeId;
    @FXML
    private TextField txtCourtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtYrsOfExp;
    public TableView<JudgeTm> tblJudge;
    @FXML
    private TableColumn<?, ?> colJudgeId;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colCourtId;
    @FXML
    private TableColumn<?, ?> colYrsOfExp;
    @FXML
    private AnchorPane rootNode;


    JudgeBo judgeBo = (JudgeBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.JUDGE);

   public void initialize(){
        setCellValueFactory();
        loadAllJudges();

       keyEventsHandling();

       Validations();
       addTextChangeListener(txtName);
       addTextChangeListener(txtCourtId);
       addTextChangeListener(txtYrsOfExp);

   }

    private void keyEventsHandling() {
        txtJudgeId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtName.requestFocus();
            }
        });

        txtName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtCourtId.requestFocus();
            }
        });

        txtCourtId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtYrsOfExp.requestFocus();
            }
        });

        txtYrsOfExp.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnSave.requestFocus();
            }
        });

    }

    private void addTextChangeListener(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (textField == txtName) {
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

            if (textField == txtCourtId && !newValue.matches("^CT.*$")) {
            }

            if (textField == txtYrsOfExp) {
                if (!newValue.matches("\\d{0,2}")) {
                    textField.setText(oldValue != null ? oldValue : "");
                    new Alert(Alert.AlertType.ERROR, "Only 2 numbers are allowed.");
                }
            }
        });
    }

    private void Validations() {
        txtJudgeId.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[J0-9]")) {
                event.consume();
            }
        });

        txtCourtId.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[CT0-9]")) {
                event.consume();
            }
        });



    }

    private void setCellValueFactory() {
        colJudgeId.setCellValueFactory(new PropertyValueFactory<>("judgeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCourtId.setCellValueFactory(new PropertyValueFactory<>("courtId"));
        colYrsOfExp.setCellValueFactory(new PropertyValueFactory<>("yrsOfExp"));
    }

    private void loadAllJudges() {
        tblJudge.getItems().clear();
        try{
            ArrayList<JudgeDTO> allJudges = judgeBo.getAllJudges();

            for (JudgeDTO j : allJudges) {
                tblJudge.getItems().add(new JudgeTm(j.getJudgeId(), j.getName(), j.getCourtId(), j.getYrsOfExp()));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction (ActionEvent event)  {
        String judgeId = txtJudgeId.getText();
        String name = txtName.getText();
        String courtId = txtCourtId.getText();
        String yearsOfExp = txtYrsOfExp.getText();

        if (judgeId.isEmpty() || name.isEmpty() || courtId.isEmpty() || yearsOfExp.isEmpty()) {
            if (judgeId.isEmpty()) {
                txtJudgeId.requestFocus();
                txtJudgeId.setStyle("-fx-border-color: red;");
            } else if (name.isEmpty()) {
                txtName.requestFocus();
                txtName.setStyle("-fx-border-color: red;");
            } else if (courtId.isEmpty()) {
                txtName.requestFocus();
                txtName.setStyle("-fx-border-color: red;");
            } else {
                txtYrsOfExp.requestFocus();
                txtYrsOfExp.setStyle("-fx-border-color: red;");
            }
            return;
        }

        int yrsOfExp = Integer.parseInt(yearsOfExp);

        try{
            judgeBo.saveJudge(new JudgeDTO(judgeId, name, courtId, yrsOfExp));
            tblJudge.getItems().add(new JudgeTm(judgeId, name,courtId, yrsOfExp));
            clearFields();
            loadAllJudges();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtJudgeId.setText("");
        txtName.setText("");
        txtCourtId.setText("");
        txtYrsOfExp.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event){
        String judgeId = txtJudgeId.getText();
        String name = txtName.getText();
        String courtId = txtCourtId.getText();
        String yearsOfExp = txtYrsOfExp.getText();

        if (judgeId.isEmpty() || name.isEmpty() || courtId.isEmpty() || yearsOfExp.isEmpty()) {
            if (judgeId.isEmpty()) {
                txtJudgeId.requestFocus();
                txtJudgeId.setStyle("-fx-border-color: red;");
            } else if (name.isEmpty()) {
                txtName.requestFocus();
                txtName.setStyle("-fx-border-color: red;");
            } else if (courtId.isEmpty()) {
                txtName.requestFocus();
                txtName.setStyle("-fx-border-color: red;");
            } else {
                txtYrsOfExp.requestFocus();
                txtYrsOfExp.setStyle("-fx-border-color: red;");
            }
            return;
        }

        int yrsOfExp = Integer.parseInt(yearsOfExp);

        try{
            judgeBo.updateJudge(new JudgeDTO(judgeId, name, courtId, yrsOfExp));
            clearFields();
            loadAllJudges();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = tblJudge.getSelectionModel().getSelectedItem().getJudgeId();
        try {
            judgeBo.deleteJudge(id);

            tblJudge.getItems().remove(tblJudge.getSelectionModel().getSelectedItem());
            tblJudge.getSelectionModel().clearSelection();
            clearFields();
            loadAllJudges();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the Lawyer " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void TableOnClick(MouseEvent mouseEvent) {
        JudgeTm judgeTm = tblJudge.getSelectionModel().getSelectedItem();
        txtJudgeId.setText(judgeTm.getJudgeId());
        txtName.setText(judgeTm.getName());
        txtYrsOfExp.setText(String.valueOf(judgeTm.getYrsOfExp()));
        txtCourtId.setText(judgeTm.getCourtId());
    }
}
