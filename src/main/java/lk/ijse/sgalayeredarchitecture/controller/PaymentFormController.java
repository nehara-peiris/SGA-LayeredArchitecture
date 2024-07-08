package lk.ijse.sgalayeredarchitecture.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sgalayeredarchitecture.dto.PaymentDTO;
import lk.ijse.sgalayeredarchitecture.dto.SalaryDTO;
import lk.ijse.sgalayeredarchitecture.view.tdm.PaymentTm;
import lk.ijse.sgalayeredarchitecture.view.tdm.SalaryTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentFormController implements Initializable {
    @FXML
    private AnchorPane rootNode;
    @FXML
    private TableView<PaymentTm> tblPayment;
    @FXML
    private TableColumn<?, ?> colPayId2;
    @FXML
    private TableColumn<?, ?> colLawyerId2;
    @FXML
    private TableColumn<?, ?> colDate2;
    @FXML
    private TableColumn<?, ?> colAmount2;
    @FXML
    private TableView<SalaryTm>  tblSalary;
    @FXML
    private TableColumn<?, ?>  colLawyerId1;
    @FXML
    private TableColumn<?, ?>  colTotalSalary1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllPayment();
        loadTotalSalary();
    }

    private void setCellValueFactory() {
        colPayId2.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colLawyerId2.setCellValueFactory(new PropertyValueFactory<>("lawyerId"));
        colDate2.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmount2.setCellValueFactory(new PropertyValueFactory<>("amount"));

        colLawyerId1.setCellValueFactory(new PropertyValueFactory<>("lawyerId"));
        colTotalSalary1.setCellValueFactory(new PropertyValueFactory<>("totalSalary"));
    }

    private void loadAllPayment() {
        /*ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

        try{
            List<PaymentDTO> paymentList = PaymentRepo.getAll();
            for (PaymentDTO payment : paymentList) {
                PaymentTm tm = new PaymentTm(
                        payment.getPaymentId(),
                        payment.getLawyerId(),
                        payment.getDate(),
                        payment.getAmount()
                );
                obList.add(tm);
            }
            tblPayment.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }*/
    }

    private void loadTotalSalary() {
        /*ObservableList<SalaryTm> obList = FXCollections.observableArrayList();

        try{
            List<SalaryDTO> salaryList = SalaryRepo.getAll();
            for (SalaryDTO salary : salaryList) {
                SalaryTm tm = new SalaryTm(
                        salary.getLawyerId(),
                        salary.getTotalSalary()
                );
                obList.add(tm);
            }

            tblSalary.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }*/
    }
}
