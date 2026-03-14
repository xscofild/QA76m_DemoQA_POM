package com.demoqa.pages;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * HomePage — стартовая страница demoqa.com.
 *
 * Точка входа в тест: отсюда навигируем в нужный раздел.
 * Каждый select-метод кликает по карточке раздела и возвращает SidePanel —
 * потому что после клика по карточке всегда открывается боковое меню (SidePanel).
 *
 * Паттерн: метод возвращает следующий Page Object → тест читается как цепочка шагов.
 */
public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // XPath "//h5[text()='..']/.." — ищет h5 с текстом, потом берёт родителя (сама кликабельная карточка).
    // Без /.. клик попадает на текст внутри карточки, а не на весь элемент — поведение может отличаться.

    @FindBy(xpath = "//h5[text()='Book Store Application']/..")
    WebElement bookStore;

    @FindBy(xpath = "//h5[text()='Elements']/..")
    WebElement elements;

    @FindBy(xpath = "//h5[text()='Alerts, Frame & Windows']/..")
    WebElement alertsFrameWindows;

    @FindBy(xpath = "//h5[normalize-space()='Widgets']/..")  // normalize-space() — обрезает пробелы в тексте
    WebElement widgets;

    public SidePanel selectBookStore() {
        clickWithJS(bookStore);
        return new SidePanel(driver);  // SidePanel появляется после клика → сразу создаём и возвращаем
    }

    public SidePanel selectElements() {
        clickWithJS(elements);
        return new SidePanel(driver);
    }

    public SidePanel selectAlertsFrameWindows() {
        clickWithJS(alertsFrameWindows);
        return new SidePanel(driver);
    }

    public SidePanel selectWidgets() {
        clickWithJS(widgets);
        return new SidePanel(driver);
    }
}