package Controller;

import View.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class CreateReportSceneController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void byWeekEvent(ActionEvent event) {
        App.changeUI("CreateReportByWeek.fxml");
    }

    @FXML
    private void byMonthEvent(ActionEvent event) {
        App.changeUI("CreateReportByMonth.fxml");
    }

    @FXML
    private void byYearEvent(ActionEvent event) {
        App.changeUI("CreateReportByYear.fxml");
    }

    @FXML
    private void logOutEvent(ActionEvent event) {
        App.changeUI("LoginPageScene.fxml");
    }

    @FXML
    private void homeBtnEvent(ActionEvent event) {
        App.changeUI("StaffHomeScene.fxml");
    }
}
