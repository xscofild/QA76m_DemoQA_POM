package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.bookStore.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * BookStoreTests — тесты для раздела Book Store.
 *
 * @BeforeEach — выполняется перед каждым тестом (предусловие)
 * @Test       — сам тест: шаги пользователя + проверка результата
 *
 * Method Chaining: каждый метод возвращает следующий Page Object,
 * что позволяет писать тест как читаемую цепочку шагов.
 */
public class BookStoreTests extends TestBase {

    // Открывает раздел Book Store перед каждым тестом
    @BeforeEach
    public void precondition() {
        new HomePage(driver).selectBookStore();
    }

    @Test
    public void loginPositiveTest() {
        // Шаг 1: В боковом меню кликаем Login
        new SidePanel(driver).selectLogin();

        // Шаг 2: Вводим данные → кликаем Login → проверяем имя на странице профиля
        new LoginPage(driver)
                .enterUserData("xscofild", "Qwertz123!")
                .clickOnLoginButton()
                .verifyUserName("xscofild");
    }
}