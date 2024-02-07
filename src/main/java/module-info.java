module View {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    

    opens View to javafx.fxml;
    opens Controller to javafx.fxml;
    
    
    
    exports View;
    exports Controller;
}
    

   
