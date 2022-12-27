package com.example.gta;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteVisitController {


    @FXML
    private javafx.scene.control.Label Label;
    @FXML
    private Button bttDeleteVisit;
    @FXML
    private Button back;

    public void initialize() {
        Label.setText("От     " + EditDelete.Data + "     ?");
    }

    @FXML
    void deleteVisitMethod(ActionEvent actionEvent) {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/Kulikov", "PKS", "PKS");
            Statement statement = con.createStatement();

            int rows = statement.executeUpdate("DELETE FROM \"Visits\" WHERE \"ID_Visit\" = " + EditDelete.ID_Visit + "");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) bttDeleteVisit.getScene().getWindow();
        stage.close();

    }





    @FXML
    void Back(){
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }


}
