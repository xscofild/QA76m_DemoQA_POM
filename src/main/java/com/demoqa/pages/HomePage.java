package com.demoqa.pages;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * HomePage — главная страница сайта demoqa.com.
 * Отсюда можно перейти в раздел Book Store.
 */
public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // CSS-селектор: находит тег <a> с атрибутом href='/books'
    @FindBy(css = ".card:nth-child(6)")
    WebElement bookStore;

    // scrollIntoView скроллит прямо к элементу, JS-клик обходит перекрытие футером
    public SidePanel selectBookStore() {
        js.executeScript("arguments[0].scrollIntoView(true);", bookStore);
        js.executeScript("arguments[0].click();", bookStore);
        return new SidePanel(driver);
    }

    @FindBy(css = ".card:nth-child(1)")
    WebElement elements;

    public SidePanel selectElements() {
        clickWithJS(elements, 0, 300);
        return new SidePanel(driver);
    }

    @FindBy(xpath = "//h5[text()='Alerts, Frame & Windows']/..")
    WebElement alertsFrameWindows;

    public SidePanel selectAlertsFrameWindows() {
        js.executeScript("arguments[0].scrollIntoView(true);", alertsFrameWindows);
        js.executeScript("arguments[0].click();", alertsFrameWindows);
        return new SidePanel(driver);
    }
}