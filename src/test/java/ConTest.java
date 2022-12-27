import com.example.gta.EditDelete;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConTest {

    public static void main (String[] args){

        String resultat = "1234";

        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://46.229.214.241:5432/Kulikov", "kulikov", "kulikov");

            ResultSet rs = con.createStatement().executeQuery("SELECT \"Password\" from \"Autorisation\" WHERE \"Login\"='admin'");

            while (rs.next()){
                for (int i = 1; i <=rs.getMetaData().getColumnCount(); i++){
                    EditDelete.Password = rs.getString(1);
                }
            }
//            System.out.println(EditDelete.Password);
            if(resultat.equals(EditDelete.Password)){
                System.out.print("Тест прошел успешно");

            }else{
                System.out.print("Тест не пройден");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }






    }
}
