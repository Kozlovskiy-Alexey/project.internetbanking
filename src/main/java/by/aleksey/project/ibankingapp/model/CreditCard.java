package by.aleksey.project.ibankingapp.model;

import java.util.Date;

public class CreditCard {

    private int id;
    private int pinCode;
    private String kindCard;
    private long cardNumber;
    private Date validThrough;
    private String statusCard;
    private String ownerCard;
    private double balanceCard;
    private String currency;
    private String customerId;

    public CreditCard(int id, int pinCode, String kindCard, long cardNumber, Date validThrough, String statusCard, String ownerCard, double balanceCard, String currency, String customerId) {
        this.id = id;
        this.pinCode = pinCode;
        this.kindCard = kindCard;
        this.cardNumber = cardNumber;
        this.validThrough = validThrough;
        this.statusCard = statusCard;
        this.ownerCard = ownerCard;
        this.balanceCard = balanceCard;
        this.currency = currency;
        this.customerId = customerId;
    }

    public CreditCard(int id, long cardNumber, String currency, double balanceCard) {
        this.id = id;
        this.balanceCard = balanceCard;
        this.currency = currency;
        this.cardNumber = cardNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public String getKindCard() {
        return kindCard;
    }

    public void setKindCard(String kindCard) {
        this.kindCard = kindCard;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getValidThrough() {
        return validThrough;
    }

    public void setValidThrough(Date validThrough) {
        this.validThrough = validThrough;
    }

    public String getStatusCard() {
        return statusCard;
    }

    public void setStatusCard(String statusCard) {
        this.statusCard = statusCard;
    }

    public String getOwnerCard() {
        return ownerCard;
    }

    public void setOwnerCard(String ownerCard) {
        this.ownerCard = ownerCard;
    }

    public double getBalanceCard() {
        return balanceCard;
    }

    public void setBalanceCard(double balanceCard) {
        this.balanceCard = balanceCard;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void toStringMy() {
        System.out.printf("%-5d%-8s%-8s%-20s%-15s%-8s%-20s%-10s%-10s%-6s%n", id, pinCode, kindCard, cardNumber, validThrough, statusCard, ownerCard, balanceCard, currency, customerId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard)) return false;
        CreditCard that = (CreditCard) o;
        return getCurrency().equals(that.getCurrency());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
