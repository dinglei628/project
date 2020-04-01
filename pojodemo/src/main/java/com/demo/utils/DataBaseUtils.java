package com.demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtils {
    private static String DRIVER = PropertiesUtils.getProperty("database.properties","driver");
    private static String URL = PropertiesUtils.getProperty("database.properties","url");
    private static String USER = PropertiesUtils.getProperty("database.properties","user");
    private static String PWD = PropertiesUtils.getProperty("database.properties","pwd");

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
