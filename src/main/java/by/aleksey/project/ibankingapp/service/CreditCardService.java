package by.aleksey.project.ibankingapp.service;

import by.aleksey.project.ibankingapp.model.CreditCard;

import java.util.ArrayList;

public interface CreditCardService {

    void showInfoAboutCreditCards(ArrayList<CreditCard> list);

    void transferMoneyToMyCard();

    void transferMoneyToAnotherCard();
}
