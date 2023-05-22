package com.vols.gestionvols;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnexionDB {
    static String user = "root";
    static String password = "";
    static String url = "jdbc:mysql://localhost/gestionvols";
    static String driver = "com.mysql.jdbc.Driver";

    public static Connection getConnectiion() throws ClassNotFoundException {
        Connection connection = null;
        try {
            Class.forName(driver);
            try {
                connection = (Connection) DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}