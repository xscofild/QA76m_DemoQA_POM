package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.bookStore.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Тесты для раздела "Book Store Application"
// Покрывает: позитивный тест логина с валидными данными
// @BeforeEach — переходим в раздел Book Store
public class BookStoreTests extends TestBase {

    @BeforeEach
    public void precondition() {
        new HomePage(driver).goToBookStore();
    }

    // Тест: успешный логин с корректными данными
    // Шаги:
    //  1. Переходим на страницу Login через SidePanel
    //  2. Вводим логин и пароль через enterUserData()
    //  3. Нажимаем Login → открывается ProfilePage
    //  4. Проверяем что имя пользователя отображается на странице профиля
    @Test
    public void loginPositiveTest() {
        new SidePanel(driver).selectLogin();
        new LoginPage(driver).enterUserData("Olga2727", "Qwerty123!")
                .clickOnLoginButton()
                .verifyUserName("Olga2727");
    }
}