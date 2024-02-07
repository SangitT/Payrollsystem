package Controller;

import Model.EmployeePayslipEntity;
import View.App;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginWorkHoursSceneController implements Initializable {

    @FXML
    private TextField hoursField;
    @FXML
    private DatePicker workDateFrom;
    @FXML
    private DatePicker workDateTo;
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
    private void submitBtnEvent(ActionEvent event) {

        // get the dates
        LocalDate start = workDateFrom.getValue();
        LocalDate end = workDateTo.getValue();

        //validate the dates
        if (start == null || end == null) {
            errorMessage.setText("Date is empty");
            errorMessage.setVisible(true);
        } else {

            // compare the dates and check if the range is positive
            int compare = start.compareTo(end);
            if (compare < 0) {

                //validate the start date is monday and end date is sunday
                if (start.getDayOfWeek().toString().equalsIgnoreCase("MONDAY")) {
                    if (end.getDayOfWeek().toString().equalsIgnoreCase("SUNDAY")) {
                        long diff = ChronoUnit.DAYS.between(start, end);

                        // validate that the date are exactly one week apart
                        if (diff == 6) {
                            try {

                                // validate the hours and makesure the hours are in the range of 0 to 60
                                double hours = Double.parseDouble(hoursField.getText());
                                if (hours <= 60 && hours >= 0) {
                                    errorMessage.setVisible(false);

                                    // create a payslip and put it in the database
                                    EmployeePayslipEntity payslip = new EmployeePayslipEntity(App.getEmployeeId(), java.sql.Date.valueOf(start), java.sql.Date.valueOf(end), hours);
                                    boolean dataAdded = App.getDatabaseManager().addNewPayslipData(payslip);
                                    if (dataAdded) {
                                        workDateFrom.setValue(null);
                                        workDateTo.setValue(null);
                                        hoursField.setText("");
                                        showSuccessMessage();
                                    } else {
                                        
                                        catchError("The Week you have provided already exist in the database, please choose another week");
                                       
                                    }

                                } else {
                                    
                                    catchError("Hours must be between 0 to 60");
                                    
                                    hoursField.setText("");
                                }

                            } catch (Exception e) {
                               catchError("Hours must be between 0 to 60");
                                    
                                    hoursField.setText("");
                            }

                        } else {
                            catchError("Please choose exactly one week starting from Monday to Sunday");
     

                        }

                    } else {
                        catchError("Please choose Sunday as 'Ending' date");
                       
                    }

                } else {
                    catchError("Please choose Monday as 'Starting' date");
                  
                }

            } else {
                catchError("Invalid Date. Monday date can not be greater than Sunday date!");
               
            }
        }

    }

    private void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        // Set the title and message text
        alert.setTitle("Payroll System");
        alert.setHeaderText("Success");
        alert.setContentText("Work hours has been added successfully");

        // Add an OK button and handle its action
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {

            }
        });
    }
    
    private void catchError(String message) {
        errorMessage.setText(message);
        errorMessage.setVisible(true);
    }

}
