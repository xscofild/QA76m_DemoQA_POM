package com.demoqa.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

// Базовый класс для всех Page Object классов
// Содержит общие методы: клики, ввод текста, скролл, ожидания, alerts, вкладки
// Все Page классы наследуют BasePage и получают доступ к этим методам
public class BasePage {

    protected WebDriver driver;
    public static JavascriptExecutor js; // static — доступен из любого места без объекта
    protected Actions actions;           // для сложных взаимодействий: hover, drag, двойной клик

    // Конструктор — вызывается при создании любой страницы
    // PageFactory.initElements() — связывает @FindBy поля с реальными элементами страницы
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }

    // ─── СКРОЛЛ ───────────────────────────────────────────

    // Прокручивает страницу к элементу через JavaScript
    // arguments[0] — ссылка на WebElement передаваемый в JS
    // scrollIntoView({block:'center'}) — помещает элемент в центр экрана
    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    // ─── КЛИКИ ────────────────────────────────────────────

    // Обычный клик по элементу
    public void click(WebElement element) {
        element.click();
    }

    // Скролл к элементу через JS + клик через JS
    // arguments[0].scrollIntoView — прокручиваем к элементу
    // arguments[0].click()        — кликаем через JS
    // Используется когда обычный click() не срабатывает
    // (элемент перекрыт другим или вне зоны видимости)
    public void clickWithJS(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block:'center'}); arguments[0].click();", element);
    }

    // ─── ВВОД ТЕКСТА ──────────────────────────────────────

    // Клик по полю → очистка → ввод текста
    // Пропускает если text == null
    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);         // кликаем чтобы поставить фокус
            element.clear();        // очищаем поле
            element.sendKeys(text); // вводим текст
        }
    }

    // Скролл к элементу через JS + ввод текста
    // Удобно для полей которые находятся вне зоны видимости
    public void typeWithJS(WebElement element, String text) {
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        type(element, text);
    }

    // ─── ОЖИДАНИЯ ─────────────────────────────────────────

    // Создаёт явное ожидание на указанное количество секунд
    // Explicit wait — ждёт конкретного условия, лучше чем Thread.sleep()
    public WebDriverWait getWait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    // Ждёт появления текста в элементе, возвращает true когда текст появился
    public boolean shouldHaveText(WebElement element, String text, int time) {
        return getWait(time)
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    // ─── ALERTS ───────────────────────────────────────────

    // Ждёт появления Alert и принимает его (нажимает OK)
    // Возвращает true если alert появился, false если не появился за указанное время
    public boolean isAlertPresent(int seconds) {
        Alert alert = getWait(seconds)
                .until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        } else {
            driver.switchTo().alert().accept(); // switchTo() — переключаемся на alert и нажимаем OK
            return true;
        }
    }

    // ─── ВКЛАДКИ БРАУЗЕРА ─────────────────────────────────

    // Переключается на вкладку браузера по индексу
    // index=0 — первая вкладка, index=1 — вторая вкладка и т.д.
    // Нужен когда тест открывает новые вкладки/окна
    public void switchToNewTabWindow(int index) {
        // getWindowHandles() — возвращает все открытые вкладки/окна
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(index)); // переключаем фокус на нужную вкладку
    }

    // ─── ПРОВЕРКИ ЭЛЕМЕНТОВ ───────────────────────────────

    // Проверяет что текст элемента содержит заданную строку
    public boolean isContainsText(String text, WebElement element) {
        return element.getText().contains(text);
    }

    // Проверяет что элемент отображается на странице
    // Возвращает false если элемент не найден (NoSuchElementException)
    public boolean isElementVisible(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            e.getMessage();
            return false;
        }
    }

    // Ждёт пока элемент станет видимым на странице
    public void waitOfElementVisibility(WebElement element, int time) {
        getWait(time).until(ExpectedConditions.visibilityOf(element));
    }
}
