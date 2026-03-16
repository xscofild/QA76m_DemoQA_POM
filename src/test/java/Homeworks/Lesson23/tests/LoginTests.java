package Homeworks.Lesson23.tests;

import Homeworks.Lesson23.core.TestBase;
import Homeworks.Lesson23.pages.HomePage;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    // Тест сценарий:
    //  1. Переходим на страницу Form Authentication
    //  2. Вводим корректные логин и пароль
    //  3. Нажимаем Login
    //  4. Проверяем что появилось сообщение "You logged into a secure area"
    @Test
    public void loginPositiveTest() {
        new HomePage(driver)
                .goToLoginPage()
                .enterCredentials("tomsmith", "SuperSecretPassword!")
                .clickLogin()
                .verifyLoginSuccess();
    }

    // Тест сценарий:
    //  1. Переходим на страницу Form Authentication
    //  2. Вводим корректный логин и неверный пароль
    //  3. Нажимаем Login
    //  4. Проверяем что появилось сообщение "Your password is invalid"
    @Test
    public void loginNegativeTest() {
        new HomePage(driver)
                .goToLoginPage()
                .enterCredentials("tomsmith", "wrongpassword")
                .clickLogin()
                .verifyLoginFailed();
    }
}
