package com.demoqa.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * BasePage — общий родитель для ВСЕХ Page Object классов.
 *
 * Содержит переиспользуемые утилиты: клики, скролл, ожидания, алерты, переключение вкладок.
 * Каждый Page Object extends BasePage → наследует driver + все методы ниже.
 *
 * Архитектурный принцип: логика взаимодействия с браузером живёт ЗДЕСЬ,
 * а не дублируется в каждом Page Object.
 */
public class BasePage {

    protected WebDriver driver;
    protected JavascriptExecutor js;     // для выполнения JS прямо в браузере
    protected Actions actions;           // для сложных взаимодействий: hover, drag&drop, удержание клавиш

    /**
     * Конструктор вызывается каждый раз, когда создаётся любой Page Object (new LoginPage(driver) и т.д.).
     * PageFactory.initElements — сканирует поля класса с @FindBy и связывает их с реальными элементами страницы.
     * Без initElements все @FindBy поля будут null → NullPointerException при обращении.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);  // обязательно! инициализирует все @FindBy в текущем классе
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }

    // Скроллит страницу так, чтобы элемент оказался по центру экрана.
    // Используется перед кликом чтобы избежать ошибки "element not interactable" (элемент за пределами viewport).
    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    /**
     * JS-клик: сначала скроллит к элементу, потом кликает через JavaScript.
     * Используй вместо обычного click() когда:
     *   - элемент перекрыт другим (overlay, sticky header)
     *   - Selenium бросает ElementClickInterceptedException
     * Минус: обходит нативную логику браузера → не всегда триггерит JS-события элемента.
     */
    public void clickWithJS(WebElement element) {
        scrollToElement(element);
        js.executeScript("arguments[0].click();", element);
    }

    // Скроллит к элементу, затем вводит текст стандартным методом type().
    public void typeWithJS(WebElement element, String text) {
        scrollToElement(element);
        type(element, text);
    }

    public void click(WebElement element) {
        element.click();
    }

    // Вводит текст: сначала click() для фокуса, clear() для очистки, потом sendKeys().
    // Проверка text != null защищает от случайного вызова с null → не очищает поле впустую.
    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    // Фабричный метод. Создаёт WebDriverWait с заданным таймаутом.
    // Используется внутри других методов (getAlert, shouldHaveText и т.д.).
    public WebDriverWait getWait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    // Ждёт появления alert максимум seconds секунд. Бросает TimeoutException если алерт не появился.
    public Alert getAlert(int seconds) {
        return getWait(seconds).until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Проверяет наличие алерта: если появился — принимает (accept) и возвращает true.
     * try/catch перехватывает TimeoutException если алерта нет → возвращает false.
     * Внимание: метод ПРИНИМАЕТ алерт как побочный эффект — не используй если нужно только проверить наличие.
     */
    public boolean isAlertPresent(int seconds) {
        try {
            getAlert(seconds).accept();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isContainsText(String text, WebElement element) {
        return element.getText().contains(text);
    }

    // Явное ожидание: ждёт пока текст text появится внутри element. Возвращает true/false.
    // Безопаснее чем getText().contains() без ожидания — элемент может рендериться с задержкой.
    public boolean shouldHaveText(WebElement element, String text, int time) {
        return getWait(time).until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    /**
     * Переключается на вкладку/окно по индексу.
     * getWindowHandles() возвращает Set (неупорядоченный!) → конвертируем в List чтобы получить по индексу.
     * index=0 — исходная вкладка, index=1 — первая открытая новая вкладка.
     * Внимание: порядок вкладок в Set не гарантирован в некоторых браузерах → тест может быть нестабильным.
     */
    public void switchToNewTabWindow(int index) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(index));
    }

    // Безопасная проверка видимости: перехватывает NoSuchElementException если элемент вообще не найден в DOM.
    // isDisplayed() сам по себе бросает исключение если элемента нет → этот метод делает проверку безопасной.
    public boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Явное ожидание видимости элемента. Блокирует выполнение до появления или до истечения time секунд.
    public void waitOfElementVisibility(WebElement element, int time) {
        getWait(time).until(ExpectedConditions.visibilityOf(element));
    }
}