package lk.ijse.sgalayeredarchitecture.controller;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.sgalayeredarchitecture.bo.BOFactory;
import lk.ijse.sgalayeredarchitecture.bo.custom.CasesBo;
import lk.ijse.sgalayeredarchitecture.bo.custom.DeedBo;
import lk.ijse.sgalayeredarchitecture.db.DbConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Map;


public class DashboardFormController implements Initializable {
    public JFXComboBox cmbReports;
    @FXML
    private Label lblUserId;
    @FXML
    private Label lblUsername;
    @FXML
    private AnchorPane rootNode;
    @FXML
    private AnchorPane mainNode;
    @FXML
    private BarChart<String, Number> chartCase;
    @FXML
    private CategoryAxis axisCases;
    @FXML
    private NumberAxis axisNoOfCases;
    @FXML
    private BarChart<String, Number> chartDeeds;
    @FXML
    private CategoryAxis axisDeeds;
    @FXML
    private NumberAxis axisNoOfDeeds;
    public Label lblDate;
    public Label lblTime;

    CasesBo casesBo = (CasesBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CASES);

    DeedBo deedBo = (DeedBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DEED);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCasesBarchart();
        setDeedBarchart();
        setUserDetail();
        setDate();
        setTime();

        setValuesToCombo();

        cmbReports.setStyle("-fx-prompt-text-fill: white");

    }

    private void setValuesToCombo() {
        cmbReports.getItems().add("Assigned Work");
        cmbReports.getItems().add("Salary Details");
    }

    public void setUserDetail(){
        lblUserId.setText("");
        lblUsername.setText("");
    }

    private void setCasesBarchart() {
        axisCases.setLabel("Case Type");
        axisNoOfCases.setLabel("Number of Cases");

        try {
            populateCaseBarChart();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void populateCaseBarChart() throws SQLException, ClassNotFoundException {
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

    private void setDeedBarchart() {
        axisDeeds.setLabel("Deed Type");
        axisNoOfDeeds.setLabel("Number of Deeds");

        try {
            populateDeedBarChart();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void populateDeedBarChart() throws SQLException, ClassNotFoundException {
        chartDeeds.getData().clear();

        Map<String, Integer> deedTypeCounts = deedBo.getAllDeedToChart();
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

        chartDeeds.getData().add(series);
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    public void setTime() {

        new Thread(() -> {
            while (true) {
                try {
                    String formattedTime = java.time.LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
                    Platform.runLater(() -> lblTime.setText(formattedTime));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @FXML
    void btnClientsOnAction(ActionEvent event) {
        try {
            AnchorPane clientForm = FXMLLoader.load(getClass().getResource("/lk/ijse/sgalayeredarchitecture/ClientForm.fxml"));
            rootNode.getChildren().add(clientForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void btnLawyersOnAction(ActionEvent event) throws IOException {
        try {
            AnchorPane clientForm = FXMLLoader.load(getClass().getResource("/lk/ijse/sgalayeredarchitecture/LawyerForm.fxml"));
            rootNode.getChildren().add(clientForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnDeedsOnAction(ActionEvent event) throws IOException {
        try {
            AnchorPane clientForm = FXMLLoader.load(getClass().getResource("/lk/ijse/sgalayeredarchitecture/DeedForm.fxml"));
            rootNode.getChildren().add(clientForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnJudgesOnAction(ActionEvent event) throws IOException {
        try {
            AnchorPane clientForm = FXMLLoader.load(getClass().getResource("/lk/ijse/sgalayeredarchitecture/JudgeForm.fxml"));
            rootNode.getChildren().add(clientForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void btnCasesOnAction(ActionEvent event) throws IOException {
        try {
            AnchorPane clientForm = FXMLLoader.load(getClass().getResource("/lk/ijse/sgalayeredarchitecture/CasesForm.fxml"));
            rootNode.getChildren().add(clientForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void btnCourtsOnAction(ActionEvent event) throws IOException {
        try {
            System.out.println("Loading FXML: " + getClass().getResource("/lk/ijse/sgalayeredarchitecture/CourtForm.fxml"));

            AnchorPane courtForm = FXMLLoader.load(getClass().getResource("/lk/ijse/sgalayeredarchitecture/CourtForm.fxml"));
            rootNode.getChildren().add(courtForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void btnSummonsOnAction(ActionEvent event) throws IOException {
        try {
            AnchorPane clientForm = FXMLLoader.load(getClass().getResource("/lk/ijse/sgalayeredarchitecture/SummonForm.fxml"));
            rootNode.getChildren().add(clientForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnChargesOnAction(ActionEvent event) throws IOException {
        try {
            AnchorPane clientForm = FXMLLoader.load(getClass().getResource("/lk/ijse/sgalayeredarchitecture/ChargeForm.fxml"));
            rootNode.getChildren().add(clientForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSalaryOnAction(ActionEvent event) throws IOException {
        try {
            AnchorPane clientForm = FXMLLoader.load(getClass().getResource("/lk/ijse/sgalayeredarchitecture/PaymentForm.fxml"));
            rootNode.getChildren().add(clientForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        try {
            AnchorPane clientForm = FXMLLoader.load(getClass().getResource("/lk/ijse/sgalayeredarchitecture/LoginForm.fxml"));
            mainNode.getChildren().add(clientForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        try {
            AnchorPane clientForm = FXMLLoader.load(getClass().getResource("/lk/ijse/sgalayeredarchitecture/DashboardForm.fxml"));
            mainNode.getChildren().add(clientForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cmbReportsOnAction(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
        String selectedItem = (String) cmbReports.getSelectionModel().getSelectedItem();

        if ("Assigned Work".equals(selectedItem)){
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/lk/ijse/sgalayeredarchitecture/reports/AssignedWorkDetails.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        }

        if ("Salary Details".equals(selectedItem)) {
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/lk/ijse/sgalayeredarchitecture/reports/SalaryDetail.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        }
    }
}