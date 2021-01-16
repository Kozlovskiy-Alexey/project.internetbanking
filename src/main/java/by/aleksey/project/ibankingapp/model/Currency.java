package by.aleksey.project.ibankingapp.model;

public class Currency {

    private int id;
    private int amount;
    private String currency;
    private double purchase;
    private double selling;

    public Currency(int id, int amount, String currency, double purchase, double selling) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.purchase = purchase;
        this.selling = selling;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getPurchase() {
        return purchase;
    }

    public void setPurchase(double purchase) {
        this.purchase = purchase;
    }

    public double getSelling() {
        return selling;
    }

    public void setSelling(double selling) {
        this.selling = selling;
    }
}
