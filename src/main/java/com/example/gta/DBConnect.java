package com.example.gta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static Connection conn;
    private static String url = "jdbc:postgresql://46.229.214.241:5432/Kulikov";
    private static String username = "PKS";
    private static String password = "PKS";

    public static Connection connect() throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(conn !=null && !conn.isClosed())
            return conn;
        connect();
        return conn;
    }
}