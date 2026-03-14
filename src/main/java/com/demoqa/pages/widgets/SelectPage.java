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

/**
 * SelectPage — страница Select Menu, демонстрирует три вида select:
 *   1. Нативный HTML <select> (oldStyleSelect)
 *   2. React-based multi-select (multiSelect)
 *   3. Стандартный <select> с проверкой цвета (verifySelectCarBy...)
 *
 * Нативный <select> → работаем через класс Select из Selenium.
 * React-select → НЕТ стандартного <select> в DOM → работаем через sendKeys в input.
 */
public class SelectPage extends BasePage {

    public SelectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "oldSelectMenu")
    WebElement oldSelectMenu;  // нативный HTML <select>

    @FindBy(id = "react-select-4-input")
    WebElement selectInput;    // скрытый input внутри React-select компонента

    @FindBy(css = "html")
    WebElement space;          // клик по <html> закрывает выпадающий список React-select (имитация "клик в сторону")

    // Select — Selenium-класс для нативного <select>. Не работает с React/Vue/Angular дропдаунами!
    public SelectPage oldStyleSelect(String color) {
        new Select(oldSelectMenu).selectByVisibleText(color);
        return this;
    }

    public SelectPage verifyColor(String expectedColor) {
        String actualColor = new Select(oldSelectMenu)
                .getFirstSelectedOption()
                .getText();
        Assertions.assertEquals(expectedColor, actualColor);
        return this;
    }

    /**
     * Мультивыбор в React-select: вводим текст → Enter выбирает подсвеченный вариант.
     * Каждая итерация: вводим название → Enter → React добавляет тег.
     * click(space) после цикла — закрывает выпадающий список.
     */
    public SelectPage multiSelect(String[] colors) {
        for (String text : colors) {
            selectInput.sendKeys(text);
            selectInput.sendKeys(Keys.ENTER);
        }
        click(space);
        return this;
    }

    /**
     * SoftAssertions — "мягкие" проверки: собирают ВСЕ ошибки и показывают их вместе в конце.
     * В отличие от Assertions.assertTrue() который останавливается на первом фейле.
     * assertAll() в конце — обязательно, иначе ошибки не будут выброшены.
     */
    public SelectPage verifyMultiSelect(String[] colors) {
        SoftAssertions softly = new SoftAssertions();

        for (String text : colors) {
            WebElement element = driver.findElement(By.xpath("//*[.='" + text + "']"));
            softly.assertThat(isContainsText(text, element))
                    .as("Не найден выбранный цвет: " + text)
                    .isTrue();
        }

        softly.assertAll();
        return this;
    }

    /**
     * Проверяет цвет фона нативного <option> через getCssValue().
     * getCssValue("background-color") возвращает цвет в формате "rgba(r, g, b, a)".
     * Сравниваем contains(color) — значит color должен быть частью rgba строки.
     * Хрупко: если формат браузера изменится → тест сломается. Лучше использовать verifySelectedCarByFormat.
     */
    public SelectPage verifySelectCarByValue(String car, String color) {
        WebElement selectedCar = driver.findElement(By.cssSelector("[value='" + car + "']"));
        click(selectedCar);
        Assertions.assertTrue(selectedCar.getCssValue("background-color").contains(color));
        return this;
    }

    /**
     * Надёжная версия проверки цвета: конвертирует rgba → HEX через Color.fromString().asHex().
     * Позволяет сравнивать цвет в HEX формате (#1967d2) вместо "rgba(25, 103, 210, 1)".
     * Color — Selenium-утилита (org.openqa.selenium.support.Color).
     * Это предпочтительный способ сравнения цветов в тестах.
     */
    public SelectPage verifySelectedCarByFormat(String car, String color) {
        WebElement selectedCar = driver.findElement(By.cssSelector("[value='" + car + "']"));
        click(selectedCar);
        String format = Color.fromString(selectedCar.getCssValue("background-color")).asHex();
        Assertions.assertTrue(format.contains(color));
        return this;
    }
}