package com.demoqa.pages;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object для главной страницы demoqa.com.
 *
 * Содержит карточки-категории: Elements, Forms, Alerts, Widgets, Book Store.
 * Каждый метод кликает по карточке и возвращает SidePanel — боковое меню раздела.
 *
 * CSS-локаторы вида a[href='/forms'] надёжнее текстовых XPath на главной:
 * href не меняется при локализации или рефакторинге вёрстки.
 */
public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // ============================================================
    // LOCATORS
    // ============================================================

    @FindBy(css = "a[href='/elements']")
    private WebElement elements;

    @FindBy(css = "a[href='/forms']")
    private WebElement forms;

    @FindBy(css = "a[href='/alertsWindows']")
    private WebElement alertsFrameWindows;

    /**
     * normalize-space() в XPath убирает лишние пробелы при поиске по тексту.
     * Используется здесь, потому что у карточки "Widgets" нет уникального href.
     */
    @FindBy(xpath = "//h5[normalize-space()='Widgets']")
    private WebElement widgets;

    @FindBy(css = "a[href='/books']")
    private WebElement bookStore;

    // ============================================================
    // METHODS
    // ============================================================

    /** Переход в раздел Elements. */
    public SidePanel selectElements() {
        clickWithJS(elements);
        return new SidePanel(driver);
    }

    /** Переход в раздел Forms. */
    public SidePanel getForms() {
        click(forms);
        return new SidePanel(driver);
    }

    /** Переход в раздел Alerts, Frame & Windows. */
    public SidePanel selectAlertsFrameWindows() {
        clickWithJS(alertsFrameWindows);
        return new SidePanel(driver);
    }

    /** Переход в раздел Widgets. */
    public SidePanel selectWidgets() {
        clickWithJS(widgets);
        return new SidePanel(driver);
    }

    /** Переход в раздел Book Store. */
    public SidePanel selectBookStore() {
        clickWithJS(bookStore);
        return new SidePanel(driver);
    }
}