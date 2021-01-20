package by.aleksey.project.ibankingapp.service;

import by.aleksey.project.ibankingapp.io.ConsoleIO;
import by.aleksey.project.ibankingapp.io.IOInterface;
import by.aleksey.project.ibankingapp.model.Account;
import by.aleksey.project.ibankingapp.model.CreditCard;
import by.aleksey.project.ibankingapp.model.Deposit;
import by.aleksey.project.ibankingapp.repository.CreditCardRepository;
import by.aleksey.project.ibankingapp.repository.CreditCardRepositoryImpl;
import by.aleksey.project.ibankingapp.repository.CurrencyRepository;
import by.aleksey.project.ibankingapp.repository.CurrencyRepositoryImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepositServiceImpl implements DepositService {

    private final static String CREATE_NEW_DEPOSIT_IN_BYN = "insert into deposit value (null, 'Интернет депозит в белорусских рублях', ?, ?, ?, ?)";
    private final static String CREATE_NEW_DEPOSIT_IN_USD = "insert into deposit value (null, 'Интернет депозит в долларах', ?, ?, ?, ?)";

    private IOInterface ioInterface = new ConsoleIO();
    private CreditCardRepository creditCardRepository = new CreditCardRepositoryImpl();
    private CreditCardService creditCardService = new CreditCardServiceImpl();
    private CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();
    private PaymentReceiptService paymentReceiptService = new PaymentReceiptServiceImpl();

    @Override
    public void showDeposits(ArrayList<Deposit> deposit) {
        System.out.println("Ваши депозиты:");
        System.out.printf("%-5s%-40s%-14s%-20s%-12s%n", "id", "Название депозита", "Срок депозита", "Процентная ставка", "Сумма");
        System.out.println("-------------------------------------------------------------------------------------");
        for (Deposit d : deposit) {
            System.out.printf("%-5d%-40s%-14d%-20s%-12.2f%n", d.getId(), d.getNameOfDeposit(), d.getTermOfDeposit(), d.getPercentRate(), d.getAmount());
        }
        System.out.println();
    }

    @Override
    public void openNewDeposit(Account account) {

        int termOfDepositInBYN = 0;
        int termOfDepositInUSD = 0;
        double percentRate = 0;
        try {
            // выбираем валюту депозита
            System.out.println(IOInterface.CURRENCY_OF_DEPOSIT);
            int currencyOfDeposit = Integer.parseInt(ioInterface.readLine());

            // выбираем срок депозита
            if (currencyOfDeposit == 1) {
                System.out.println(IOInterface.TERM_OF_DEPOSIT_IN_BYN);
                termOfDepositInBYN = Integer.parseInt(ioInterface.readLine());
                percentRate = DepositServiceImpl.getPercentRate(currencyOfDeposit, termOfDepositInBYN);
            } else if (currencyOfDeposit == 2) {
                System.out.println(IOInterface.TERM_OF_DEPOSIT_IN_USD);
                termOfDepositInUSD = Integer.parseInt(ioInterface.readLine());
                percentRate = DepositServiceImpl.getPercentRate(currencyOfDeposit, termOfDepositInUSD);
            }


            // выбираем карту для перевода денег на депозит
            System.out.println("Выберите ID карты для перевода денег на депозит");
            ArrayList<CreditCard> creditCards = creditCardRepository.getInfoByCard(account);
            creditCardService.showInfoAboutCreditCards(creditCards);
            int idCard = Integer.parseInt(ioInterface.readLine());
            CreditCard creditCard = creditCardRepository.getCreditCardById(idCard);

            // вводим сумму для открытия депозита в белорусских рублях, если карта в рублях
            if (currencyOfDeposit == 1 && creditCard.getCurrency().equals("BYN")) {
                System.out.println("Введите сумму депозита в рублях:");
                double amountOfDeposit = Double.parseDouble(ioInterface.readLine());

                Connection connection = ConnectionImp.getConnection();
                PreparedStatement statement = connection.prepareStatement(CREATE_NEW_DEPOSIT_IN_BYN);
                statement.setInt(1, termOfDepositInBYN);
                statement.setDouble(2, percentRate);
                statement.setDouble(3, amountOfDeposit);
                statement.setInt(4, account.getId());
                statement.executeUpdate();
                creditCardRepository.updateBalanceCard(idCard, (creditCard.getBalanceCard() - amountOfDeposit));
                System.out.println("Уважаемый, " + account.getUserName() + ", благодарим Вас за открытие депозита!");
                paymentReceiptService.setTransactionOpeningDeposit(creditCard,amountOfDeposit);

                // вводим сумму для открытия депозита в долларах, если карта в долларах
            } else if (currencyOfDeposit == 2 && creditCard.getCurrency().equals("USD")) {
                System.out.println("Введите сумму депозита в долларах:");
                double amountOfDeposit = Double.parseDouble(ioInterface.readLine());

                Connection connection = ConnectionImp.getConnection();
                PreparedStatement statement = connection.prepareStatement(CREATE_NEW_DEPOSIT_IN_USD);
                statement.setInt(1, termOfDepositInUSD);
                statement.setDouble(2, percentRate);
                statement.setDouble(3, amountOfDeposit);
                statement.setInt(4, account.getId());
                statement.executeUpdate();
                creditCardRepository.updateBalanceCard(idCard, (creditCard.getBalanceCard() - amountOfDeposit));
                System.out.println("Уважаемый, " + account.getUserName() + ", благодарим Вас за открытие депозита!");
                paymentReceiptService.setTransactionOpeningDeposit(creditCard,amountOfDeposit);

                // вводим сумму для открытия депозита в рублях, если карта в долларах
            } else if (currencyOfDeposit == 1 && creditCard.getCurrency().equals("USD")) {
                System.out.println("Введите сумму депозита в рублях:");
                double amountOfDeposit = Double.parseDouble(ioInterface.readLine());

                Connection connection = ConnectionImp.getConnection();
                PreparedStatement statement = connection.prepareStatement(CREATE_NEW_DEPOSIT_IN_BYN);
                statement.setInt(1, termOfDepositInUSD);
                statement.setDouble(2, percentRate);
                statement.setDouble(3, amountOfDeposit);
                statement.setInt(4, account.getId());
                statement.executeUpdate();
                creditCardRepository.updateBalanceCard(idCard, (creditCard.getBalanceCard() - amountOfDeposit / currencyRepository.getByCurrencyUSD().getPurchase()));
                System.out.println("Уважаемый, " + account.getUserName() + ", благодарим Вас за открытие депозита!");
                paymentReceiptService.setTransactionOpeningDeposit(creditCard,amountOfDeposit);

                // вводим сумму для открытия депозита в долларах, если карта в рублях
            } else if (currencyOfDeposit == 2 && creditCard.getCurrency().equals("BYN")) {
                System.out.println("Введите сумму депозита в долларах:");
                double amountOfDeposit = Double.parseDouble(ioInterface.readLine());

                Connection connection = ConnectionImp.getConnection();
                PreparedStatement statement = connection.prepareStatement(CREATE_NEW_DEPOSIT_IN_USD);
                statement.setInt(1, termOfDepositInUSD);
                statement.setDouble(2, percentRate);
                statement.setDouble(3, amountOfDeposit);
                statement.setInt(4, account.getId());
                statement.executeUpdate();
                creditCardRepository.updateBalanceCard(idCard, (creditCard.getBalanceCard() - amountOfDeposit * currencyRepository.getByCurrencyUSD().getPurchase()));
                System.out.println("Уважаемый, " + account.getUserName() + ", благодарим Вас за открытие депозита!");
                paymentReceiptService.setTransactionOpeningDeposit(creditCard,amountOfDeposit);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    public static double getPercentRate(int currencyOfDeposit, int termOfDeposit) {
        double percentRate = 0;
        if (currencyOfDeposit == 1 && termOfDeposit == 12) {
            percentRate = 13.00;
        } else if (currencyOfDeposit == 1 && termOfDeposit == 24) {
            percentRate = 14.00;
        } else if (currencyOfDeposit == 1 && termOfDeposit == 36) {
            percentRate = 15.00;
        } else if (currencyOfDeposit == 2 && termOfDeposit == 12) {
            percentRate = 0.50;
        } else if (currencyOfDeposit == 2 && termOfDeposit == 24) {
            percentRate = 1.00;
        } else if (currencyOfDeposit == 2 && termOfDeposit == 36) {
            percentRate = 1.50;
        }
        return percentRate;
    }
}