package by.aleksey.project.ibankingapp.model;

import java.sql.Date;

public class PaymentReceipt {

    private int id;
    private  String transaction;
    private String timeTransaction;
    private String userNameSender;
    private String currency;
    private double amount;
    private long cardNumberSender;

    public PaymentReceipt(int id, String transaction, String timeTransaction, String userNameSender, String currency, double amount, long cardNumberSender) {
        this.id = id;
        this.transaction = transaction;
        this.timeTransaction = timeTransaction;
        this.userNameSender = userNameSender;
        this.currency = currency;
        this.amount = amount;
        this.cardNumberSender = cardNumberSender;
    }

    public PaymentReceipt(int id, String transaction, String timeTransaction, String userNameSender, long cardNumberSender, String currency, double amount) {
        this.id = id;
        this.transaction = transaction;
        this.timeTransaction = timeTransaction;
        this.userNameSender = userNameSender;
        this.currency = currency;
        this.amount = amount;
        this.cardNumberSender = cardNumberSender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getTimeTransaction() {
        return timeTransaction;
    }

    public void setTimeTransaction(String timeTransaction) {
        this.timeTransaction = timeTransaction;
    }

    public String getUserNameSender() {
        return userNameSender;
    }

    public void setUserNameSender(String userNameSender) {
        this.userNameSender = userNameSender;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCardNumberSender() {
        return cardNumberSender;
    }

    public void setCardNumberSender(long cardNumberSender) {
        this.cardNumberSender = cardNumberSender;
    }
}
