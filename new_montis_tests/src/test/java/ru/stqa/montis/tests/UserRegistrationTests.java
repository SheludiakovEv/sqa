package ru.stqa.montis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase{

    @Test
    void canRegisterUser(String username){
        var email = String.format("%s@lockalhost", username);
        //создать пользователя адрес на почтовом сервере (JamesHelper)
        //Заполняеем форму создания и отправляем (браузер)
        //ждем почту(MailHelper)
         //извлекаем ссылку
        //проходим по ссылке  и завершаем регистрацию (браузер)
        //проверяем что пользователь залогинился (HttpSessionHelper)
    }
}
