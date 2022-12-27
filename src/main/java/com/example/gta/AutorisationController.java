package com.example.gta;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.*;

public class AutorisationController {

    @FXML
    private Button bttEnter;
    @FXML
    private Label Label;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtLogin;
    public void Enter(){

        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/Kulikov", "PKS", "PKS");

            ResultSet rs = con.createStatement().executeQuery("SELECT \"Password\" from \"Autorisation\" WHERE \"Login\"='"+ txtLogin.getText() +"'");

            while (rs.next()){
                for (int i = 1; i <=rs.getMetaData().getColumnCount(); i++){
                    EditDelete.Password = rs.getString(1);
                }
            }
//            System.out.println(EditDelete.Password);
            if(txtPassword.getText().equals(EditDelete.Password)){
                try{
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                    stage.setScene(new Scene(root));
                    stage.setTitle("Добавление новой записи");
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{
                Label.setText("Логин или пароль неверны");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }






    }



}
