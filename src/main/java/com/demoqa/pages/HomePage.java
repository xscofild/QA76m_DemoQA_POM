package com.demoqa.pages;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Главная страница demoqa.com
// Содержит карточки-категории: Elements, Alerts, Widgets, Book Store
// Каждый метод кликает по карточке и возвращает SidePanel — боковое меню раздела
public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Карточка "Book Store Application" на главной странице
    @FindBy(css = "a[href='/books']")
    WebElement bookStore;

    // Карточка "Elements" на главной странице
    @FindBy(css = "a[href='/elements']")
    WebElement elements;

    // Карточка "Alerts, Frame & Windows" на главной странице
    @FindBy(css = "a[href='/alertsWindows']")
    WebElement alertsFrameWindows;

    // Карточка "Widgets" на главной странице
    // normalize-space() убирает лишние пробелы при поиске по тексту
    @FindBy(xpath = "//h5[normalize-space()='Widgets']")
    WebElement widgets;

    // Клик по карточке Book Store → возвращает SidePanel этого раздела
    public SidePanel selectBookStore() {
        clickWithJS(bookStore);
        return new SidePanel(driver);
    }

    // Клик по карточке Elements → возвращает SidePanel этого раздела
    public SidePanel selectElements() {
        clickWithJS(elements);
        return new SidePanel(driver);
    }

    // Клик по карточке Alerts, Frame & Windows → возвращает SidePanel
    public SidePanel selectAlertsFrameWindows() {
        clickWithJS(alertsFrameWindows);
        return new SidePanel(driver);
    }

    // Клик по карточке Widgets → возвращает SidePanel этого раздела
    public SidePanel selectWidgets() {
        clickWithJS(widgets);
        return new SidePanel(driver);
    }
}
