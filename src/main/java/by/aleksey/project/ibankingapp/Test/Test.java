package by.aleksey.project.ibankingapp.Test;


import by.aleksey.project.ibankingapp.service.ConnectionImp;
import by.aleksey.project.ibankingapp.service.PaymentReceiptServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test {
    private final static String SET_PAYMENT_RECEIPT_TRANSACTION_QUERY = "insert into paymentreciept value (null, 'перевод на карту',?, ?, ?, ?, ?, ?)";
    private final static String SET_PAYMENT_RECEIPT_DEPOSIT_QUERY = "insert into paymentreciept value (null, 'открытие депозита', ?, ?, ?, ?, ?, ?)";

    public static void main(String[] args) {

   /*     String timeOfTransaction = PaymentReceiptServiceImpl.getDateAndTime();
        try {
            Connection connection = ConnectionImp.getConnection();
            PreparedStatement statement = connection.prepareStatement(SET_PAYMENT_RECEIPT_TRANSACTION_QUERY);
            statement.setString(1, "12/05");
            statement.setString(2, "accountName");
            statement.setString(3, "creditCard.getCurrency()");
            statement.setDouble(4, 100.00);
            statement.setLong(5, 1313213213);
            statement.setInt(6, 5);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }
}