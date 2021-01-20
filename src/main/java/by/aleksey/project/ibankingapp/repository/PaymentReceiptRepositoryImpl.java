package by.aleksey.project.ibankingapp.repository;

import by.aleksey.project.ibankingapp.model.Account;
import by.aleksey.project.ibankingapp.model.PaymentReceipt;
import by.aleksey.project.ibankingapp.service.ConnectionImp;

import java.sql.*;
import java.util.ArrayList;

public class PaymentReceiptRepositoryImpl implements PaymentReceiptRepository {

    private static final String GET_PAYMENT_RECEIPT_BY_ACCOUNT = "select * from paymentreciept where userId = ? order by timetransaction;";

    @Override
    public ArrayList<PaymentReceipt> getByPaymentReceipt(Account account) throws SQLException{
        ArrayList<PaymentReceipt> list = new ArrayList<>();
        Connection connection = ConnectionImp.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_PAYMENT_RECEIPT_BY_ACCOUNT);
        statement.setInt(1, account.getId());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String transaction = resultSet.getString("transaction");
            String timeTransaction = resultSet.getString("timeTransaction");
            String userNameSender = resultSet.getString("userNameSender");
            String currency = resultSet.getString("currency");
            double amount = resultSet.getDouble("amount");
            long cardNumberSender = resultSet.getLong("cardNumberSender");
            list.add(new PaymentReceipt(id, transaction, timeTransaction, userNameSender, cardNumberSender, currency, amount));
        }
        return list;
    }
}
