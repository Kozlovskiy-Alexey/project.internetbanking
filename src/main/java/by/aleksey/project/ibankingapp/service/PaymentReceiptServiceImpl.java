package by.aleksey.project.ibankingapp.service;

import by.aleksey.project.ibankingapp.model.CreditCard;
import by.aleksey.project.ibankingapp.model.PaymentReceipt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PaymentReceiptServiceImpl implements PaymentReceiptService {

    private final static String SET_PAYMENT_RECEIPT_TRANSACTION_TO_OWN_CARD_QUERY = "insert into paymentreciept value (null, 'перевод средств с карты',?, ?, ?, ?, ?, ?)";
    private final static String SET_PAYMENT_RECEIPT_TRANSACTION_TO_ANOTHER_CARD_QUERY = "insert into paymentreciept value (null, 'поступление средств на карту',?, ?, ?, ?, ?, ?)";
    private final static String SET_PAYMENT_RECEIPT_DEPOSIT_QUERY = "insert into paymentreciept value (null, 'открытие депозита', ?, ?, ?, ?, ?, ?)";

    public void showHistoryOfTransaction(ArrayList<PaymentReceipt> paymentReceipt) {
        System.out.println("История операций:");
        System.out.printf("%-5s%-30s%-30s%-20s%-20s%-12s%n", "id", "Название операции", "Дата и время выполнения", "Данные отправителя", "Карта отправителя", "Сумма");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        for (PaymentReceipt p : paymentReceipt) {
            System.out.printf("%-5d%-30s%-30s%-20s%-20d%-12.2f%n", p.getId(), p.getTransaction(), p.getTimeTransaction(), p.getUserNameSender(), p.getCardNumberSender(), p.getAmount());
        }
        System.out.println();
    }

    @Override
    public void setTransactionTransferMoneyToOwnCard(CreditCard creditCard, double amount) throws SQLException {
        String timeOfTransaction = PaymentReceiptServiceImpl.getDateAndTime();
        Connection connection = ConnectionImp.getConnection();
        PreparedStatement statement = connection.prepareStatement(SET_PAYMENT_RECEIPT_TRANSACTION_TO_OWN_CARD_QUERY);
        statement.setString(1, timeOfTransaction);
        statement.setString(2, creditCard.getOwnerCard());
        statement.setString(3, creditCard.getCurrency());
        statement.setDouble(4, amount);
        statement.setLong(5, creditCard.getCardNumber());
        statement.setInt(6, creditCard.getCustomerId());
        statement.executeUpdate();
    }

    @Override
    public void setTransactionTransferMoneyToAnotherCard(CreditCard creditCard, double amount) throws SQLException {
        String timeOfTransaction = PaymentReceiptServiceImpl.getDateAndTime();
        Connection connection = ConnectionImp.getConnection();
        PreparedStatement statement = connection.prepareStatement(SET_PAYMENT_RECEIPT_TRANSACTION_TO_ANOTHER_CARD_QUERY);
        statement.setString(1, timeOfTransaction);
        statement.setString(2, creditCard.getOwnerCard());
        statement.setString(3, creditCard.getCurrency());
        statement.setDouble(4, amount);
        statement.setLong(5, creditCard.getCardNumber());
        statement.setInt(6, creditCard.getCustomerId());
        statement.executeUpdate();
    }

    @Override
    public void setTransactionOpeningDeposit(CreditCard creditCard, double amount) throws SQLException {
        String timeOfTransaction = PaymentReceiptServiceImpl.getDateAndTime();
        Connection connection = ConnectionImp.getConnection();
        PreparedStatement statement = connection.prepareStatement(SET_PAYMENT_RECEIPT_DEPOSIT_QUERY);
        statement.setString(1, timeOfTransaction);
        statement.setString(2, creditCard.getOwnerCard());
        statement.setString(3, creditCard.getCurrency());
        statement.setDouble(4, amount);
        statement.setLong(5, creditCard.getCardNumber());
        statement.setInt(6, creditCard.getCustomerId());
        statement.executeUpdate();
    }


    public static String getDateAndTime() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        return dateFormat.format(date);
    }
}