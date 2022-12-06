package com.example.gta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddClientController {
    String stat;
    String id;
    List listId;
    List listStat;
    String str;
    List<String>id_list = new ArrayList<>();
    List<String>stat_list = new ArrayList<>();
    String GetValue;
    @FXML
    private ComboBox<?> comboStatus;

    @FXML
    void initialize(){
        getcomboStatus();
    }
    public void getcomboStatus(){
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/Kulikov", "kulikov", "kulikov")) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from \"Status\"");
            while(rs.next()){
                ObservableList<String> listRow = FXCollections.observableArrayList();
                for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    listRow.add(rs.getString(i));
                }

                id = listRow.get(0);
                stat = listRow.get(1);

                listId = new ArrayList<>(Collections.singleton(id));
                listStat = new ArrayList(Collections.singleton(stat));

                id_list.add(id);
                stat_list.add(stat);

                stat_list.indexOf(stat_list);

                System.out.println("Выбранный элемент " + listStat.get(0));
                comboStatus.getItems().addAll(listStat);
                comboStatus.getSelectionModel().select(0);

                GetValue = String.valueOf(comboStatus.getSelectionModel().getSelectedIndex());

                str = String.valueOf(stat_list.indexOf(stat_list.get(Integer.parseInt(GetValue))));

            }
            System.out.println(stat_list.get(0));
            System.out.println(id_list.get(Integer.parseInt(str)));


        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
