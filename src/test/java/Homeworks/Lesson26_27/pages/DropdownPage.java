package Homeworks.Lesson26_27.pages;

import Homeworks.Lesson26_27.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * DropdownPage — нативный HTML <select> на the-internet.herokuapp.com/dropdown.
 *
 * Select — Selenium-класс для работы с нативным <select>.
 * selectByValue("1") → выбирает <option value="1">Option 1</option>.
 * selectByValue vs selectByVisibleText:
 *   - value  — атрибут option в HTML (стабильнее, не зависит от перевода текста)
 *   - visibleText — текст который видит пользователь (хрупче при локализации)
 *
 * new Select(dropdown) создаётся каждый раз заново — это нормально,
 * Select — лёгкая обёртка над WebElement, не хранит состояние.
 */
public class DropdownPage extends BasePage {

    public DropdownPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "dropdown")
    private WebElement dropdown;

    public DropdownPage selectByValue(String value) {
        new Select(dropdown).selectByValue(value);
        return this;
    }

    // getFirstSelectedOption() — возвращает текущий выбранный <option>.
    // Используем после selectByValue чтобы убедиться что выбор применился.
    public DropdownPage verifySelectedOption(String expectedText) {
        String selected = new Select(dropdown).getFirstSelectedOption().getText();
        Assertions.assertEquals(expectedText, selected);
        return this;
    }
}