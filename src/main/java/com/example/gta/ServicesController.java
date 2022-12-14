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

public class ServicesController {
    Stage window;

    @FXML
    private TableView<ObservableList> tableServices;

    private ObservableList<ObservableList> data;
    DataSingelton dataS = DataSingelton.getInstance();



    @FXML
    private Button bttServBack;

    @FXML
    protected void initialize(){
        UpdateServiceTable();

    }

    public void UpdateServiceTable(){
        Connection c;
        data = FXCollections.observableArrayList();
        try{
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
                data.add(row);
            }
            tableServices.setItems(data);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }


    }



    @FXML
    protected void Back() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        window = (Stage) bttServBack.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    protected void addService(ActionEvent actionEvent) throws IOException{
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("addServise.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("???????????????????? ?????????? ????????????");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void DeleteService(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("DeleteService.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("???????????????? ????????????");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getcell(MouseEvent mouseEvent){
        ObservableList nom = tableServices.getSelectionModel().getSelectedItem();
        dataS.setIdClient(nom.get(0).toString());

        EditDelete.ID_Service = Integer.parseInt(nom.get(0).toString());
        EditDelete.Name_Service = nom.get(1).toString();
        EditDelete.Group_Lessons = nom.get(2).toString();
        EditDelete.Pool = nom.get(3).toString();
        EditDelete.Fight_lessons = nom.get(4).toString();
        EditDelete.Massage = nom.get(5).toString();
        EditDelete.Spa = nom.get(6).toString();


//        System.out.println(EditDelete.ID_Service);
//        System.out.println(EditDelete.Name_Service);
//        System.out.println(EditDelete.Group_Lessons);
//        System.out.println(EditDelete.Pool);
//        System.out.println(EditDelete.Fight_lessons);
//        System.out.println(EditDelete.Massage);
//        System.out.println(EditDelete.Spa);

    }
}
