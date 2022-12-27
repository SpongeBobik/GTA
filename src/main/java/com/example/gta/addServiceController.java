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

public class addServiceController implements Initializable {
    @FXML
    private Button addService;
    @FXML
    private TextField txtboxNameServ;
    @FXML
    private ChoiceBox<String> chboxGroup;
    @FXML
    private ChoiceBox<String> chboxPool;
    @FXML
    private ChoiceBox<String> chboxFight;
    @FXML
    private ChoiceBox<String> chboxMassage;
    @FXML
    private ChoiceBox<String> chboxSpa;
    private String[] vibor = {"есть", "нет"};


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        chboxGroup.getItems().addAll(vibor);
        chboxPool.getItems().addAll(vibor);
        chboxFight.getItems().addAll(vibor);
        chboxMassage.getItems().addAll(vibor);
        chboxSpa.getItems().addAll(vibor);

    }

    public void buttonAdd(ActionEvent actionEvent){

        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/Kulikov", "PKS", "PKS")) {
            Statement statement = con.createStatement();
            int row = statement.executeUpdate("INSERT INTO \"Services\"(\"Name_Service\", \"Group_Lessons\", \"Pool\", \"Fight_lessons\", \"Massage\", \"Spa\") VALUES ('" + txtboxNameServ.getText() + "','" + chboxGroup.getValue().toString() + "','" + chboxPool.getValue().toString() + "','" + chboxFight.getValue().toString() + "','"+ chboxMassage.getValue().toString() + "','" + chboxSpa.getValue().toString() + "')");
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        Stage stage = (Stage) addService.getScene().getWindow();
        stage.close();

    }
}
