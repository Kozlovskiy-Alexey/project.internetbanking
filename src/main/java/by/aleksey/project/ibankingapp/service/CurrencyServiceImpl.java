package by.aleksey.project.ibankingapp.service;
import by.aleksey.project.ibankingapp.model.Currency;

public class CurrencyServiceImpl {

    public void showCurrencyRate(Currency currency1, Currency currency2) {
        System.out.println("Курсы валют:");
        System.out.printf("%-12s%-8s%-10s%-10s%n", "Количество", "Валюта", "Покупка", "Продажа");
        System.out.println("-----------------------------------------");
        System.out.printf("%-12s%-8s%-10s%-10s%n", currency1.getAmount(), currency1.getCurrency(), currency1.getPurchase(), currency1.getSelling());
        System.out.printf("%-12s%-8s%-10s%-10s%n", currency2.getAmount(), currency2.getCurrency(), currency2.getPurchase(), currency2.getSelling());
    }
}
