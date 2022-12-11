package com.example.gta;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection ccc;
        ccc = DBConnect.connect();
        if(ccc !=null && !ccc.isClosed()){
            System.out.println("Есть контакт!");
        }else{
            System.out.println("Ладно");
        }


    }
}
