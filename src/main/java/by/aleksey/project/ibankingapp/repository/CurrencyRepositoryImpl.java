package by.aleksey.project.ibankingapp.repository;

import by.aleksey.project.ibankingapp.model.Currency;
import by.aleksey.project.ibankingapp.service.ConnectionImp;

import java.sql.*;

public class CurrencyRepositoryImpl implements CurrencyRepository {

    @Override
    public Currency getByCurrencyUSD() throws SQLException {

        Connection connection = ConnectionImp.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from currencyrate where currency = 'USD'");
        resultSet.next();
        int id = resultSet.getInt("id");
        int amount = resultSet.getInt("amount");
        String currency = resultSet.getString("currency");
        double purchase = resultSet.getDouble("purchase");
        double selling = resultSet.getDouble("selling");
        return new Currency(id, amount, currency, purchase, selling);
    }

    @Override
    public Currency getByCurrencyEUR() throws SQLException {

        Connection connection = ConnectionImp.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from currencyrate where currency = 'EUR'");
        resultSet.next();
        int id = resultSet.getInt("id");
        int amount = resultSet.getInt("amount");
        String currency = resultSet.getString("currency");
        double purchase = resultSet.getDouble("purchase");
        double selling = resultSet.getDouble("selling");
        return new Currency(id, amount, currency, purchase, selling);
    }
}


