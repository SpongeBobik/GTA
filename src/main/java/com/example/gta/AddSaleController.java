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

public class AddSaleController {

    @FXML
    private TextField txtboxService;
    @FXML
    private TextField txtboxDur;
    @FXML
    private TextField txtboxClient;
    @FXML
    private TextField txtboxCol;
    @FXML
    private TextField txtboxCost;
    @FXML
    private Button bttAddSale;
    @FXML
    private Button Back;

    public void addSale (ActionEvent actionEvent){

        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/Kulikov", "PKS", "PKS")) {
            Statement statement = con.createStatement();
            int row = statement.executeUpdate("INSERT INTO \"Sale_Services\"(\"Service_ID\", \"Duration\", \"Client_ID\", \"Col_Visits\", \"Cost\") VALUES ('" + txtboxService.getText() + "','" + txtboxDur.getText() + "','" + txtboxClient.getText() + "','" +txtboxCol.getText()+ "','" + txtboxCost.getText()+ "')");
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        Stage stage = (Stage) bttAddSale.getScene().getWindow();
        stage.close();

    }

    @FXML
    void back(){
        Stage stage = (Stage) Back.getScene().getWindow();
        stage.close();
    }
}
