package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.JSExecutor;
import com.demoqa.pages.SidePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Тесты демонстрирующие работу с JavaScript через JavascriptExecutor
// Покрывает: заполнение полей через JS, клик через JS, навигация через JS
// @BeforeEach — переходим в раздел Elements
public class JSElementsTests extends TestBase {

    SidePanel sidePanel;

    @BeforeEach
    public void precondition() {
        new HomePage(driver).goToElements();
        sidePanel = new SidePanel(driver);
    }

    // Тест: заполнение формы Text Box через JavascriptExecutor
    // Шаги:
    //  1. Переходим на страницу Text Box
    //  2. Заполняем поля через JS (document.getElementById)
    //  3. Нажимаем Submit через JS (document.querySelector.click())
    //  4. Получаем innerText и URL через JS
    //  5. Обновляем страницу через JS (history.go(0))
    //  6. Переходим на новую страницу через JS (window.location)
    //  7. Получаем title страницы через JS (document.title)
    @Test
    public void jsExecutorTest() {
        sidePanel.selectTextBox();
        new JSExecutor(driver)
                .enterPersonalData("Olga Podgornaya", "Test@gmail.com")
                .clickOnSubmitButton()
                .getInnerText()
                .verifyURL()
                .refreshPage()
                .navigateToNewPage("https://telranedu.web.app") // открывает новую ссылку в том же окне
                .verifyNewPageFaveIconTitle();
    }
}