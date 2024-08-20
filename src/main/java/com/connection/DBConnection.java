
package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {

    private static Connection con;

    public static Connection getConnection() {
        try {
            if (con == null) {
                // Driver class load
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Create Connection
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebook-app", "root", "Dipanwita3018@");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }

        return con;
    }
}
