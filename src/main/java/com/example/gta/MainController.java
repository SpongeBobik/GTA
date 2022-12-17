package com.example.gta;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    Stage window;
    @FXML
    private Button ButtonClients;

    @FXML
    private  Button ButtonServices;

    @FXML
    private Button ButtonSaleServ;
    @FXML
    private Button ButtonVisits;
    @FXML
    protected void buttonClients() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Clients.fxml"));
        window = (Stage) ButtonClients.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    protected void buttonServices() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Services.fxml"));
        window = (Stage) ButtonServices.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    protected void buttonSale() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Sale.fxml"));
        window = (Stage) ButtonSaleServ.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    protected void buttonVisits() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Visits.fxml"));
        window = (Stage) ButtonClients.getScene().getWindow();
        window.setScene(new Scene(root));
    }


}
