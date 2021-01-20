package by.aleksey.project.ibankingapp.repository;

import by.aleksey.project.ibankingapp.model.Account;
import by.aleksey.project.ibankingapp.model.Deposit;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DepositRepository {

    ArrayList<Deposit> getByDeposit(Account account) throws SQLException;
}
