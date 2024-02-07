package Controller;

import Model.BankEntity;
import Model.EmployeeEntity;
import Model.LoginEntity;
import View.App;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class UpdateEmployeeSceneController implements Initializable {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField contactNumberField;
    @FXML
    private TextField dateOfBirthField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField genderField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField jobTitleField;
    @FXML
    private TextField bankNameField;
    @FXML
    private TextField bsbField;
    @FXML
    private TextField accountNameField;
    @FXML
    private TextField accountNumberField;
    @FXML
    private TextField startDateField;
    @FXML
    private TextField emailField1;
    @FXML
    private TextField passwordField;
    @FXML
    private Text errorMessage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void logOutBtnEvent(ActionEvent event) {
        App.changeUI("LoginPageScene.fxml");
    }

    @FXML
    private void employeePortalBtn(ActionEvent event) {
        if (App.getEmployeeType().equalsIgnoreCase("Employee")) {
            App.changeUI("EmployeeHomeScene.fxml");
        } else {
            App.changeUI("StaffHomeScene.fxml");
        }
    }

    @FXML
    private void updateBtnEvent(ActionEvent event) {

        // get the employee details
        String fname = firstNameField.getText();
        String lname = lastNameField.getText();
        String address = addressField.getText();
        String contactNum = contactNumberField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        //validate the employee details
        if (fname.isBlank()) {
            catchError("First Name can not be blank");
        } else if (lname.isBlank()) {
            catchError("Last Name can not be blank");
        } else if (address.isBlank()) {
            catchError("Address can not be blank");
        } else if (contactNum.isBlank()) {
            catchError("Contact Number can not be blank");
        } else if (email.isBlank()) {
            catchError("Email Address can not be blank");
        } else if (password.isBlank()) {
            catchError("Password can not be blank");
        } else {

            // if the login user is employee
            if (App.getEmployeeType().equalsIgnoreCase("Employee")) {
                App.getDatabaseManager().updateEmployeeDetails(fname, lname, address, contactNum, email, password);
                showSuccessMessage();
            } // if login user is staff
            else if (App.getEmployeeType().equalsIgnoreCase("Staff")) {

                String dateOfbirth = dateOfBirthField.getText();
                String gender = genderField.getText();
                String startDate = startDateField.getText();
                String jobTitle = jobTitleField.getText();
                String employeeType = emailField1.getText();
                String bankName = bankNameField.getText();
                String bsb = bsbField.getText();
                String aname = accountNameField.getText();
                String accNum = accountNumberField.getText();

                // validate the user details
                if (dateOfbirth.isBlank()) {
                    catchError("Date Of Birth can not be blank");

                } else if (gender.isBlank()) {
                    catchError("Gender can not be blank");

                } else if (startDate.isBlank()) {
                    catchError("Start Date can not be blank");

                } else if (jobTitle.isBlank()) {
                    catchError("Job Title can not be blank");

                } else if (employeeType.isBlank()) {
                    catchError("Employee Type can not be blank");

                } else if (bankName.isBlank()) {
                    catchError("Bank Name can not be blank");

                } else if (bsb.isBlank()) {
                    catchError("BSB Number can not be blank");

                } else if (aname.isBlank()) {
                    catchError("Account Name can not be blank");

                } else if (accNum.isBlank()) {
                    catchError("Account Number can not be blank");

                } // validate the employee type is either Staff or Employee
                else if (!employeeType.equalsIgnoreCase("staff") && !employeeType.equalsIgnoreCase("employee")) {
                    catchError("Employee type must be either 'Staff' or 'Employee'");
                } else {

                    // get the dates
                    this.errorMessage.setVisible(false);
                    boolean dobCorrect = false;
                    boolean startDateCorrect = false;
                    java.sql.Date sqlDateOfBirth = null;
                    java.sql.Date sqlStartDate = null;

                    // validate the dates and convert them to sql format
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {

                        java.util.Date utilDate = dateFormat.parse(dateOfbirth);
                        sqlDateOfBirth = new java.sql.Date(utilDate.getTime());
                        dobCorrect = true;
                    } catch (Exception e) {
                        catchError("Date Of Birth format is incorrect. Please use 'YYYY-MM-DD' Format");
                    }
                    try {

                        java.util.Date utilDate = dateFormat.parse(startDate);
                        sqlStartDate = new java.sql.Date(utilDate.getTime());
                        startDateCorrect = true;
                    } catch (Exception e) {
                        catchError("Start Date format is incorrect. Please use 'YYYY-MM-DD' Format");
                    }

                    // if the dates are of correct format then
                    if (dobCorrect && startDateCorrect) {

                        // create a new employee and update its data in the database
                        EmployeeEntity emp = new EmployeeEntity(fname, lname, sqlDateOfBirth, gender, contactNum, address, sqlStartDate, jobTitle, employeeType, email);
                        emp.setEmployeeid(App.getSearchEmployeeID());
                        BankEntity ba = new BankEntity(bankName, bsb, aname, accNum);
                        LoginEntity ld = new LoginEntity(App.getSearchEmployeeID(), email, password);
                        App.getDatabaseManager().updateEmployeeDetails(emp, ba, ld);
                        showSuccessMessage();

                    }
                }
            }
        }
    }

    public void catchError(String message) {
        errorMessage.setText(message);
        errorMessage.setVisible(true);
    }

    public void setFirstNameField(String f) {
        this.firstNameField.setText(f);
    }

    public void setLastNameField(String l) {
        this.lastNameField.setText(l);
    }

    public void setAddressField(String a) {
        this.addressField.setText(a);
    }

    public void setContactNumberField(String c) {
        this.contactNumberField.setText(c);
    }

    public void setDateOfBirthField(LocalDate d) {
        this.dateOfBirthField.setText(d.toString());
    }

    public void setEmailField(String email) {
        this.emailField.setText(email);
    }

    public void setGenderField(String gender) {
        this.genderField.setText(gender);
    }

    public void setJobTitleField(String title) {
        this.jobTitleField.setText(title);
    }

    public void setBankNameField(String bankNum) {
        this.bankNameField.setText(bankNum);
    }

    public void setBsbField(String bsb) {
        this.bsbField.setText(bsb);
    }

    public void setAccountNameField(String accountName) {
        this.accountNameField.setText(accountName);
    }

    public void setAccountNumberField(String accountNum) {
        this.accountNumberField.setText(accountNum);
    }

    public void setStartDateField(String date) {
        this.startDateField.setText(date);
    }

    public void setPasswordField(String pass) {
        this.passwordField.setText(pass);
    }

    public void setEmployeeType(String type) {
        this.emailField1.setText(type);
    }

    public void makeEditable() {
        dateOfBirthField.setEditable(true);
        genderField.setEditable(true);
        startDateField.setEditable(true);
        jobTitleField.setEditable(true);
        emailField1.setEditable(true);
        bankNameField.setEditable(true);
        bsbField.setEditable(true);
        accountNameField.setEditable(true);
        accountNumberField.setEditable(true);
    }

    public void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        // Set the title and message text
        alert.setTitle("GET IT NOW, Payroll System");
        alert.setHeaderText("Success");
        alert.setContentText("Employee Data has been Updated");

        // Add an OK button and handle its action
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {

            }
        });
    }

}
