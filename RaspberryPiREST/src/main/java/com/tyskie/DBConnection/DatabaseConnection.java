package com.tyskie.DBConnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    public Connection getConnection() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        InputStream resourceAsStream = contextClassLoader.getResourceAsStream("JDBC.properties");
        Properties properties = new Properties();
        Connection conn = null;
        try {
            properties.load(resourceAsStream);
            Class.forName(properties.getProperty("DB_DRIVER_MYSQL"));
            conn = DriverManager.getConnection(properties.getProperty("DB_URL_MYSQL"), properties.getProperty("DB_USER_MYSQL"), properties.getProperty("DB_PASSWORD_MYSQL"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        DatabaseConnection connection = new DatabaseConnection();
        if(connection.getConnection() == null){
            System.out.println("no connection");
        } else {
            System.out.println("Connected");
        }
    }
}