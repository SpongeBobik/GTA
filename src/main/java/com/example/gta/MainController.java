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
    protected void buttonClients() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Clients.fxml"));
        window = (Stage) ButtonClients.getScene().getWindow();
        window.setScene(new Scene(root));
    }

//@FXML
//private Button id_nomenclature;

//       @FXML
//    protected void buttonNomenclature() throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource("nomenclaturePage.fxml"));
//        window = (Stage) id_nomenclature.getScene().getWindow();
//        window.setScene(new Scene(root));
//    }


}
