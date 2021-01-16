package by.aleksey.project.ibankingapp.service;

import by.aleksey.project.ibankingapp.model.Account;
import by.aleksey.project.ibankingapp.model.CreditCard;

import java.sql.SQLException;

public interface AccountService {

    Account login(String login, String password) throws SQLException;
}
