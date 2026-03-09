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
    @FindBy(css = "a[href='/books']")
    WebElement bookStore;

    // scrollIntoView скроллит прямо к элементу, JS-клик обходит перекрытие футером
    public SidePanel selectBookStore() {
        js.executeScript("arguments[0].scrollIntoView(true);", bookStore);
        js.executeScript("arguments[0].click();", bookStore);
        return new SidePanel(driver);
    }
}