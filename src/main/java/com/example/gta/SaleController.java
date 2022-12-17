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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

public class SaleController {
    Stage window;
    DataSingelton dataS = DataSingelton.getInstance();
    @FXML
    private TableView<ObservableList> tableSale_Serv;
    private ObservableList<ObservableList> data;
    private ObservableList<ObservableList> dataSer;
    @FXML
    private Button bttSaleBack;

    @FXML
    private  TableView<ObservableList> tableServices;


    @FXML
    protected void initialize(){
        UpdateSalesTable();
        UpdateServTable();

    }

    public void UpdateSalesTable(){
        data = FXCollections.observableArrayList();
        try{
            Connection c;
            c = DBConnect.connect();
            ResultSet rs = c.createStatement().executeQuery("SELECT * from \"Sale_Services\"");
            for(int i = 0; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tableSale_Serv.getColumns().addAll(col);
            }
            while (rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <=rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tableSale_Serv.setItems(data);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    public void UpdateServTable(){
        dataSer = FXCollections.observableArrayList();
        try{
            Connection c;
            c = DBConnect.connect();
            ResultSet rs = c.createStatement().executeQuery("SELECT * from \"Services\"");
            for(int i = 0; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tableServices.getColumns().addAll(col);
            }
            while (rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <=rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                dataSer.add(row);
            }
            tableServices.setItems(dataSer);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    protected void addSale(ActionEvent actionEvent) throws IOException{
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("addSale.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Добавление новой записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void DeleteSale(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("DeleteSale.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Удаление записи");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    protected void Back() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        window = (Stage) bttSaleBack.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void getcell(MouseEvent mouseEvent){
        ObservableList nom = tableSale_Serv.getSelectionModel().getSelectedItem();
        dataS.setIdClient(nom.get(0).toString());


        EditDelete.ID_Card = Integer.parseInt(nom.get(0).toString());
        EditDelete.Service_ID = Integer.parseInt(nom.get(1).toString());
        EditDelete.Duration = nom.get(2).toString();
        EditDelete.Client_ID = Integer.parseInt(nom.get(3).toString());
        EditDelete.Col_Visits = nom.get(4).toString();
        EditDelete.Cost = nom.get(5).toString();

//
//        System.out.println(EditDelete.ID_Card);
//        System.out.println(EditDelete.Service_ID);
//        System.out.println(EditDelete.Duration);
//        System.out.println(EditDelete.Client_ID);
//        System.out.println(EditDelete.Col_Visits);
//        System.out.println(EditDelete.Cost);

    }

}
