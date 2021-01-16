package by.aleksey.project.ibankingapp.Test;

import by.aleksey.project.ibankingapp.io.ConsoleIO;
import by.aleksey.project.ibankingapp.io.IOInterface;
import by.aleksey.project.ibankingapp.model.CreditCard;
import by.aleksey.project.ibankingapp.repository.CreditCardRepository;
import by.aleksey.project.ibankingapp.repository.CreditCardRepositoryImpl;


import java.io.IOException;
import java.sql.*;

public class Test {



    public static void main(String[] args) {

        CreditCardRepository creditCardRepository = new CreditCardRepositoryImpl();

        try {
            IOInterface ioInterface = new ConsoleIO();
            System.out.println("Выберите карту отправитель");
            int idCardSender = Integer.parseInt(ioInterface.readLine());
            System.out.println("Выберите карту получатель");
            int idCardReceiver = Integer.parseInt(ioInterface.readLine());

            CreditCard creditCardSender = creditCardRepository.getCreditCardById(idCardSender);
            CreditCard creditCardReceiver = creditCardRepository.getCreditCardById(idCardSender);


            // транзакция, если валюта отправителя BYN и валюта получателя BYN
            System.out.println(creditCardSender.getCurrency().equals("BYN"));
            System.out.println(creditCardReceiver.getCurrency().equals("BYN"));

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


    }
}