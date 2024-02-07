package Controller;

import View.App;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class ViewEmployeeSceneController implements Initializable {

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
}
