package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * WindowsPage — работа с новыми вкладками/окнами браузера.
 *
 * После открытия новой вкладки Selenium остаётся в старой — нужно явно переключиться.
 * switchToNewTabWindow(index) из BasePage переключает по индексу списка дескрипторов окон.
 * index=0 — оригинальная вкладка, index=1 — новая вкладка.
 */
public class WindowsPage extends BasePage {

    public WindowsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "tabButton")
    WebElement tabButton;

    public WindowsPage clickOnNewTabButton() {
        clickWithJS(tabButton);
        return this;
    }

    // Делегирует переключение вкладки в BasePage.switchToNewTabWindow(index).
    public WindowsPage switchToNewTab(int index) {
        switchToNewTabWindow(index);
        return this;
    }

    // Элемент на НОВОЙ вкладке. Доступен только после switchToNewTab(1), иначе ищет в старой вкладке.
    @FindBy(id = "sampleHeading")
    WebElement sampleHeading;

    public WindowsPage verifyNewTabTitle(String title) {
        Assertions.assertTrue(shouldHaveText(sampleHeading, title, 5));
        return this;
    }
}