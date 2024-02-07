package Controller;

import Model.BankEntity;
import Model.EmployeeEntity;
import Model.LoginEntity;
import View.App;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class NewEmployeeSceneController implements Initializable {

    @FXML
    public TextField lnField;
    @FXML
    private TextField addressLine1Field;
    @FXML
    private TextField addressLine2Field;
    @FXML
    private TextField contactNumberField;
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
    private DatePicker startDateField;
    @FXML
    private MenuButton genderMenu;
    @FXML
    private DatePicker dobField;
    @FXML
    private MenuButton employeeTypeMenu;
    String gender = "";
    String employeeType = "";
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmPasswordField;
    @FXML
    private Text errorMessage;
    @FXML
    private TextField fnField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void logOutBtnEvent(ActionEvent event) {
        App.changeUI("LoginPageScene.fxml");
    }

    @FXML
    private void homeBtnEvent(ActionEvent event) {
        App.changeUI("StaffHomeScene.fxml");
    }

    @FXML
    private void addEmployeeEvent(ActionEvent event) {
        
        // get all the details
        String fname = fnField.getText();
        String lname = lnField.getText();
        LocalDate dob = dobField.getValue();
        String gtype = gender;
        String address = addressLine1Field.getText() + " " + addressLine2Field.getText();
        String contactNum = contactNumberField.getText();
        String employeeTy = employeeType;
        LocalDate startDate = startDateField.getValue();
        String jobTitle = jobTitleField.getText();
        String bankName = bankNameField.getText();
        String bsb = bsbField.getText();
        String accountName = accountNameField.getText();
        String accountNumber = accountNumberField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confPassword = confirmPasswordField.getText();
        
        
        // check for empty fields
        if (fname.isBlank()) {
            catchError("FirstName is empty");
        } else if (lname.isBlank()) {
            catchError("LastName is empty");
        } else if (dob == null) {
            catchError("Date of Birth is empty");
        } else if (gtype.isBlank()) {
            catchError("Gender is empty");
        } else if (address.isBlank()) {
            catchError("Address is empty");
        } else if (contactNum.isBlank()) {
            catchError("Contact Number is empty");
        } else if (employeeTy.isBlank() || employeeTy.equalsIgnoreCase("no selection")) {
            catchError("Please select Employee Type");
        } else if (startDate == null) {
            catchError("Start Date is empty");
        } else if (jobTitle.isBlank()) {
            catchError("Job title is empty");
        } else if (bankName.isBlank()) {
            catchError("Bank Name is empty");
        } else if (bsb.isBlank()) {
            catchError("BSB Number is empty");
        } else if (accountName.isBlank()) {
            catchError("Account Name is empty");
        } else if (accountNumber.isBlank()) {
            catchError("Account Number is empty");
        } else if (email.isBlank()) {
            catchError("Email is empty");
        } else if (password.isBlank()) {
            catchError("Password is empty");
        } else if (confPassword.isBlank()) {
            catchError("Confirm Password is empty");
        } else if (!password.equalsIgnoreCase(confPassword)) {
            catchError("Password and Confirm Password are not matching");
        } else if (App.getDatabaseManager().isEmailRegistered(email)) {
            catchError("This email is already in the database. choose another email");
        } else {
            java.sql.Date sqlDOB = java.sql.Date.valueOf(dob);
            java.sql.Date sqlstartDate = java.sql.Date.valueOf(startDate);
            
            // add employee in the database
            EmployeeEntity empl = new EmployeeEntity(fname, lname, sqlDOB, gtype, contactNum, address, sqlstartDate, jobTitle, employeeTy, email);
            // add employee's bank account in the database
            BankEntity bnk = new BankEntity(bankName, bsb, accountName, accountNumber);
            App.getDatabaseManager().addNewEmployeeData(empl, bnk);
            App.getDatabaseManager().addNewBankData(bnk, empl.getEmployeeid());
            // add login data for the employee in the database
            LoginEntity data = new LoginEntity(empl.getEmployeeid(), email, password);
            App.getDatabaseManager().addNewLoginData(data);
            
            // clear all the fields
            clearFields();

            // show the success message
            showSuccessMessage();
        }
    }

    public void catchError(String message) {
        this.errorMessage.setText(message);
        this.errorMessage.setVisible(true);
    }

    @FXML
    private void genderMenuEvent(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        String selected = selectedItem.getText();
        genderMenu.setText(selected);
        gender = selected;
    }

    @FXML
    private void employeeTypeMenuEvent(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        String selected = selectedItem.getText();
        employeeTypeMenu.setText(selected);
        employeeType = selected;
    }

    public TextField getLnField() {
        return lnField;
    }

    public void setLnField(TextField lnField) {
        this.lnField = lnField;
    }

    public TextField getAddressLine1Field() {
        return addressLine1Field;
    }

    public void setAddressLine1Field(TextField addressLine1Field) {
        this.addressLine1Field = addressLine1Field;
    }

    public TextField getAddressLine2Field() {
        return addressLine2Field;
    }

    public void setAddressLine2Field(TextField addressLine2Field) {
        this.addressLine2Field = addressLine2Field;
    }

    public TextField getContactNumberField() {
        return contactNumberField;
    }

    public void setContactNumberField(TextField contactNumberField) {
        this.contactNumberField = contactNumberField;
    }

    public TextField getJobTitleField() {
        return jobTitleField;
    }

    public void setJobTitleField(TextField jobTitleField) {
        this.jobTitleField = jobTitleField;
    }

    public TextField getBankNameField() {
        return bankNameField;
    }

    public void setBankNameField(TextField bankNameField) {
        this.bankNameField = bankNameField;
    }

    public TextField getBsbField() {
        return bsbField;
    }

    public void setBsbField(TextField bsbField) {
        this.bsbField = bsbField;
    }

    public TextField getAccountNameField() {
        return accountNameField;
    }

    public void setAccountNameField(TextField accountNameField) {
        this.accountNameField = accountNameField;
    }

    public TextField getAccountNumberField() {
        return accountNumberField;
    }

    public void setAccountNumberField(TextField accountNumberField) {
        this.accountNumberField = accountNumberField;
    }

    public DatePicker getStartDateField() {
        return startDateField;
    }

    public void setStartDateField(DatePicker startDateField) {
        this.startDateField = startDateField;
    }

    public MenuButton getGenderMenu() {
        return genderMenu;
    }

    public void setGenderMenu(MenuButton genderMenu) {
        this.genderMenu = genderMenu;
    }

    public DatePicker getDobField() {
        return dobField;
    }

    public void setDobField(DatePicker dobField) {
        this.dobField = dobField;
    }

    public MenuButton getEmployeeTypeMenu() {
        return employeeTypeMenu;
    }

    public void setEmployeeTypeMenu(MenuButton employeeTypeMenu) {
        this.employeeTypeMenu = employeeTypeMenu;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public void setEmailField(TextField emailField) {
        this.emailField = emailField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(TextField passwordField) {
        this.passwordField = passwordField;
    }

    public TextField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public void setConfirmPasswordField(TextField confirmPasswordField) {
        this.confirmPasswordField = confirmPasswordField;
    }

    public Text getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Text errorMessage) {
        this.errorMessage = errorMessage;
    }

    public TextField getFnField() {
        return fnField;
    }

    public void setFnField(TextField fnField) {
        this.fnField = fnField;
    }
    
    public void clearFields()
    {
        
            fnField.setText("");
            lnField.setText("");
            dobField.setValue(null);
            genderMenu.setText("No Selection");
            addressLine1Field.setText("");
            addressLine2Field.setText("");
            contactNumberField.setText("");
            employeeTypeMenu.setText("No Selection");
            startDateField.setValue(null);
            jobTitleField.setText("");
            bankNameField.setText("");
            bsbField.setText("");
            accountNameField.setText("");
            accountNumberField.setText("");
            emailField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
            gender = "";
            employeeType = "";
            errorMessage.setVisible(false);
        
    }
    
    public void showSuccessMessage()
    {
        
        Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Payroll System");
            alert.setHeaderText("Success");
            alert.setContentText("A new Employee has been added successfully");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                }
            });
        
    }
    
    
}

