package by.aleksey.project.ibankingapp.service;

import by.aleksey.project.ibankingapp.io.ConsoleIO;
import by.aleksey.project.ibankingapp.io.IOInterface;
import by.aleksey.project.ibankingapp.model.CreditCard;
import by.aleksey.project.ibankingapp.model.Currency;
import by.aleksey.project.ibankingapp.repository.CreditCardRepository;
import by.aleksey.project.ibankingapp.repository.CreditCardRepositoryImpl;
import by.aleksey.project.ibankingapp.repository.CurrencyRepository;
import by.aleksey.project.ibankingapp.repository.CurrencyRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreditCardServiceImpl implements CreditCardService {

    private IOInterface ioInterface = new ConsoleIO();
    private CurrencyRepository currencyRate = new CurrencyRepositoryImpl();
    private CreditCardRepository creditCardRepository = new CreditCardRepositoryImpl();
    private PaymentReceiptService paymentReceiptService = new PaymentReceiptServiceImpl();

    // показать информацию по всем картам пользователя
    @Override
    public void showInfoAboutCreditCards(ArrayList<CreditCard> list) {

        System.out.println("Карты:");
        System.out.printf("%-5s%-8s%-8s%-20s%-15s%-8s%-20s%-10s%-10s%-6s%n", "ID", "PIN-код", "Карта", "Номер карты", "Срок действия", "Статус", "Владелец ФИО", "Остаток", "Валюта", "Клиент ID");
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        for (CreditCard c : list) {
            System.out.printf("%-5d%-8s%-8s%-20s%-15s%-8s%-20s%-10s%-10s%-6s%n", c.getId(), c.getPinCode(), c.getKindCard(), c.getCardNumber(), c.getValidThrough(), c.getStatusCard(), c.getOwnerCard(), c.getBalanceCard(), c.getCurrency(), c.getCustomerId());
        }
    }

    // перевод средств на свою карту по номеру Id карты
    @Override
    public void transferMoneyToMyCard() {

        try {
            System.out.println("Выберите id карты отправителя");
            int idCardSender = Integer.parseInt(ioInterface.readLine());
            System.out.println("Выберите id карты получателя");
            int idCardReceiver = Integer.parseInt(ioInterface.readLine());

            CreditCard creditCardSender = creditCardRepository.getCreditCardById(idCardSender);
            CreditCard creditCardReceiver = creditCardRepository.getCreditCardById(idCardReceiver);

            Currency currencyUSD = currencyRate.getByCurrencyUSD();

            // транзакция, если валюта отправителя BYN и валюта получателя BYN
            if (creditCardSender.getCurrency().equals("BYN") && creditCardReceiver.getCurrency().equals("BYN")) {
                System.out.println("введите сумму в рублях");
                int amount = Integer.parseInt(ioInterface.readLine());
                creditCardSender.setBalanceCard(creditCardSender.getBalanceCard() - amount);
                creditCardReceiver.setBalanceCard(creditCardReceiver.getBalanceCard() + amount);
                creditCardRepository.updateBalanceCard(idCardSender, creditCardSender.getBalanceCard());
                creditCardRepository.updateBalanceCard(idCardReceiver, creditCardReceiver.getBalanceCard());
                paymentReceiptService.setTransactionTransferMoneyToOwnCard(creditCardSender,amount);
                paymentReceiptService.setTransactionTransferMoneyToAnotherCard(creditCardReceiver,amount);

                // транзакция, если валюта отправителя USD и валюта получателя USD
            } else if (creditCardSender.getCurrency().equals("USD") && creditCardReceiver.getCurrency().equals("USD")) {
                System.out.println("введите сумму в долларах");
                int amount = Integer.parseInt(ioInterface.readLine());
                creditCardSender.setBalanceCard(creditCardSender.getBalanceCard() - amount);
                creditCardReceiver.setBalanceCard(creditCardReceiver.getBalanceCard() + amount);
                creditCardRepository.updateBalanceCard(idCardSender, creditCardSender.getBalanceCard());
                creditCardRepository.updateBalanceCard(idCardReceiver, creditCardReceiver.getBalanceCard());
                paymentReceiptService.setTransactionTransferMoneyToOwnCard(creditCardSender,amount);
                paymentReceiptService.setTransactionTransferMoneyToAnotherCard(creditCardReceiver,amount);

                // транзакция, если валюта отправителя BYN и валюта получателя USD
            } else if (creditCardSender.getCurrency().equals("BYN") && creditCardReceiver.getCurrency().equals("USD")) {
                System.out.println("введите сумму в долларах");
                int amount = Integer.parseInt(ioInterface.readLine());
                creditCardSender.setBalanceCard(creditCardSender.getBalanceCard() - amount * currencyUSD.getPurchase());
                creditCardReceiver.setBalanceCard(creditCardReceiver.getBalanceCard() + amount);
                creditCardRepository.updateBalanceCard(idCardSender, creditCardSender.getBalanceCard());
                creditCardRepository.updateBalanceCard(idCardReceiver, creditCardReceiver.getBalanceCard());
                paymentReceiptService.setTransactionTransferMoneyToOwnCard(creditCardSender,amount);
                paymentReceiptService.setTransactionTransferMoneyToAnotherCard(creditCardReceiver,amount);

                // транзакция, если валюта отправителя USD и валюта получателя BYN
            } else if (creditCardSender.getCurrency().equals("USD") && creditCardReceiver.getCurrency().equals("BYN")) {
                System.out.println("введите сумму в рублях");
                int amount = Integer.parseInt(ioInterface.readLine());
                creditCardSender.setBalanceCard(creditCardSender.getBalanceCard() - amount / currencyUSD.getPurchase());
                creditCardReceiver.setBalanceCard(creditCardReceiver.getBalanceCard() + amount);
                creditCardRepository.updateBalanceCard(idCardSender, creditCardSender.getBalanceCard());
                creditCardRepository.updateBalanceCard(idCardReceiver, creditCardReceiver.getBalanceCard());
                paymentReceiptService.setTransactionTransferMoneyToOwnCard(creditCardSender,amount);
                paymentReceiptService.setTransactionTransferMoneyToAnotherCard(creditCardReceiver,amount);
            }
        } catch (SQLException | IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // перевод средств по на чужую карту по номеру карты
    @Override
    public void transferMoneyToAnotherCard() {

        try {
            System.out.println("Выберите id вашей карты");
            int idCardSender = Integer.parseInt(ioInterface.readLine());
            System.out.println("Введите номер карты получателя");
            long idCardReceiver = Long.parseLong(ioInterface.readLine());

            CreditCard creditCardSender = creditCardRepository.getCreditCardById(idCardSender);
            CreditCard creditCardReceiver = creditCardRepository.getCreditCardByCardNumber(idCardReceiver);

            Currency currencyUSD = currencyRate.getByCurrencyUSD();

            // транзакция, если валюта отправителя BYN и валюта получателя BYN
            if (creditCardSender.getCurrency().equals("BYN") && creditCardReceiver.getCurrency().equals("BYN")) {
                System.out.println("введите сумму в рублях");
                double amount = Double.parseDouble(ioInterface.readLine());
                creditCardSender.setBalanceCard(creditCardSender.getBalanceCard() - amount);
                creditCardReceiver.setBalanceCard(creditCardReceiver.getBalanceCard() + amount);
                creditCardRepository.updateBalanceCard(creditCardSender.getId(), creditCardSender.getBalanceCard());
                creditCardRepository.updateBalanceCard(creditCardReceiver.getId(), creditCardReceiver.getBalanceCard());
                paymentReceiptService.setTransactionTransferMoneyToOwnCard(creditCardSender,amount);
                paymentReceiptService.setTransactionTransferMoneyToAnotherCard(creditCardReceiver,amount);

                // транзакция, если валюта отправителя USD и валюта получателя USD
            } else if (creditCardSender.getCurrency().equals("USD") && creditCardReceiver.getCurrency().equals("USD")) {
                System.out.println("Введите сумму в долларах");
                double amount = Double.parseDouble(ioInterface.readLine());
                creditCardSender.setBalanceCard(creditCardSender.getBalanceCard() - amount);
                creditCardReceiver.setBalanceCard(creditCardReceiver.getBalanceCard() + amount);
                creditCardRepository.updateBalanceCard(creditCardSender.getId(), creditCardSender.getBalanceCard());
                creditCardRepository.updateBalanceCard(creditCardReceiver.getId(), creditCardReceiver.getBalanceCard());
                paymentReceiptService.setTransactionTransferMoneyToOwnCard(creditCardSender,amount);
                paymentReceiptService.setTransactionTransferMoneyToAnotherCard(creditCardReceiver,amount);

                // транзакция, если валюта отправителя BYN и валюта получателя USD
            } else if (creditCardSender.getCurrency().equals("BYN") && creditCardReceiver.getCurrency().equals("USD")) {
                System.out.println("Введите сумму в долларах");
                double amount = Double.parseDouble(ioInterface.readLine());
                creditCardSender.setBalanceCard(creditCardSender.getBalanceCard() - amount * currencyUSD.getPurchase());
                creditCardReceiver.setBalanceCard(creditCardReceiver.getBalanceCard() + amount);
                creditCardRepository.updateBalanceCard(creditCardSender.getId(), creditCardSender.getBalanceCard());
                creditCardRepository.updateBalanceCard(creditCardReceiver.getId(), creditCardReceiver.getBalanceCard());
                paymentReceiptService.setTransactionTransferMoneyToOwnCard(creditCardSender,amount);
                paymentReceiptService.setTransactionTransferMoneyToAnotherCard(creditCardReceiver,amount);

                // транзакция, если валюта отправителя USD и валюта получателя BYN
            } else if (creditCardSender.getCurrency().equals("USD") && creditCardReceiver.getCurrency().equals("BYN")) {
                System.out.println("Введите сумму в рублях");
                double amount = Double.parseDouble(ioInterface.readLine());
                creditCardSender.setBalanceCard(creditCardSender.getBalanceCard() - amount / currencyUSD.getPurchase());
                creditCardReceiver.setBalanceCard(creditCardReceiver.getBalanceCard() + amount);
                creditCardRepository.updateBalanceCard(creditCardSender.getId(), creditCardSender.getBalanceCard());
                creditCardRepository.updateBalanceCard(creditCardReceiver.getId(), creditCardReceiver.getBalanceCard());
                paymentReceiptService.setTransactionTransferMoneyToOwnCard(creditCardSender,amount);
                paymentReceiptService.setTransactionTransferMoneyToAnotherCard(creditCardReceiver,amount);
            }
        } catch (SQLException | IOException e) {
            e.getStackTrace();
        }
    }
}


