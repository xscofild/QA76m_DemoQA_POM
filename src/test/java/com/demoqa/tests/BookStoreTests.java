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
 * Обрати внимание: здесь нет полей SidePanel/LoginPage на уровне класса.
 * Объекты создаются прямо в теле теста и связываются цепочкой (Method Chaining).
 * Это чище когда тест линейный и объект нужен только в одном месте.
 *
 * Сравни с AlertsFrameWindowsTests где объекты — поля класса.
 * Оба подхода рабочие; поля класса удобны когда один объект используется в нескольких тестах.
 */
public class BookStoreTests extends TestBase {

    @BeforeEach
    public void precondition() {
        new HomePage(driver).selectBookStore(); // открываем раздел Book Store
    }

    @Test
    public void loginPositiveTest() {
        new SidePanel(driver).selectLogin();  // кликаем Login в боковом меню → переходим на LoginPage

        // enterUserData → clickOnLoginButton (возвращает ProfilePage) → verifyUserName
        new LoginPage(driver)
                .enterUserData("xscofild", "Qwertz123!")
                .clickOnLoginButton()   // → возвращает ProfilePage
                .verifyUserName("xscofild");
    }
}