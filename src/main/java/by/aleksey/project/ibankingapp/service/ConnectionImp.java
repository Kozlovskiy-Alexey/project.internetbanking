package by.aleksey.project.ibankingapp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionImp {

    private static final String URL = "jdbc:mysql://localhost:3306/ibanking?serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
