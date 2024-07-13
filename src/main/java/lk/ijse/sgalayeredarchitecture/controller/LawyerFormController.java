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
import lk.ijse.sgalayeredarchitecture.bo.custom.LawCaseBo;
import lk.ijse.sgalayeredarchitecture.bo.custom.LawyerBo;
import lk.ijse.sgalayeredarchitecture.dto.LawCaseDTO;
import lk.ijse.sgalayeredarchitecture.dto.LawyerDTO;
import lk.ijse.sgalayeredarchitecture.view.tdm.LawCaseTm;
import lk.ijse.sgalayeredarchitecture.view.tdm.LawyerTm;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LawyerFormController implements Initializable {
    public JFXButton btnSave;
    @FXML
    private TextField txtLawyerId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtContact;
    @FXML
    private TextField txtYrsOfExp;
    @FXML
    private TextField txtEmail;
    @FXML
    private TableView<LawyerTm> tblLawyer;
    @FXML
    private TableColumn<?,?> colLawyerID;
    @FXML
    private TableColumn<?,?> colName;
    @FXML
    private TableColumn<?, ?> colYrsOfExp;
    @FXML
    private TableColumn<?, ?> colAddress;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colContact;
    @FXML
    private AnchorPane rootNode;
    LawyerBo lawyerBo = (LawyerBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LAWYER);

    static LawCaseBo lawCaseBo = (LawCaseBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LAWCASE);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllLawyers();
        keyEventsHandling();

        Validations();
        addTextChangeListener(txtName);
        addTextChangeListener(txtAddress);
        addTextChangeListener(txtContact);
        addTextChangeListener(txtEmail);
        addTextChangeListener(txtYrsOfExp);
    }

    private void Validations() {
        txtLawyerId.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[L0-9]")) {
                event.consume();
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

            if (textField == txtAddress) {
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

            if (textField == txtContact) {
                // Check if the new value contains only digits and has a length of 10
                if (!newValue.matches("\\d{0,10}")) {
                    // If it contains non-digits or its length is not 10, prevent typing and display an error message
                    textField.setText(oldValue != null ? oldValue : "");
                    new Alert(Alert.AlertType.ERROR, "Only numbers are allowed.").show();

                }
            }

            if (textField == txtEmail && !newValue.matches("^([A-z])([A-z0-9.]){1,}@([A-z0-9]){1,10}[.]([A-z]){2,5}$")) {
            }

            if (textField == txtYrsOfExp) {
                if (!newValue.matches("\\d{0,2}")) {
                    textField.setText(oldValue != null ? oldValue : "");
                    new Alert(Alert.AlertType.ERROR, "Only 2 numbers are allowed.").show();
                }
            }
        });
    }

    private void keyEventsHandling() {
        txtLawyerId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtName.requestFocus();
            }
        });

        txtName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtYrsOfExp.requestFocus();
            }
        });

        txtYrsOfExp.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtAddress.requestFocus();
            }
        });

        txtAddress.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtEmail.requestFocus();
            }
        });

        txtEmail.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtContact.requestFocus();
            }
        });

        txtContact.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnSave.requestFocus();
            }
        });
    }

    private void setCellValueFactory() {
        colLawyerID.setCellValueFactory(new PropertyValueFactory<>("lawyerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colYrsOfExp.setCellValueFactory(new PropertyValueFactory<>("yrsOfExp"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

    }

    private void loadAllLawyers() {
        tblLawyer.getItems().clear();
        try{
            ArrayList<LawyerDTO> allLawyers = lawyerBo.getAllLawyers();

            for (LawyerDTO l : allLawyers) {
                tblLawyer.getItems().add(new LawyerTm(l.getLawyerId(), l.getName(), l.getYrsOfExp(), l.getAddress(), l.getEmail(), l.getContact()));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction (ActionEvent event) {
        String lawyerId = txtLawyerId.getText();
        String name = txtName.getText();
        String yearsOfExp = txtYrsOfExp.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String contactNo = txtContact.getText();

        if (lawyerId.isEmpty() || name.isEmpty() || yearsOfExp.isEmpty() || address.isEmpty() || email.isEmpty() || contactNo.isEmpty()) {
            if (lawyerId.isEmpty()) {
                txtLawyerId.requestFocus();
                txtLawyerId.setStyle("-fx-border-color: red;");
            } else if (name.isEmpty()) {
                txtName.requestFocus();
                txtName.setStyle("-fx-border-color: red;");
            } else if (yearsOfExp.isEmpty()) {
                txtYrsOfExp.requestFocus();
                txtYrsOfExp.setStyle("-fx-border-color: red;");
            } else if (address.isEmpty()) {
                txtAddress.requestFocus();
                txtAddress.setStyle("-fx-border-color: red;");
            } else if (email.isEmpty()) {
                txtEmail.requestFocus();
                txtEmail.setStyle("-fx-border-color: red;");
            } else {
                txtContact.requestFocus();
                txtContact.setStyle("-fx-border-color: red;");
            }
            return;
        }

        int yrsOfExp = Integer.parseInt(yearsOfExp);
        int contact = Integer.parseInt(contactNo);


        try {
            lawyerBo.saveLawyer(new LawyerDTO(lawyerId, name, yrsOfExp, address, email, contact));
            tblLawyer.getItems().add(new LawyerTm(lawyerId, name, yrsOfExp, address, email, contact));
            clearFields();
            loadAllLawyers();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the lawyer " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtLawyerId.setText("");
        txtName.setText("");
        txtYrsOfExp.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtContact.setText("");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event){
        String lawyerId = txtLawyerId.getText();
        String name = txtName.getText();
        String yearsOfExp = txtYrsOfExp.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String contactNo = txtContact.getText();

        if (lawyerId.isEmpty() || name.isEmpty() || yearsOfExp.isEmpty() || address.isEmpty() || email.isEmpty() || contactNo.isEmpty()) {
            if (lawyerId.isEmpty()) {
                txtLawyerId.requestFocus();
                txtLawyerId.setStyle("-fx-border-color: red;");
            } else if (name.isEmpty()) {
                txtName.requestFocus();
                txtName.setStyle("-fx-border-color: red;");
            } else if (yearsOfExp.isEmpty()) {
                txtYrsOfExp.requestFocus();
                txtYrsOfExp.setStyle("-fx-border-color: red;");
            } else if (address.isEmpty()) {
                txtAddress.requestFocus();
                txtAddress.setStyle("-fx-border-color: red;");
            } else if (email.isEmpty()) {
                txtEmail.requestFocus();
                txtEmail.setStyle("-fx-border-color: red;");
            } else {
                txtContact.requestFocus();
                txtContact.setStyle("-fx-border-color: red;");
            }
            return;
        }

        int yrsOfExp = Integer.parseInt(yearsOfExp);
        int contact = Integer.parseInt(contactNo);


        try {
            lawyerBo.updateLawyer(new LawyerDTO(lawyerId, name, yrsOfExp, address, email, contact));
            clearFields();
            loadAllLawyers();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the lawyer " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = tblLawyer.getSelectionModel().getSelectedItem().getLawyerId();
        try {
            lawyerBo.deleteLawyer(id);

            tblLawyer.getItems().remove(tblLawyer.getSelectionModel().getSelectedItem());
            tblLawyer.getSelectionModel().clearSelection();
            clearFields();
            loadAllLawyers();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the Lawyer " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void TableOnClick(MouseEvent mouseEvent) {
        LawyerTm lawyerTm  = tblLawyer.getSelectionModel().getSelectedItem();
        txtLawyerId.setText(lawyerTm.getLawyerId());
        txtName.setText(lawyerTm.getName());
        txtYrsOfExp.setText(String.valueOf(lawyerTm.getYrsOfExp()));
        txtAddress.setText(lawyerTm.getAddress());
        txtEmail.setText(lawyerTm.getEmail());
        txtContact.setText(String.valueOf(lawyerTm.getContact()));
    }

    public void BtnSendEmailOnAction(ActionEvent event) {
        sendEmail();

    }
    @FXML
    private void sendEmail() {

       String toWhom = txtEmail.getText();
        new Thread(()->{
            try {
                String subject = "Confidential: Detailed Report for Review and Legal Counsel";
                String emailBody = "Dear  Sir/Madam\n" +
                        "I hope this email finds you well.This is a friendly reminder regarding the detailed report we discussed previously. \n" +
                        "I will be sending you the full report and relevant documentation as a PDF attachment. Your review and counsel on this matter would be greatly appreciated.\n" +
                        "If you have any immediate questions or need further information before receiving the report, please feel free to reach out to me.Thank you for your attention to this matter.\n\n\n\n\n" +
                        "Best regards,\n" +
                        "Nehara Peiris";
                String encodedEmailBody = URLEncoder.encode(emailBody, "UTF-8");
                String encodedSubject = URLEncoder.encode(subject, "UTF-8");
                String url = "https://mail.google.com/mail/?view=cm&fs=1&to=" + toWhom + "&body="+encodedEmailBody+"&su="+encodedSubject;
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                System.out.println("An error occurred : "+e.getLocalizedMessage());
            }
        }).start();
    }
}
