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

public class VisitsController {
    Stage window;
    DataSingelton dataS = DataSingelton.getInstance();
    @FXML
    private Button bttVisitsBack;
    @FXML
    private TableView<ObservableList> tableVisits;
    @FXML
    private TableView<ObservableList> tableSales;
    private ObservableList<ObservableList> data;
    private ObservableList<ObservableList> dataSale;

    @FXML
    protected void initialize(){
        UpdateVisitsTable();
        UpdateSalesTable();
    }

    public void UpdateVisitsTable(){
        Connection c;
        data = FXCollections.observableArrayList();
        try{
            c = DBConnect.connect();
            ResultSet rs = c.createStatement().executeQuery("SELECT * from \"Visits\"");
            for(int i = 0; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tableVisits.getColumns().addAll(col);
            }
            while (rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <=rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tableVisits.setItems(data);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }

    public void UpdateSalesTable(){
        dataSale = FXCollections.observableArrayList();
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
                tableSales.getColumns().addAll(col);
            }
            while (rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <=rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                dataSale.add(row);
            }
            tableSales.setItems(dataSale);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    protected void addVisit(ActionEvent actionEvent) throws IOException{
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("addVisit.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("???????????????????? ?????????? ????????????");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void DeleteVisit(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("DeleteVisit.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("???????????????? ????????????");
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
        window = (Stage) bttVisitsBack.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void getcell(MouseEvent mouseEvent){
        ObservableList nom = tableVisits.getSelectionModel().getSelectedItem();
        dataS.setIdClient(nom.get(0).toString());


        EditDelete.ID_Visit = Integer.parseInt(nom.get(0).toString());
        EditDelete.Card_ID = Integer.parseInt(nom.get(1).toString());
        EditDelete.Data = nom.get(2).toString();
//
//        System.out.println(EditDelete.ID_Visit);
//        System.out.println(EditDelete.Card_ID);
//        System.out.println(EditDelete.Data);

    }


}
