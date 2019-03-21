package config;

import java.sql.*;

public class MyConnection {

    private static Connection conn = null;

    public static Connection makeConnection() {

        if (conn == null){
            try {
                // register driver
                Class.forName("com.mysql.jdbc.Driver");

                conn = DriverManager.getConnection("jdbc:mysql://localhost/db_tokobuku",
                        "root", "");

            } catch (Exception e) {
                e.printStackTrace();
            }

            return conn;
        } else {
            return conn;
        }

    }

}
