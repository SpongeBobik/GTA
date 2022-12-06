module com.example.gta {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.gta to javafx.fxml;
    exports com.example.gta;
}