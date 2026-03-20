package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Страница "Browser Windows" в разделе Alerts, Frame & Windows
// Демонстрирует работу с новыми вкладками и окнами браузера
//
// Когда открывается новая вкладка — Selenium остаётся на старой
// Чтобы работать с новой вкладкой нужно переключить фокус:
//   driver.getWindowHandles()       — возвращает все открытые вкладки/окна
//   driver.switchTo().window(handle) — переключает фокус на нужную вкладку
//
// switchToNewTabWindow(index) унаследован из BasePage
public class WindowsPage extends BasePage {

    public WindowsPage(WebDriver driver) {
        super(driver);
    }

    // Кнопка "New Tab" — открывает страницу в новой вкладке браузера
    @FindBy(id = "tabButton")
    WebElement tabButton;

    // Заголовок на открывшейся новой вкладке
    @FindBy(id = "sampleHeading")
    WebElement sampleHeading;

    // Кликает по кнопке New Tab
    // После клика браузер открывает новую вкладку, Selenium остаётся на текущей
    public WindowsPage clickOnNewTabButton() {
        clickWithJS(tabButton);
        return this;
    }

    // Переключается на вкладку по индексу
    // index=1 — вторая вкладка (новая открытая)
    // Делегирует в switchToNewTabWindow(index) из BasePage
    public WindowsPage switchToNewTab(int index) {
        switchToNewTabWindow(index);
        return this;
    }

    // Проверяет заголовок на новой вкладке
    // shouldHaveText() ждёт появления текста до 5 секунд
    public WindowsPage verifyNewTabTitle(String title) {
        Assertions.assertTrue(shouldHaveText(sampleHeading, title, 5));
        return this;
    }
}