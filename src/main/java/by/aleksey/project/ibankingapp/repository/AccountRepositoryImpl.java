package by.aleksey.project.ibankingapp.repository;

import by.aleksey.project.ibankingapp.model.Account;
import by.aleksey.project.ibankingapp.service.ConnectionImp;

import java.sql.*;

public class AccountRepositoryImpl implements AccountRepository {

    private static final String GET_BY_LOGIN_QUERY = "select * from ibankingusers where login = ?";

    public Account getByLogin(String login) throws SQLException {

        Connection connection = ConnectionImp.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_BY_LOGIN_QUERY);
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next())
            throw new SQLException("логин не найден!");

        int id = resultSet.getInt("id");
        String loginUser = resultSet.getString("login");
        String password = resultSet.getString("password");
        String userName = resultSet.getString("username");

        return new Account(id, loginUser, password, userName);
    }
}

