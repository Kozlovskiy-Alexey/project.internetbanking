package by.aleksey.project.ibankingapp.repository;

import by.aleksey.project.ibankingapp.model.Account;
import by.aleksey.project.ibankingapp.model.CreditCard;
import by.aleksey.project.ibankingapp.service.ConnectionImp;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class CreditCardRepositoryImpl implements CreditCardRepository {

    private static final String UPDATE_BALANCE = "update creditcards set balancecard = ? where id = ?";
    private static final String GET_BY_ID_QUERY = "select balancecard from creditcards where id = ?";
    private static final String GET_BY_LOGIN_ID_QUERY = "select * from creditcards where customerid = ?";
    private static final String GET_CREDIT_CARD_BY_ID_QUERY = "select * from creditcards where id = ?";
    private static final String GET_CREDIT_CARD_BY_CARD_NUMBER_QUERY = "select * from creditcards where cardnumber = ?";

    @Override
    public CreditCard getCreditCardById(int creditCardId) throws SQLException {

        Connection connection = ConnectionImp.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_CREDIT_CARD_BY_ID_QUERY);
        statement.setInt(1, creditCardId);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int id = resultSet.getInt("id");
        double balanceCard = resultSet.getDouble("balancecard");
        String currency = resultSet.getString("currency");
        long curdNumber = resultSet.getLong("cardnumber");
        String ownerCard = resultSet.getString("ownercard");
        int customerId = resultSet.getInt("customerid");
        return new CreditCard(id, curdNumber, currency, balanceCard, ownerCard, customerId);
    }

    @Override
    public CreditCard getCreditCardByCardNumber(long creditCardNumber) throws SQLException {

        Connection connection = ConnectionImp.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_CREDIT_CARD_BY_CARD_NUMBER_QUERY);
        statement.setLong(1, creditCardNumber);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int id = resultSet.getInt("id");
        double balanceCard = resultSet.getDouble("balancecard");
        String currency = resultSet.getString("currency");
        long curdNumber = resultSet.getLong("cardnumber");
        String ownerCard = resultSet.getString("ownercard");
        int customerId = resultSet.getInt("customerid");
        return new CreditCard(id, curdNumber, currency, balanceCard, ownerCard, customerId);
    }

    @Override
    public void updateBalanceCard(int id, double amount) throws SQLException {

        Connection connection = ConnectionImp.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_BALANCE);
        statement.setDouble(1, amount);
        statement.setInt(2, id);
        statement.executeUpdate();
    }

    @Override
    public ArrayList<CreditCard> getInfoByCard(Account account) {

        ArrayList<CreditCard> list = new ArrayList<>();
        try {
            Connection connection = ConnectionImp.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_BY_LOGIN_ID_QUERY);
            statement.setInt(1, account.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int pinCode = resultSet.getInt("pincode");
                String kindCard = resultSet.getString("kindcard");
                long cardNumber = resultSet.getLong("cardnumber");
                Date validThrough = resultSet.getDate("validthrough");
                String statusCard = resultSet.getString("statuscard");
                String ownerCard = resultSet.getString("ownercard");
                double balanceCard = resultSet.getDouble("balancecard");
                String currency = resultSet.getString("currency");
                int customerId = resultSet.getInt("customerid");
                list.add(new CreditCard(id, pinCode, kindCard, cardNumber, validThrough, statusCard, ownerCard, balanceCard, currency, customerId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}


