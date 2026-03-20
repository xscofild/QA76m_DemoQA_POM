package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.elements.BrokenLinksImagePage;
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
// Покрывает: Double Click, Right Click, Copy-Paste, Text Box (3 варианта), Broken Links, Broken Images
// @BeforeEach — инициализируем Page Objects и переходим в раздел Elements
public class ElementsTests extends TestBase {

    SidePanel sidePanel;
    ButtonsPage buttonsPage;
    TextBoxPage textBoxPage;
    BrokenLinksImagePage broken;

    @BeforeEach
    public void precondition() {
        new HomePage(driver).goToElements();
        sidePanel = new SidePanel(driver);
        buttonsPage = new ButtonsPage(driver);
        textBoxPage = new TextBoxPage(driver);
        broken = new BrokenLinksImagePage(driver);
    }

    // ============================================================
    // TESTS — кнопки (Actions)
    // ============================================================

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
    // actions.contextClick() — симулирует правый клик мышью
    // Ожидаемый результат: появляется сообщение "right click"
    @Test
    public void rightClickTest() {
        sidePanel.getButtons();
        buttonsPage.rightClick()
                .verifyRightClick("right click");
    }

    // ============================================================
    // TESTS — Text Box
    // ============================================================

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
        sidePanel.selectTextBox();
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
        sidePanel.selectTextBox();
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
        sidePanel.selectTextBox();
        textBoxPage.enterPersonalData(name, email, address)
                .clickOnSubmitButton()
                .verifyPersonalData(name, email, address);
    }

    // ============================================================
    // TESTS — Broken Links & Images
    // ============================================================

    // Тест: выводит в консоль все ссылки на странице (текст или href)
    @Test
    public void getAllLinksTest() {
        sidePanel.getBrokenLinksImages();
        broken.getAllLinks();
    }

    // Тест: проходит по всем ссылкам и проверяет их HTTP статус
    // Результат выводится в консоль: OK / Redirect / Broken Link
    @Test
    public void checkBrokenLinksTest() {
        sidePanel.getBrokenLinksImages();
        broken.checkBrokenLinks();
    }

    // Тест: проверяет все изображения на странице
    // Выводит количество картинок, HTTP статус и статус загрузки каждой
    @Test
    public void checkBrokenImagesTest() {
        sidePanel.getBrokenLinksImages();
        broken.checkBrokenImages();
    }
}