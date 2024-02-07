package Controller;

import Model.EmployeePayslipEntity;
import View.App;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class CreateReportByYearController implements Initializable {

    @FXML
    private DatePicker dateField;
    @FXML
    private Text errorMessage;
    private TextArea textArea;
    @FXML
    private Label weekStartLabel;
    @FXML
    private Text startDate;
    @FXML
    private Label weekEndLabel;
    @FXML
    private Text endDate;
    @FXML
    private Label totalHoursLabel;
    @FXML
    private Text totalHours;
    @FXML
    private Label totalEmployeeLabel;
    @FXML
    private Text totalEmployee;
    @FXML
    private Label totalPayLabel;
    @FXML
    private Text totalPay;
    @FXML
    private Label payRateLabel;
    @FXML
    private Text payRate;
    @FXML
    private Label superRateLabel;
    @FXML
    private Text superRate;
    @FXML
    private Label totalTaxLabel;
    @FXML
    private Text totalTax;
    @FXML
    private Label totalSuperLabel;
    @FXML
    private Text totalSuper;
    @FXML
    private Label netPayLabel;
    @FXML
    private Text netPay;
    @FXML
    private Line line1;
    @FXML
    private Line line2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void logOutEvent(ActionEvent event) {
        App.changeUI("LoginPageScene.fxml");
    }

    @FXML
    private void homeBtnEvent(ActionEvent event) {
        App.changeUI("StaffHomeScene.fxml");
    }

    @FXML
    private void generateBtnEvent(ActionEvent event) {
        
        // get the date
        LocalDate date = dateField.getValue();
        
        // validate the date
        if (date == null) {
            setErrorMessage("Please Choose any Date of the Year");
            enableFields(false);
        } else {
            
            // get first and last day of the month
            LocalDate firstDay = date.withDayOfYear(1);
            LocalDate lastDay = date.withDayOfYear(date.lengthOfYear());

            List<EmployeePayslipEntity> payrollInfo = App.getDatabaseManager().fetchPayslip(java.sql.Date.valueOf(firstDay), java.sql.Date.valueOf(lastDay));
            if (payrollInfo.size() == 0) {
                setErrorMessage("No Payroll Data exist for this Year");
                enableFields(false);
                
            } else {
                
                // set the fields
                startDate.setText(firstDay.toString());
                endDate.setText(lastDay.toString());
                payRate.setText("$ " + EmployeePayslipEntity.getPAYRATE() + "");
                superRate.setText((EmployeePayslipEntity.getSUPERRATE() * 100) + "%");
                
                // initialise the variables
                int te = 0;
                double th = 0.0;
                double tp = 0.0;
                double tt = 0.0;
                double ts = 0.0;
                double np = 0.0;
                int empId = 0;
                
                //populate the variables
                for (EmployeePayslipEntity p : payrollInfo) {
                    th += p.getHoursWorked();
                    tp += p.getTotalPay();
                    tt += p.getTax();
                    ts += p.getSuperAnnuation();
                    np += p.getNetPay();
                    if (empId != p.getEmployeeId()) {
                        te++;
                        empId = p.getEmployeeId();
                    }
                }
                
                // populate the fields
                this.totalEmployee.setText(te + "");
                this.totalHours.setText(th + " Hrs");
                this.totalPay.setText(String.format("$ %.2f",tp));
                this.totalTax.setText(String.format("$ %.2f",tt));
                this.totalSuper.setText(String.format("$ %.2f",ts));
                this.netPay.setText(String.format("$ %.2f",np));
                enableFields(true);
            }
        }
    }

    public void setErrorMessage(String message) {
        this.errorMessage.setText(message);
        this.errorMessage.setVisible(true);
    }

    public void enableFields(boolean effect) {
       line1.setVisible(effect);
       line2.setVisible(effect);
        weekStartLabel.setVisible(effect);
        weekEndLabel.setVisible(effect);
        totalEmployeeLabel.setVisible(effect);
        totalHoursLabel.setVisible(effect);
        totalPayLabel.setVisible(effect);
        totalSuperLabel.setVisible(effect);
        totalTaxLabel.setVisible(effect);
        totalEmployee.setVisible(effect);
        totalHours.setVisible(effect);
        totalPay.setVisible(effect);
        totalSuper.setVisible(effect);
        totalTax.setVisible(effect);
        startDate.setVisible(effect);
        endDate.setVisible(effect);
        payRate.setVisible(effect);
        payRateLabel.setVisible(effect);
        superRate.setVisible(effect);
        superRateLabel.setVisible(effect);
        netPay.setVisible(effect);
        netPayLabel.setVisible(effect);
    }
}
