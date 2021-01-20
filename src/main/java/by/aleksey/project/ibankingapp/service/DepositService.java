package by.aleksey.project.ibankingapp.service;

import by.aleksey.project.ibankingapp.model.Account;
import by.aleksey.project.ibankingapp.model.CreditCard;
import by.aleksey.project.ibankingapp.model.Deposit;

import java.util.ArrayList;

public interface DepositService {

    void showDeposits(ArrayList<Deposit> deposit);

    void openNewDeposit(Account account);
}
