package by.aleksey.project.ibankingapp.repository;

import by.aleksey.project.ibankingapp.model.Currency;

import java.sql.SQLException;

public interface CurrencyRepository {

    Currency getByCurrencyUSD() throws SQLException;

    Currency getByCurrencyEUR() throws SQLException;
}
