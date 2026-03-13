package com.demoqa.pages;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h5[text()='Book Store Application']/..")
    WebElement bookStore;

    public SidePanel selectBookStore() {
        clickWithJS(bookStore);
        return new SidePanel(driver);
    }

    @FindBy(xpath = "//h5[text()='Elements']/..")
    WebElement elements;

    public SidePanel selectElements() {
        clickWithJS(elements);
        return new SidePanel(driver);
    }

    @FindBy(xpath = "//h5[text()='Alerts, Frame & Windows']/..")
    WebElement alertsFrameWindows;

    public SidePanel selectAlertsFrameWindows() {
        clickWithJS(alertsFrameWindows);
        return new SidePanel(driver);
    }

    @FindBy(xpath = "//h5[normalize-space()='Widgets']/..")
    WebElement widgets;

    public SidePanel selectWidgets() {
        clickWithJS(widgets);
        return new SidePanel(driver);
    }
}