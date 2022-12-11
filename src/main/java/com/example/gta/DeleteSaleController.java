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

public class DeleteSaleController {

    @FXML
    private Label Label;
    @FXML
    private Button bttDeleteSale;
    @FXML
    private Button back;

    public void initialize() {
        Label.setText("Карта N " + EditDelete.ID_Card + "     ?");
    }

    @FXML
    void deleteSaleMethod(ActionEvent actionEvent) {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/Kulikov", "kulikov", "kulikov");
            Statement statement = con.createStatement();

            int rows = statement.executeUpdate("DELETE FROM \"Sale_Services\" WHERE \"ID_Card\" = " + EditDelete.ID_Card + "");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) bttDeleteSale.getScene().getWindow();
        stage.close();

    }





    @FXML
    void Back(){
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }


}
