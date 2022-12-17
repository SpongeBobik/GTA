package com.example.gta;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UpdateClientController implements Initializable {

    @FXML
    private TextField txtBoxNameClient;
    @FXML
    private TextField txtBoxPhoneClient;
    @FXML
    private TextField txtBoxMailClient;
    @FXML
    private Button bttUpdate;
    @FXML
    private ChoiceBox<String> chboxStatus;
    private String[] vibor = {"Активен", "Неактивен"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chboxStatus.getItems().addAll(vibor);

        txtBoxNameClient.setText(EditDelete.FioClient);
        txtBoxPhoneClient.setText(EditDelete.PhoneClient);
        txtBoxMailClient.setText(EditDelete.e_mailClient);
        chboxStatus.setValue(EditDelete.Status);


    }

    public void buttonUpdate(ActionEvent actionEvent){


        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/Kulikov", "kulikov", "kulikov")) {
            Statement statement = con.createStatement();
            int row = statement.executeUpdate("UPDATE \"Clients\" SET \"FIO\"='"+ txtBoxNameClient.getText() +"', \"Phone\"='"+ txtBoxPhoneClient.getText() +"', \"e_mail\"='"+ txtBoxMailClient.getText() +"', \"Status\"='"+ chboxStatus.getValue() +"' WHERE \"ID_Client\" ="+ EditDelete.idClient+ ";");
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        Stage stage = (Stage) bttUpdate.getScene().getWindow();
        stage.close();

    }


}
