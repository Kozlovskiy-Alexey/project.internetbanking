package by.aleksey.project.ibankingapp.repository;

import by.aleksey.project.ibankingapp.model.Account;
import by.aleksey.project.ibankingapp.model.CreditCard;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CreditCardRepository {

    CreditCard getCreditCardById(int creditCardId) throws SQLException;

    CreditCard getCreditCardByCardNumber(long creditCardNumber) throws SQLException;

    void updateBalanceCard(int id, double amount) throws SQLException;

    ArrayList<CreditCard> getInfoByCard(Account account) throws SQLException;
}
