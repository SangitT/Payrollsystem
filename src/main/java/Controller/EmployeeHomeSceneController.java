package Controller;

import View.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class EmployeeHomeSceneController implements Initializable {

    @FXML
    private Label employeeNameField;

    public void setName(String name) {
        this.employeeNameField.setText(name);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void enterWorkHoursEvent(ActionEvent event) {
        App.changeUI("LoginWorkHoursScene.fxml");
    }

    @FXML
    private void viewPersonalInfoEvent(ActionEvent event) {
        App.changeUI("ViewEmployeeScene.fxml");
    }

    @FXML
    private void updatePersonalInfoEvent(ActionEvent event) {
        App.changeUI("UpdateEmployeeScene.fxml");
    }

    @FXML
    private void viewPayslipsEvent(ActionEvent event) {
        App.changeUI("ViewPayslipScene.fxml");
    }

    @FXML
    private void logOutEvent(ActionEvent event) {
        App.changeUI("LoginPageScene.fxml");
    }
}
