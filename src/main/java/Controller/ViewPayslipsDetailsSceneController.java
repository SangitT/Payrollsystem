package Controller;

import View.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class ViewPayslipsDetailsSceneController implements Initializable {

    @FXML
    private TextField fullNameField;
    @FXML
    private TextField dateFromField;
    @FXML
    private TextField dateToField;
    @FXML
    private TextField hoursWorkedField;
    @FXML
    private TextField hourlyRateField;
    @FXML
    private TextField totalField;
    @FXML
    private TextField taxField;
    @FXML
    private TextField netPayField;
    @FXML
    private TextField superField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void employeePortalEvent(ActionEvent event) {
        App.changeUI("EmployeeHomeScene.fxml");
    }

    @FXML
    private void logOutEvent(ActionEvent event) {
        App.changeUI("LoginPageScene.fxml");
    }

    public void setFullNameField(String name) {
        this.fullNameField.setText(name);
    }

    public void setDateFromField(String dateFrom) {
        this.dateFromField.setText(dateFrom);
    }

    public void setDateToField(String dateTo) {
        this.dateToField.setText(dateTo);
    }

    public void setHoursWorkedField(String hoursWorked) {
        this.hoursWorkedField.setText(hoursWorked);
    }

    public void setHourlyRateField(String rate) {
        this.hourlyRateField.setText(rate);
    }

    public void setTotalField(String total) {
        this.totalField.setText(total);
    }

    public void setTaxField(String tax) {
        this.taxField.setText(tax);
    }

    public void setNetPayField(String netPay) {
        this.netPayField.setText(netPay);
    }

    public void setSuperField(String superA) {
        this.superField.setText(superA);
    }
}
