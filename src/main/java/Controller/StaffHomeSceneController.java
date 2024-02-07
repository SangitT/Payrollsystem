package Controller;

import View.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;

public class StaffHomeSceneController implements Initializable {

    @FXML
    private Label employeeNameField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void registerNewEmployeeEvent(ActionEvent event) {
        App.changeUI("NewEmployeeScene.fxml");
    }

    @FXML
    private void searchEmployeeEvent(ActionEvent event) {
        App.changeUI("SearchEmployeeScene.fxml");
    }

    @FXML
    private void generateReportEvent(ActionEvent event) {
        App.changeUI("CreateReportScene.fxml");
    }

    @FXML
    private void logOutEvent(ActionEvent event) {
        App.changeUI("LoginPageScene.fxml");
    }

    public void setEmployeeName(String employeeName) {
        employeeNameField.setText(employeeName);
    }
}
