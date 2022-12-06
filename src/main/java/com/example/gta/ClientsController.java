package com.example.gta;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

public class ClientsController {

    Stage window;
    @FXML
    private TableView<ObservableList> tableClients;
    @FXML
    private Button bttClientsBack;
    @FXML
    private Button bttAddClient;
    private ObservableList<ObservableList> data;
    @FXML
    protected void initialize(){
        Connection c;
        data = FXCollections.observableArrayList();
        try{
            c = DBConnect.connect();
            ResultSet rs = c.createStatement().executeQuery("SELECT * from \"Clients\"");
            for(int i = 0; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tableClients.getColumns().addAll(col);
            }
            while (rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <=rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tableClients.setItems(data);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }


    }
    @FXML
    protected void Back() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        window = (Stage) bttClientsBack.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    protected void addClient(ActionEvent actionEvent) throws IOException{
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("addClient.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Добавление новой записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
