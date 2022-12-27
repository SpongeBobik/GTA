package com.example.gta;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.nio.Buffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteServiceController {

    @FXML
    private Label myLabel;
    @FXML
    private Button bttDelService;
    @FXML
    private Button back;


    public void initialize() {
        myLabel.setText(EditDelete.Name_Service + " (id: " + EditDelete.ID_Service + ")      ?");
    }

    @FXML
    void deleteServiceMethod(ActionEvent actionEvent) {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/Kulikov", "PKS", "PKS");
            Statement statement = con.createStatement();

            int rows = statement.executeUpdate("DELETE FROM \"Services\" WHERE \"ID_Service\" = " + EditDelete.ID_Service + "");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) bttDelService.getScene().getWindow();
        stage.close();

    }

    @FXML
    void Back(){
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }
}
