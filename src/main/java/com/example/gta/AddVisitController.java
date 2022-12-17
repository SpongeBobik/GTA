package com.example.gta;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddVisitController {

    @FXML
    private TextField txtCard;
    @FXML
    private TextField txtData;
    @FXML
    private Button bttAddVisit;

    public void addVisit (ActionEvent actionEvent){

            try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/Kulikov", "kulikov", "kulikov")) {
                Statement statement = con.createStatement();
                int row = statement.executeUpdate("INSERT INTO \"Visits\"(\"Card_ID\", \"Date\") VALUES ('" + txtCard.getText() + "','" + txtData.getText() + "')");
            }catch(SQLException throwables){
                throwables.printStackTrace();
            }

            Stage stage = (Stage) bttAddVisit.getScene().getWindow();
            stage.close();



    }



}
