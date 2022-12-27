package com.example.gta;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteClientController {


    @FXML
    private Button Back;
    @FXML
    private Button deleteClient;
    @FXML
    private Label labelDeletedItem;

    public void initialize() {
        labelDeletedItem.setText(EditDelete.FioClient + " (id: " + EditDelete.idClient + ")      ?");
    }


    @FXML
    void back(){
        Stage stage = (Stage) Back.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteClientMethod(ActionEvent actionEvent) {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/Kulikov", "PKS", "PKS");
            Statement statement = con.createStatement();

            int rows = statement.executeUpdate("DELETE FROM \"Clients\" WHERE \"ID_Client\" = " + EditDelete.idClient + "");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) deleteClient.getScene().getWindow();
        stage.close();

    }
}
