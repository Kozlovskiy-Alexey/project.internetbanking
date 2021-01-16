package by.aleksey.project.ibankingapp.repository;

import by.aleksey.project.ibankingapp.model.Account;

import java.sql.SQLException;

public interface AccountRepository {

    Account getByLogin(String login) throws SQLException;
}
