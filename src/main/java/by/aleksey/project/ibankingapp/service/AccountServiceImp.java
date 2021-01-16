package by.aleksey.project.ibankingapp.service;

import by.aleksey.project.ibankingapp.io.ConsoleIO;
import by.aleksey.project.ibankingapp.io.IOInterface;
import by.aleksey.project.ibankingapp.model.Account;
import by.aleksey.project.ibankingapp.repository.AccountRepository;
import by.aleksey.project.ibankingapp.repository.AccountRepositoryImpl;

import java.sql.SQLException;

public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImp() {
        accountRepository = new AccountRepositoryImpl();
    }

    public Account login(String login, String password) throws SQLException {
        Account account = null;
        account = accountRepository.getByLogin(login);
        if (account != null && account.getPassword().equals(password)) {
            return account;
        } else {
            throw new SQLException("неверный пароль!");
        }
    }
}
