package com.rental_hubs_app.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconfig {

    private static final String URL = "jdbc:mysql://localhost:3307/rental_hub_app";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {

        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}