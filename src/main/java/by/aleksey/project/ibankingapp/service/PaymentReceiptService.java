package by.aleksey.project.ibankingapp.service;

import by.aleksey.project.ibankingapp.model.Account;
import by.aleksey.project.ibankingapp.model.CreditCard;
import by.aleksey.project.ibankingapp.model.PaymentReceipt;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentReceiptService {

    void showHistoryOfTransaction(ArrayList<PaymentReceipt> paymentReceipt);

    public void setTransactionTransferMoneyToOwnCard(CreditCard creditCard, double amount) throws SQLException;

    void setTransactionTransferMoneyToAnotherCard(CreditCard creditCard, double amount) throws SQLException;

    void setTransactionOpeningDeposit(CreditCard creditCard, double amount) throws SQLException;

}
