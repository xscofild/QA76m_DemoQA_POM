package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertTrue;

// Страница "Text Box" в разделе Elements на DemoQA
//
// Демонстрирует два подхода к заполнению формы:
//  1. enterPersonalData() — прямой ввод значений в каждое поле
//  2. copyPast()          — ввод в одно поле + копирование через Actions (Ctrl+C / Ctrl+V)
//
// После нажатия Submit страница отображает введённые данные в блоке .border
// Методы verify* проверяют корректность отображения
public class TextBoxPage extends BasePage {

    public TextBoxPage(WebDriver driver) {
        super(driver);
    }

    // ===================== INPUT FIELDS =====================

    // Поле ввода имени пользователя
    @FindBy(id = "userName")
    WebElement userName;

    // Поле ввода email
    @FindBy(id = "userEmail")
    WebElement userEmail;

    // Поле "Current Address" (текущий адрес)
    @FindBy(id = "currentAddress")
    WebElement currentAddress;

    // Поле "Permanent Address" (постоянный адрес)
    @FindBy(id = "permanentAddress")
    WebElement permanentAddress;

    // Кнопка Submit — отправляет форму и показывает блок с результатами
    @FindBy(id = "submit")
    WebElement submit;

    // ==================== OUTPUT FIELDS =====================
    // Блок .border появляется после нажатия Submit и содержит введённые данные

    // Отображаемое имя после Submit
    @FindBy(css = ".border #name")
    WebElement outputName;

    // Отображаемый email после Submit
    @FindBy(css = ".border #email")
    WebElement outputEmail;

    // Отображаемый текущий адрес после Submit
    @FindBy(css = ".border #currentAddress")
    WebElement currentAddressResult;

    // Отображаемый постоянный адрес после Submit
    @FindBy(css = ".border #permanentAddress")
    WebElement permanentAddressResult;

    // ======================== ACTIONS =======================

    // Заполняет все поля формы напрямую
    // Оба адреса получают одно и то же значение — для последующей проверки через assertEquals
    // Возвращает this для fluent-цепочки вызовов
    public TextBoxPage enterPersonalData(String name, String email, String address) {
        type(userName, name);
        type(userEmail, email);
        type(currentAddress, address);    // текущий адрес
        type(permanentAddress, address);  // постоянный адрес — то же значение
        return this;
    }

    // Вводит адрес в Current Address, копирует и вставляет в Permanent Address через Actions
    // Цепочка клавиш:
    //  Ctrl+A — выделить всё содержимое поля
    //  Ctrl+C — скопировать в буфер обмена
    //  Tab    — переключить фокус на следующее поле (Permanent Address)
    //  Ctrl+V — вставить из буфера обмена
    // Примечание: Actions работают с текущим активным элементом в фокусе
    public TextBoxPage copyPaste(String address) {
        type(currentAddress, address);
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
        actions.sendKeys(Keys.TAB).perform();
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
        return this;
    }

    // Нажимает кнопку Submit
    // После клика страница показывает блок .border с введёнными данными
    public TextBoxPage clickOnSubmitButton() {
        click(submit);
        return this;
    }

    // ====================== ASSERTIONS ======================

    // Проверяет что блок .border содержит корректные имя, email и адрес
    // contains() — частичное совпадение, т.к. вывод содержит префикс ("Name: John" и т.д.)
    public TextBoxPage verifyPersonalData(String name, String email, String address) {
        assertTrue(outputName.getText().contains(name));
        assertTrue(outputEmail.getText().contains(email));
        assertTrue(currentAddressResult.getText().contains(address));
        assertTrue(permanentAddressResult.getText().contains(address));
        return this;
    }

    // Проверяет что Current Address и Permanent Address содержат одинаковое значение
    // split(":", 2) — разбивает текст по первому двоеточию (максимум 2 части)
    // Второй аргумент 2 защищает от ошибки если в адресе есть двоеточие ("New York: 5th Ave")
    // current[1] и permanent[1] — часть строки после двоеточия (сам адрес)
    public TextBoxPage verifyAddress() {
        String[] current = currentAddressResult.getText().split(":", 2);
        String[] permanent = permanentAddressResult.getText().split(":", 2);
        Assertions.assertEquals(current[1], permanent[1]);
        return this;
    }
}