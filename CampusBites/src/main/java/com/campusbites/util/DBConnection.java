package com.campusbites.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/campus_bites";

    private static final String USER = "root";

    private static final String PASSWORD = "1122";

    public static Connection getConnection() {

        Connection connection = null;

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("Database connected successfully!");

        } catch (Exception e) {

            System.out.println("Database connection failed!");

            e.printStackTrace();
        }

        return connection;
    }
}