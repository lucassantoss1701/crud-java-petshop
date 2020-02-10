package br.com.luan.petshop.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    private static Connection connection = null;

    public static Connection getConnection() {
        if(connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.
                        getConnection("jdbc:mysql://localhost/petshop"
                                ,"root","123456789");
                System.out.println("Created DB Connection....");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
