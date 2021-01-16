package by.aleksey.project.ibankingapp.io;

import java.io.IOException;

public interface IOInterface {

    String MENU =
            "------------------------\n" +
                    "1. Карты\n" +
                    "2. Переводы\n" +
                    "3. Чеки оплат\n" +
                    "4. Электронные депозиты\n" +
                    "5. Курсы валют\n" +
                    "6. Выход";

    String MENU_TRANSFER_TO_CARD =
            "**************\n" +
                    "1. На свою карту\n" +
                    "2. По номеру карты\n" +
                    "3. Назад";

    String readLine() throws IOException;

}
