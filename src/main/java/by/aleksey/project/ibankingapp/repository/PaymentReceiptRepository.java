package by.aleksey.project.ibankingapp.repository;

import by.aleksey.project.ibankingapp.model.Account;
import by.aleksey.project.ibankingapp.model.PaymentReceipt;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentReceiptRepository {

    ArrayList<PaymentReceipt> getByPaymentReceipt(Account account) throws SQLException;

}
