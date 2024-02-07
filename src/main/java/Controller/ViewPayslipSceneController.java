package Controller;

import Model.EmployeePayslipEntity;
import View.App;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;

public class ViewPayslipSceneController implements Initializable {

    @FXML
    private DatePicker payslipDate;
    @FXML
    private Text payslipText;
    @FXML
    private Button viewBtn;
    @FXML
    private Text errorMessage;

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

    @FXML
    private void searchBtnEvent(ActionEvent event) {
        
        // get the date
        LocalDate date = payslipDate.getValue();
        if(date == null)
        {
            catchError("Date is empty");
        }
        else{
            
                    // validate the date
        if (date.getDayOfWeek().toString().equalsIgnoreCase("Monday")) {
            
            // get the paylsip data
            EmployeePayslipEntity p = App.getDatabaseManager().fetchPayslipData(App.getEmployeeId(), java.sql.Date.valueOf(date));
            
            // validate the payslip data
            if (p != null) {
                if(errorMessage.isVisible())
                {
                    errorMessage.setVisible(false);
                }
                payslipText.setText("Payslip - Satrting " + p.getStartingDate().toString() + " Ending " + p.getEndingDate().toString() + " => ");
                payslipText.setVisible(true);
                viewBtn.setVisible(true);
            } else {
                catchError("Payslip for this Week does not exist");
            }

        } else {
            catchError("Choose a Monday Date for Payslip");
        }
            
        }

    }

    @FXML
    private void viewBtnEvent(ActionEvent event) {
        App.changeUI("ViewPayslipsDetailsScene.fxml");
    }

    public void catchError(String message) {
        errorMessage.setText(message);
        errorMessage.setVisible(true);
    }

}
