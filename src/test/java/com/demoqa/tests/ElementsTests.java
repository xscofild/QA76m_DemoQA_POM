package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.elements.ButtonsPage;
import com.demoqa.pages.elements.TextBoxPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Тесты для раздела "Elements"
// Покрывает: Double Click, Right Click, Copy-Paste через Actions
// @BeforeEach — создаём Page Objects и переходим в раздел Elements
public class ElementsTests extends TestBase {

    SidePanel sidePanel;
    ButtonsPage buttonsPage;
    TextBoxPage textBoxPage;

    @BeforeEach
    public void precondition() {
        sidePanel = new SidePanel(driver);
        buttonsPage = new ButtonsPage(driver);
        new HomePage(driver).selectElements();
        textBoxPage = new TextBoxPage(driver);
    }

    // Тест: двойной клик по кнопке
    // actions.doubleClick() — симулирует двойной клик мышью
    // Ожидаемый результат: появляется сообщение "double click"
    @Test
    public void doubleClickTest() {
        sidePanel.getButtons();
        buttonsPage.doubleClick()
                .verifyDoubleClick("double click");
    }

    // Тест: клик правой кнопкой мыши
    // dispatchEvent contextmenu — надёжнее чем actions.contextClick()
    // Ожидаемый результат: появляется сообщение "right click"
    @Test
    public void rightClickTest() {
        sidePanel.getButtons();
        buttonsPage.rightClick()
                .verifyRightClick("right click");
    }

    // Тест: копирование адреса через Ctrl+C и вставка через Ctrl+V
    // Шаги:
    //  1. Вводим адрес в Current Address
    //  2. Ctrl+A → выделяем, Ctrl+C → копируем, Tab → переходим, Ctrl+V → вставляем
    //  3. Нажимаем Submit
    //  4. Проверяем что оба поля содержат одинаковый адрес
    @Test
    public void copyPastTest() {
        sidePanel.selectTextBox();
        textBoxPage.copyPast("Friedrichstr 12, Berlin")
                .clickOnSubmitButton()
                .verifyAddress();
    }
}
