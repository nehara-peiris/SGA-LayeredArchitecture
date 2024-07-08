package lk.ijse.sgalayeredarchitecture.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.sgalayeredarchitecture.bo.custom.ClientBo;
import lk.ijse.sgalayeredarchitecture.dto.ClientDTO;
import lk.ijse.sgalayeredarchitecture.entity.Client;
import lk.ijse.sgalayeredarchitecture.view.tdm.ClientTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientFormController implements Initializable {
    @FXML
    private JFXButton btnSave;
    @FXML
    private TextField txtClientId;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtContact;
    @FXML
    private TextField txtLawyerId;
    @FXML
    private AnchorPane rootNode;
    @FXML
    private TableColumn<?, ?> colClientId;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colAddress;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colContact;
    @FXML
    private TableColumn<?, ?> colLawyerId;
    @FXML
    private TableView<ClientTm> tblClient;

    ClientBo clientBo = (ClientBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CLIENT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllClients();

        keyEventsHandling();

        Validations();
        addTextChangeListener(txtClientId);
        addTextChangeListener(txtName);
        addTextChangeListener(txtAddress);
        addTextChangeListener(txtContact);
        addTextChangeListener(txtEmail);
        addTextChangeListener(txtLawyerId);

    }

    private void keyEventsHandling() {
        txtClientId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtName.requestFocus();
            }
        });

        txtName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtAddress.requestFocus();
            }
        });

        txtAddress.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtContact.requestFocus();
            }
        });

        txtContact.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtEmail.requestFocus();
            }
        });

        txtEmail.setOnKeyPressed(event -> {
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

    private void Validations() {
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
                if (!newValue.matches("\\d{0,10}")) {
                    textField.setText(oldValue != null ? oldValue : "");
                    new Alert(Alert.AlertType.ERROR, "Please enter 10 integer numbers.");
                }
            }

            if (textField == txtEmail && !newValue.matches("^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$")) {
            }

            if (textField == txtContact) {
                if (!newValue.matches("\\d{0,10}")) {
                    textField.setText(oldValue != null ? oldValue : "");
                    new Alert(Alert.AlertType.ERROR, "Please enter 10 integer numbers.");
                }
            }

        });
    }

    private void setCellValueFactory() {
        colClientId.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colLawyerId.setCellValueFactory(new PropertyValueFactory<>("LawyerID"));
    }

    private void loadAllClients() {
        tblClient.getItems().clear();
        try {
            ArrayList<ClientDTO> allClients = clientBo.getAllClients();

            for (ClientDTO c : allClients) {
                tblClient.getItems().add(new ClientTm(c.getClientId(), c.getName(), c.getAddress(), c.getEmail(), c.getContact(), c.getLawyerId()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction (ActionEvent event)  {
        String clientId = txtClientId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String contactNo = txtContact.getText();
        String lawyerId = txtLawyerId.getText();

        if (clientId.isEmpty() || name.isEmpty() || address.isEmpty() || email.isEmpty() || contactNo.isEmpty() || lawyerId.isEmpty()) {
            if (clientId.isEmpty()) {
                txtClientId.requestFocus();
                txtClientId.setStyle("-fx-border-color: red;");
            } else if (name.isEmpty()) {
                txtName.requestFocus();
                txtName.setStyle("-fx-border-color: red;");
            } else if (address.isEmpty()) {
                txtAddress.requestFocus();
                txtAddress.setStyle("-fx-border-color: red;");
            } else if (email.isEmpty()) {
                txtEmail.requestFocus();
                txtEmail.setStyle("-fx-border-color: red;");
            }else if (contactNo.isEmpty()) {
                txtContact.requestFocus();
                txtContact.setStyle("-fx-border-color: red;");
            } else {
                txtLawyerId.requestFocus();
                txtLawyerId.setStyle("-fx-border-color: red;");
            }
            return;
        }

        int contact = Integer.parseInt(contactNo);

        Client client = new Client(clientId, name, address, email, contact, lawyerId);

        try{
            boolean isSaved = clientBo.saveClient(client);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Client saved successfully!").show();
                sendEmailToClient();
                clearFields();
                loadAllClients();
                txtClientId.requestFocus();
            }
        }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtClientId.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtContact.clear();
        txtLawyerId.clear();
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtClientId.getText();

        Client client = clientBo.searchClientById(id);
        if (client != null) {
            txtClientId.setText(client.getClientId());
            txtName.setText(client.getName());
            txtAddress.setText(client.getAddress());
            txtEmail.setText(client.getEmail());
            txtContact.setText(String.valueOf(client.getContact()));
            txtLawyerId.setText(String.valueOf(client.getLawyerId()));
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Client not found!").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event){
        String clientId = txtClientId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String contactNo = txtContact.getText();
        String lawyerId = txtLawyerId.getText();

        if (clientId.isEmpty() || name.isEmpty() || address.isEmpty() || email.isEmpty() || contactNo.isEmpty() || lawyerId.isEmpty()) {
            if (clientId.isEmpty()) {
                txtClientId.requestFocus();
                txtClientId.setStyle("-fx-border-color: red;");
            } else if (name.isEmpty()) {
                txtName.requestFocus();
                txtName.setStyle("-fx-border-color: red;");
            } else if (address.isEmpty()) {
                txtAddress.requestFocus();
                txtAddress.setStyle("-fx-border-color: red;");
            } else if (email.isEmpty()) {
                txtEmail.requestFocus();
                txtEmail.setStyle("-fx-border-color: red;");
            }else if (contactNo.isEmpty()) {
                txtContact.requestFocus();
                txtContact.setStyle("-fx-border-color: red;");
            } else {
                txtLawyerId.requestFocus();
                txtLawyerId.setStyle("-fx-border-color: red;");
            }
            return;
        }

        int contact = Integer.parseInt(contactNo);

        Client client = new Client(clientId, name, address, email, contact, lawyerId);

        try{
            boolean isUpdated = clientBo.updateClient(client);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Client updated successfully!").show();
                clearFields();
                loadAllClients();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtClientId.getText();

        try {
            boolean isDeleted = clientBo.deleteClient(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Client deleted!").show();
                loadAllClients();
                txtClientId.requestFocus();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void TableOnClick(MouseEvent mouseEvent) {
        ClientTm clientTm = tblClient.getSelectionModel().getSelectedItem();
        txtClientId.setText(clientTm.getClientId());
        txtName.setText(clientTm.getName());
        txtAddress.setText(clientTm.getAddress());
        txtEmail.setText(clientTm.getEmail());
        txtContact.setText(String.valueOf(clientTm.getContact()));
        txtLawyerId.setText(clientTm.getLawyerID());
    }

    public void BtnSendEmailOnAction(ActionEvent event) {
        sendEmail();

    }

    @FXML
    private void sendEmail() {

    }

    private void sendEmailToClient() {
        /*String toWhom = txtEmail.getText();
        new Thread(()->{
            try {
                String subject = "Confidential: Detailed Report for Review and Legal Counsel";
                String emailBody = "Dear  Sir/Madam\n" +
                        "You are saved as client in Shiranthi Gunawardena Associates.\n" +
                        "Thank you for your trust and cooperation.\n" +
                        "Please do not reply to this. This is just for verify you.\n" +
                        "Nehara Peiris";
                String encodedEmailBody = URLEncoder.encode(emailBody, "UTF-8");
                String encodedSubject = URLEncoder.encode(subject, "UTF-8");
                String url = "https://mail.google.com/mail/?view=cm&fs=1&to=" + toWhom + "&body="+encodedEmailBody+"&su="+encodedSubject;
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                System.out.println("An error occurred : "+e.getLocalizedMessage());
            }
        }).start();*/
    }
}
