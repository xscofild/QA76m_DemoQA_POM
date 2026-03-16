package com.demoqa.pages.widgets;

import com.demoqa.core.BasePage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

// Страница "Select Menu" в разделе Widgets
// Демонстрирует три типа select-элементов:
//
//  1. Old Style Select — стандартный HTML <select>
//     Работаем через класс Select из Selenium
//     Select.selectByVisibleText() — выбор по тексту опции
//     Select.getFirstSelectedOption() — получить выбранную опцию
//
//  2. Multi Select (react-select) — кастомный React компонент
//     НЕ является стандартным <select> — класс Select не подходит
//     Работаем через sendKeys() + Enter для каждого значения
//
//  3. Standard Multi Select — стандартный HTML <select multiple>
//     Проверяем цвет выбранной опции через getCssValue()
//     Color.fromString().asHex() — конвертирует RGB → HEX формат
public class SelectPage extends BasePage {

    public SelectPage(WebDriver driver) {
        super(driver);
    }

    // Стандартный HTML <select> (старый стиль)
    @FindBy(id = "oldSelectMenu")
    WebElement oldSelectMenu;

    // Поле ввода react-select для множественного выбора
    @FindBy(id = "react-select-4-input")
    WebElement selectInput;

    // Тег html — клик по пустому месту закрывает открытый dropdown
    @FindBy(css = "html")
    WebElement space;

    // ─── Old Style Select ──────────────────────────────────

    // Выбирает значение в стандартном HTML select по видимому тексту
    // new Select(element) — обёртка Selenium для работы с тегом <select>
    // selectByVisibleText() — выбор по тексту как он отображается в списке
    public SelectPage oldStyleSelect(String color) {
        Select select = new Select(oldSelectMenu);
        select.selectByVisibleText(color);
        return this;
    }

    // Проверяет что в select выбрано правильное значение
    // getFirstSelectedOption() — возвращает первый выбранный <option>
    // shouldHaveText() — ждёт появления текста с explicit wait
    public SelectPage verifyColor() {
        String firstSelectedOption = new Select(oldSelectMenu).getFirstSelectedOption().getText();
        Assertions.assertTrue(shouldHaveText(oldSelectMenu, firstSelectedOption, 5));
        return this;
    }

    // ─── React Multi Select ────────────────────────────────

    // Выбирает несколько значений в react-select
    // Для каждого значения: вводим текст → нажимаем Enter для подтверждения
    // click(space) — кликаем по пустому месту чтобы закрыть dropdown
    public SelectPage multiSelect(String[] colors) {
        for (String text : colors) {
            selectInput.sendKeys(text);
            selectInput.sendKeys(Keys.ENTER); // ENTER — подтвердить выбор текущего значения
        }
        click(space);
        return this;
    }

    // Проверяет все выбранные значения через SoftAssertions
    // SoftAssertions — не останавливается на первой ошибке,
    //   собирает все ошибки и выводит их вместе через assertAll()
    // driver.findElement(By.xpath(...)) — ищем каждое значение на странице
    public SelectPage verifyMultiSelect(String[] colors) {
        SoftAssertions softly = new SoftAssertions();
        for (String text : colors) {
            WebElement element = driver.findElement(By.xpath("//*[.='" + text + "']"));
            softly.assertThat(isContainsText(text, element)).isTrue();
        }
        softly.assertAll();
        return this;
    }

    // ─── Standard Multi Select (проверка цвета) ────────────

    // Кликает по option по значению атрибута value и проверяет цвет через rgba
    // getCssValue("background-color") — возвращает цвет в формате rgba(r, g, b, a)
    public SelectPage verifySelectCarByValue(String car, String color) {
        WebElement selectedCar = driver.findElement(By.cssSelector("[value='" + car + "']"));
        click(selectedCar);
        Assertions.assertTrue(selectedCar.getCssValue("background-color").contains(color));
        return this;
    }

    // Кликает по option и проверяет цвет в HEX формате
    // Color.fromString() — парсит rgba строку в объект Color
    // .asHex() — конвертирует в #rrggbb формат для удобного сравнения
    public SelectPage verifySelectCarByFormat(String car, String color) {
        WebElement selectedCar = driver.findElement(By.cssSelector("[value='" + car + "']"));
        click(selectedCar);
        String format = Color.fromString(selectedCar.getCssValue("background-color")).asHex();
        Assertions.assertTrue(format.contains(color));
        return this;
    }
}
