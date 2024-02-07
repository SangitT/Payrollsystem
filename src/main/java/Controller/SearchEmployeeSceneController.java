package Controller;

import Model.EmployeeEntity;
import View.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SearchEmployeeSceneController implements Initializable {

    @FXML
    private Text employeeText;
    @FXML
    private Button viewBtn;
    @FXML
    private Text errorMessage;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField contactNumberField;
    @FXML
    private Button updateBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    // employee portal event
    @FXML
    private void employeePortalEvent(ActionEvent event) {
        App.changeUI("StaffHomeScene.fxml");
    }

    @FXML
    private void logOutEvent(ActionEvent event) {
        App.changeUI("LoginPageScene.fxml");
    }

    @FXML
    private void searchBtnEvent(ActionEvent event) {
        
        // get employees details
        String fname = firstNameField.getText();
        String lname = lastNameField.getText();
        String contactNum = contactNumberField.getText();
        
        
        // validate employee's details
        if (fname.isBlank()) {
            catchError("First Name can not be blank");
        } else if (lname.isBlank()) {
            catchError("Last Name can not be blank");
        } else if (contactNum.isBlank()) {
            catchError("Contact Number can not be blank");
        } else {
            
            // get the employee's Data
            EmployeeEntity employee = App.getDatabaseManager().searchForEmployeeByName(fname, lname, contactNum);
            if (employee == null) {
                catchError("Employee not Found in the Database");
            } else {
                // if employee is not found
                this.errorMessage.setVisible(false);
                employeeText.setText("Employee Found - FirstName: " + employee.getFirstName() + " LastName: " + employee.getLastName() + " ");
                employeeText.setVisible(true);
                viewBtn.setVisible(true);
                updateBtn.setVisible(true);
                App.setSearchEmployeeID(employee.getEmployeeid());
            }
        }
    }

    // listen for view btn
    @FXML
    private void viewBtnEvent(ActionEvent event) {
        App.changeUI("ViewEmployeeScene.fxml");
    }

    
    // listen for update btn
    @FXML
    private void updateBtnEvent(ActionEvent event) {
        App.changeUI("UpdateEmployeeScene.fxml");
    }

    // catch the error
    public void catchError(String message) {
        this.errorMessage.setText(message);
        this.errorMessage.setVisible(true);
    }
   
}
