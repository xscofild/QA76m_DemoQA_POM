package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.elements.ButtonsPage;
import com.demoqa.pages.elements.TextBoxPage;
import com.demoqa.utils.MyArgumentsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

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
        textBoxPage.copyPaste("Friedrichstr 12, Berlin")
                .clickOnSubmitButton()
                .verifyAddress();
    }

    // ===== ВАРИАНТ 1: данные из внешнего класса-поставщика =====
    // ArgumentsProvider позволяет вынести тестовые данные в отдельный класс
    // Удобно когда данных много или они требуют сложной подготовки
    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    public void textBoxWithArgumentsProviderTest(String name, String email, String address) {
        // Открываем страницу Text Box через боковую панель навигации
        sidePanel.selectTextBox();

        // Вводим имя, email и адрес в форму → отправляем → проверяем, что данные отобразились корректно
        textBoxPage.enterPersonalData(name, email, address)
                .clickOnSubmitButton()
                .verifyPersonalData(name, email, address);
    }


    // ===== ВАРИАНТ 2: данные прямо в аннотации @CsvSource =====
    // Удобно для небольшого количества наборов — данные видны прямо в тесте
    // Каждая строка = один запуск теста: имя, email, адрес
    @ParameterizedTest
    @CsvSource({
            "John, john@mail.com, Berlin",
            "Anna, anna@mail.com, Munich"
    })
    public void textBoxWithCsvSourceTest(String name, String email, String address) {
        // Открываем страницу Text Box через боковую панель навигации
        sidePanel.selectTextBox();

        // Вводим имя, email и адрес в форму → отправляем → проверяем, что данные отобразились корректно
        textBoxPage.enterPersonalData(name, email, address)
                .clickOnSubmitButton()
                .verifyPersonalData(name, email, address);
    }


    // ===== ВАРИАНТ 3: данные из внешнего CSV-файла =====
    // Данные хранятся в src/test/resources/PersonalData.csv
    // Удобно когда наборов данных много или их редактируют не разработчики
    @ParameterizedTest
    @CsvFileSource(resources = "/PersonalData.csv")
    public void textBoxWithCsvFileTest(String name, String email, String address) {
        // Открываем страницу Text Box через боковую панель навигации
        sidePanel.selectTextBox();

        // Вводим имя, email и адрес в форму → отправляем → проверяем, что данные отобразились корректно
        textBoxPage.enterPersonalData(name, email, address)
                .clickOnSubmitButton()
                .verifyPersonalData(name, email, address);
    }


}
