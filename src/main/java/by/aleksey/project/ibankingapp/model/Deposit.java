package by.aleksey.project.ibankingapp.model;

public class Deposit {

    private int id;
    private String nameOfDeposit;
    private int    termOfDeposit;
    private double percentRate;
    private double amount;
    private  int customerId;

    public Deposit(int id, String nameOfDeposit, int termOfDeposit, double percentRate, double amount, int customerId) {
        this.id = id;
        this.nameOfDeposit = nameOfDeposit;
        this.termOfDeposit = termOfDeposit;
        this.percentRate = percentRate;
        this.amount = amount;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public String getNameOfDeposit() {
        return nameOfDeposit;
    }

    public int getTermOfDeposit() {
        return termOfDeposit;
    }

    public double getPercentRate() {
        return percentRate;
    }

    public double getAmount() {
        return amount;
    }

    public int getCustomerId() {
        return customerId;
    }
}
