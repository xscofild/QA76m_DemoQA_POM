package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Страница "Text Box" в разделе Elements
// Демонстрирует работу с Actions для симуляции клавиатурных сочетаний:
//  Ctrl+A — выделить всё
//  Ctrl+C — копировать
//  Tab    — переключить фокус на следующее поле
//  Ctrl+V — вставить
//
// Сценарий: вводим адрес в Current Address → копируем → вставляем в Permanent Address
// После Submit оба поля должны содержать одинаковый адрес
public class TextBoxPage extends BasePage {

    public TextBoxPage(WebDriver driver) {
        super(driver);
    }

    // Поле "Current Address"
    @FindBy(id = "currentAddress")
    WebElement currentAddress;

    // Кнопка Submit
    @FindBy(id = "submit")
    WebElement submit;

    // Результат Current Address после нажатия Submit (внутри блока .border)
    @FindBy(css = ".border #currentAddress")
    WebElement currentAddressResult;

    // Результат Permanent Address после нажатия Submit (внутри блока .border)
    @FindBy(css = ".border #permanentAddress")
    WebElement permanentAddressResult;

    // Вводит адрес в Current Address, копирует и вставляет в Permanent Address
    // Цепочка Actions:
    //   keyDown(CTRL) + "a" + keyUp(CTRL) — выделить всё (Ctrl+A)
    //   keyDown(CTRL) + "c" + keyUp(CTRL) — скопировать (Ctrl+C)
    //   sendKeys(TAB)                      — переключить фокус на Permanent Address
    //   keyDown(CTRL) + "v" + keyUp(CTRL) — вставить (Ctrl+V)
    public TextBoxPage copyPast(String address) {
        type(currentAddress, address);
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
        actions.sendKeys(Keys.TAB).perform();
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
        return this;
    }

    // Нажимает кнопку Submit
    // После клика страница показывает введённые данные в блоке .border
    public TextBoxPage clickOnSubmitButton() {
        click(submit);
        return this;
    }

    // Проверяет что оба адреса одинаковые
    // split(":") — разбиваем текст "Current Address: Friedrichstr..." по двоеточию
    // current[1] и permanent[1] — берём часть после двоеточия
    public TextBoxPage verifyAddress() {
        String[] current = currentAddressResult.getText().split(":");
        String[] permanent = permanentAddressResult.getText().split(":");
        Assertions.assertEquals(current[1], permanent[1]);
        return this;
    }
}
