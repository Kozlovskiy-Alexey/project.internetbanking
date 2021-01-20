package by.aleksey.project.ibankingapp.repository;

import by.aleksey.project.ibankingapp.model.Account;
import by.aleksey.project.ibankingapp.model.Deposit;
import by.aleksey.project.ibankingapp.service.ConnectionImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepositRepositoryImpl implements DepositRepository {

    private static final String GET_DEPOSIT = "select * from deposit where customerid = ? order by id";

    @Override
    public ArrayList<Deposit> getByDeposit(Account account) throws SQLException {

        ArrayList<Deposit> list = new ArrayList<>();
        Connection connection = ConnectionImp.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_DEPOSIT);
        statement.setInt(1, account.getId());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nameOfDeposit = resultSet.getString("nameOfDeposit");
            int termOfDeposit = resultSet.getInt("termOfDeposit");
            double percentRate = resultSet.getDouble("percentRate");
            double amount = resultSet.getDouble("amount");
            int customerId = resultSet.getInt("customerId");
            list.add(new Deposit(id, nameOfDeposit, termOfDeposit, percentRate, amount, customerId));
        }
        return list;
    }
}
