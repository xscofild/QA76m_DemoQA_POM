package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

// Страница "Frames" в разделе Alerts, Frame & Windows
// Демонстрирует работу с HTML iframes в Selenium
//
// iframe — встроенный HTML-документ внутри основного документа
// По умолчанию Selenium работает с основным DOM
// Чтобы взаимодействовать с элементами ВНУТРИ iframe — нужно переключиться:
//   driver.switchTo().frame(...)        — переход внутрь iframe
//   driver.switchTo().defaultContent()  — возврат в основной DOM
//
// Способы переключения:
//  1. По индексу      — frame(0), frame(1) ...
//  2. По WebElement   — frame(webElement) используя id/name атрибут
public class FramesPage extends BasePage {

    public FramesPage(WebDriver driver) {
        super(driver);
    }

    // Список всех iframe на странице (css = "iframe" собирает все элементы с тегом iframe)
    @FindBy(css = "iframe")
    List<WebElement> iframes;

    // Конкретный iframe с id="frame1"
    @FindBy(id = "frame1")
    WebElement frame1;

    // Заголовок внутри iframe (искать только ПОСЛЕ переключения в iframe)
    @FindBy(id = "sampleHeading")
    WebElement sampleHeading;

    // Заголовок основной страницы (искать только ПОСЛЕ switchToMainPage)
    @FindBy(css = ".text-center")
    WebElement textCenter;

    // ─── Утилиты ───────────────────────────────────────────

    // Выводит количество найденных iframe двумя способами:
    // 1. Через Selenium List size()
    // 2. Через JavaScript window.length
    public FramesPage returnListOfIframes() {
        System.out.println("Количество iframe (Selenium): " + iframes.size());
        int numberOfIframes = Integer.parseInt(js.executeScript("return window.length").toString());
        System.out.println("Количество iframe (JS): " + numberOfIframes);
        return this;
    }

    // ─── Переключение в iframe ─────────────────────────────

    // Переключается в iframe по числовому индексу
    // Индексация с 0: первый iframe = 0, второй = 1
    public FramesPage switchToIframeByIndex(int index) {
        driver.switchTo().frame(index);
        return this;
    }

    // Переключается в iframe через WebElement (по id="frame1")
    // Более явный и стабильный способ чем по индексу
    public FramesPage switchToIframeById() {
        driver.switchTo().frame(frame1);
        return this;
    }

    // Возвращается в основной DOM страницы
    // defaultContent() — обязательно вызывать после работы с iframe
    // иначе Selenium продолжит искать элементы внутри iframe
    public FramesPage switchToMainPage() {
        driver.switchTo().defaultContent();
        return this;
    }

    // ─── Проверки ──────────────────────────────────────────

    // Проверяет заголовок внутри iframe
    // ВАЖНО: вызывать только после switchToIframe — иначе элемент не найден
    public FramesPage verifyIframeByTitle(String title) {
        Assertions.assertTrue(isContainsText(title, sampleHeading));
        return this;
    }

    // Проверяет заголовок основной страницы
    // ВАЖНО: вызывать только после switchToMainPage()
    public FramesPage verifyMainPageTitle(String text) {
        Assertions.assertTrue(isContainsText(text, textCenter));
        return this;
    }
}
