package View;

import Controller.EmployeeHomeSceneController;
import Controller.StaffHomeSceneController;
import Controller.UpdateEmployeeSceneController;
import Controller.ViewEmployeeSceneController;
import Controller.ViewPayslipsDetailsSceneController;
import Model.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    private static boolean databaseExist = false;
    private static Stage primaryStage;
    private static Scene currentScene;
    private static DatabaseManager databaseManager;
    private static String fileName;
    private static String employeeName;
    private static int employeeId;
    private static int searchEmployeeID;
    private static String employeeType;
    private static final String DATABASE_SERVER_USERNAME = "root"; // change as per mysql username
    private static final String DATABASE_SERVER_PASSWORD = "15896"; // change as per mysql password



    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("GET IT NOW, Payroll System");
        // Load the initial FXML file and set it as the scene
        changeUI("LoginPageScene.fxml");
        fileName = "AddNewEmployee.fxml";
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void changeUI(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlFileName));
            Parent root = loader.load();
            if (!databaseExist) {

                // change the username and password here according to mysql username and password
                databaseManager = new DatabaseManager(DATABASE_SERVER_USERNAME, DATABASE_SERVER_PASSWORD);
                databaseManager.createDatabaseAndTables();
                databaseExist = true;
            }
            if (fxmlFileName.equalsIgnoreCase("EmployeeHomeScene.fxml")) {
                EmployeeHomeSceneController epc = (EmployeeHomeSceneController) loader.getController();
                epc.setName(employeeName);
            }
            if (fxmlFileName.equalsIgnoreCase("StaffHomeScene.fxml")) {
                StaffHomeSceneController epc = (StaffHomeSceneController) loader.getController();
                System.out.println(employeeName);
                epc.setEmployeeName(employeeName);
            }
            if (fxmlFileName.equalsIgnoreCase("ViewEmployeeScene.fxml")) {
                ViewEmployeeSceneController vec = (ViewEmployeeSceneController) loader.getController();
                databaseManager.setFieldForViewEmployee(vec, App.getSearchEmployeeID());
            }
            if (fxmlFileName.equalsIgnoreCase("UpdateEmployeeScene.fxml")) {
                UpdateEmployeeSceneController uec = (UpdateEmployeeSceneController) loader.getController();
                databaseManager.setFieldsForUpdateEmployee(uec, App.getSearchEmployeeID());
            }
            if (fxmlFileName.equalsIgnoreCase("ViewPayslipsDetailsScene.fxml")) {
                ViewPayslipsDetailsSceneController vpd = (ViewPayslipsDetailsSceneController) loader.getController();
                databaseManager.setFieldsForViewPayslip(vpd);
            }

            currentScene = new Scene(root);
            primaryStage.setScene(currentScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        App.fileName = fileName;
    }
        public static String getEmployeeType() {
        return employeeType;
    }

    public static void setEmployeeType(String employeeType) {
        App.employeeType = employeeType;
    }

    public static int getSearchEmployeeID() {
        return searchEmployeeID;
    }

    public static void setSearchEmployeeID(int searchEmployeeID) {
        App.searchEmployeeID = searchEmployeeID;
    }

    public static int getEmployeeId() {
        return employeeId;
    }

    public static void setEmployeeId(int employeeId) {
        App.employeeId = employeeId;
    }

    public static String getEmployeeName() {
        return employeeName;
    }

    public static void setEmployeeName(String employeeName) {
        App.employeeName = employeeName;
    }

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public static void setDatabaseManager(DatabaseManager databaseManager) {
        App.databaseManager = databaseManager;
    }
}
