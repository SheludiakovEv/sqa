package ru.stqa.montis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.montis.common.CommonFunctions;

import java.util.regex.Pattern;
import java.time.Duration;

public class UserRegistrationTests extends TestBase{

    @Test
    void canRegisterUserHttp() {
        // Генерируем данные внутри теста
        String username = CommonFunctions.randomString(8);
        String password = "password";
        String email = String.format("%s@localhost", username);

        // Создаем почтовый ящик для пользователя на сервере James
        app.jamesCli().addUser(email, password);

        // Заполняем форму создания и отправляем (браузер)
        app.session().registration(username, email);

        // Ждем почту (MailHelper)
        var messages = app.mail().receive(email, password, Duration.ofSeconds(30));
        Assertions.assertFalse(messages.isEmpty(), "No email received");
        System.out.println(messages);

        // Получаем текст первого письма
        String mailContent = messages.get(0).content();

        // Извлекаем ссылку из письма с помощью регулярного выражения
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(mailContent);
        Assertions.assertTrue(matcher.find(), "Not found in email");
        String url = matcher.group(); // Теперь переменная url объявлена и инициализирована!

        // Проходим по ссылке и завершаем регистрацию (браузер)
        app.session().finishRegistration(url, username, password);

        // Проверяем, что пользователь залогинился (HttpSessionHelper)
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggetIn());
    }
}