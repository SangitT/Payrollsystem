package Controller;

import View.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginPageSceneController implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private Text errorMessage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginBtnEvent(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();
        
        int result = App.getDatabaseManager().verifyIfUserExist(email, password);
        if(result != -1)
        {
            String employeeT = App.getDatabaseManager().getTypeForEmployee(result);
            App.setEmployeeId(result);
            App.setSearchEmployeeID(result);
            App.setEmployeeType(employeeT);
            if(employeeT.equalsIgnoreCase("employee"))
            {
                App.changeUI("EmployeeHomeScene.fxml");

            }
            else if(employeeT.equalsIgnoreCase("staff"))
            {
                App.changeUI("StaffHomeScene.fxml");
            }
        }
        else{
            errorMessage.setVisible(true);
        }
    }
}
