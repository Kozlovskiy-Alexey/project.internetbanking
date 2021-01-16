package by.aleksey.project.ibankingapp.controller;

import by.aleksey.project.ibankingapp.io.ConsoleIO;
import by.aleksey.project.ibankingapp.io.IOInterface;
import by.aleksey.project.ibankingapp.model.Account;
import by.aleksey.project.ibankingapp.model.CreditCard;
import by.aleksey.project.ibankingapp.model.Currency;
import by.aleksey.project.ibankingapp.repository.CreditCardRepository;
import by.aleksey.project.ibankingapp.repository.CreditCardRepositoryImpl;
import by.aleksey.project.ibankingapp.repository.CurrencyRepository;
import by.aleksey.project.ibankingapp.repository.CurrencyRepositoryImpl;
import by.aleksey.project.ibankingapp.service.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class IBankingAppController {

    private final IOInterface ioInterface;
    private final AccountService accountService;
    private final CreditCardRepository creditCardRepository;
    private final CreditCardService creditCardService;
    private final CurrencyRepository currencyRepository;
    private final CurrencyServiceImpl currencyService;

    public IBankingAppController() {
        this.ioInterface = new ConsoleIO();
        this.accountService = new AccountServiceImp();
        this.creditCardRepository = new CreditCardRepositoryImpl();
        this.creditCardService = new CreditCardServiceImpl();
        this.currencyRepository = new CurrencyRepositoryImpl();
        this.currencyService = new CurrencyServiceImpl();

    }

    public void start() {
        while (true) {
            System.out.println("-----------------------");
            System.out.println("Вход в систему IBanking");
            System.out.println("-----------------------");
            System.out.println("Введите логин");

            Account account = null;
            while (account == null) {
                try {
                    String login = ioInterface.readLine();
                    System.out.println("Введите пароль");
                    String password = ioInterface.readLine();
                    account = accountService.login(login, password);
                } catch (IOException e) {
                    e.getStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            boolean cont = true;
            while (cont) {
                cont = mainProcess(account);
            }
        }
    }

    private boolean mainProcess(Account account) {
        String input = "";
        String input1 = "";
        String input2 = "";
        String input3 = "";
        ArrayList<CreditCard> list = new ArrayList<>();
        do {
            System.out.println(IOInterface.MENU);
            try {
                input = ioInterface.readLine();
            } catch (IOException e) {
                System.out.println("введите число от 1 до 6");
            }
            switch (input) {
                case "1":
                    try {
                        list = creditCardRepository.getInfoByCard(account);
                        creditCardService.showInfoAboutCreditCards(list);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    do {
                        System.out.println(IOInterface.MENU_TRANSFER_TO_CARD);
                        try {
                            input1 = ioInterface.readLine();
                        } catch (IOException e) {
                            System.out.println("введите число от 1 до 3");
                        }
                        switch (input1) {
                            case "1":
                                try {
                                    list = creditCardRepository.getInfoByCard(account);
                                    creditCardService.showInfoAboutCreditCards(list);
                                    creditCardService.transferMoneyToMyCard();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "2":
                                try {
                                    list = creditCardRepository.getInfoByCard(account);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                creditCardService.showInfoAboutCreditCards(list);
                                creditCardService.transferMoneyToAnotherCard();
                                break;
                        }
                    } while (!input1.equals("3"));
                    break;
                case "3":
                    System.out.println("чеки оплат");
                    break;
                case "4":
                    System.out.println("электронные депозиты");
                    break;
                case "5":
                    System.out.println("курсы валют");
                    try {
                        Currency currencyUSD = currencyRepository.getByCurrencyUSD();
                        Currency currencyEUR = currencyRepository.getByCurrencyEUR();
                        currencyService.showCurrencyRate(currencyUSD, currencyEUR);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;
                case "6":
                    System.out.println("выход...");
                    break;
                default:
                    System.out.println("введите число от 1 до 6");
                    break;
            }
        } while (!input.equals("6"));
        return false;
    }
}