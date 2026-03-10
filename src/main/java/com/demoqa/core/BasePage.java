package com.demoqa.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage — родительский класс для всех Page Object классов.
 * Содержит общие методы: клик, ввод текста, скролл через JavaScript.
 * Каждая страница наследует этот класс через extends BasePage.
 */
public class BasePage {

    protected WebDriver driver;          // WebDriver — управляет браузером
    public static JavascriptExecutor js; // JS-исполнитель — нужен для скролла и кликов

    // Конструктор: принимает driver, инициализирует @FindBy-поля через PageFactory
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Связывает @FindBy-поля с реальными элементами
        js = (JavascriptExecutor) driver;
    }

    // Скроллит страницу на x пикселей по горизонтали и y по вертикали
    public void scrollWithJS(int x, int y) {
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
    }

    // Скроллит до нужной позиции, затем кликает по элементу
    public void clickWithJS(WebElement element, int x, int y) {
        scrollWithJS(x, y);
        click(element);
    }

    // Скроллит до нужной позиции, затем вводит текст в элемент
    public void typeWithJS(WebElement element, String text, int x, int y) {
        scrollWithJS(x, y);
        type(element, text);
    }

    // Простой клик по элементу
    public void click(WebElement element) {
        element.click();
    }

    // Кликает на поле, очищает его и вводит текст. Если text == null — ничего не делает
    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);         // Фокусируемся на поле
            element.clear();        // Очищаем старое значение
            element.sendKeys(text); // Вводим новый текст
        }
    }
    public WebDriverWait getWait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public boolean isAlertPresent(int seconds) {
        try {
            Alert alert = getWait(seconds)
                    .until(ExpectedConditions.alertIsPresent());

            if (alert != null) {
                alert.accept(); // нажимаем OK на алерте
                return true;
            }
            return false;

        } catch (TimeoutException e) {
            return false; // алерт не появился за 20 секунд
        }
    }
}