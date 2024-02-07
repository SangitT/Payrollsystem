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

public class CreateReportByWeekController implements Initializable {

    @FXML
    private DatePicker dateField;
    @FXML
    private Text errorMessage;
    @FXML
    private Text startDate;
    @FXML
    private Text endDate;
    @FXML
    private Text totalHours;
    @FXML
    private Text totalEmployee;
    @FXML
    private Text totalPay;
    @FXML
    private Text payRate;
    @FXML
    private Text superRate;
    @FXML
    private Text totalTax;
    @FXML
    private Text totalSuper;
    @FXML
    private Text netPay;
    @FXML
    private Label weekStartLabel;
    @FXML
    private Label weekEndLabel;
    @FXML
    private Label totalHoursLabel;
    @FXML
    private Label totalEmployeeLabel;
    @FXML
    private Label totalPayLabel;
    @FXML
    private Label payRateLabel;
    @FXML
    private Label superRateLabel;
    @FXML
    private Label totalTaxLabel;
    @FXML
    private Label totalSuperLabel;
    @FXML
    private Label netPayLabel;
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
        LocalDate localdate = dateField.getValue();
        
        //validate the date
        if (localdate == null) {
            catchError("Payslip's Monday Date is required for the week");
            enableFields(false);
            
        } else if (!localdate.getDayOfWeek().toString().equalsIgnoreCase("Monday")) {
            catchError("Only Payslip's Monday Date is required for the week");
            enableFields(false);
            
        } else {
            
            // get the payroll inforamtion
            List<EmployeePayslipEntity> payrollInfo = App.getDatabaseManager().fetchPayslip(java.sql.Date.valueOf(localdate));
            if (payrollInfo.size() == 0) {
                catchError("No Payroll Data found for this week");
                enableFields(false);
               
            } else {
                
                // set the fields 
                startDate.setText(payrollInfo.get(0).getStartingDate().toString());
                endDate.setText(payrollInfo.get(0).getEndingDate().toString());
                payRate.setText("$ " + EmployeePayslipEntity.getPAYRATE() + "");
                superRate.setText((EmployeePayslipEntity.getSUPERRATE() * 100) + "%");
                
                // initialize the variables
                int te = payrollInfo.size();
                double th = 0.0;
                double tp = 0.0;
                double tt = 0.0;
                double ts = 0.0;
                double np = 0.0;
                
                // fill the variables
                for (EmployeePayslipEntity p : payrollInfo) {
                    th += p.getHoursWorked();
                    tp += p.getTotalPay();
                    tt += p.getTax();
                    ts += p.getSuperAnnuation();
                    np += p.getNetPay();
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

    public void catchError(String message) {
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
