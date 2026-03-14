package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * FramesPage — работа с iframe на странице.
 *
 * КРИТИЧЕСКИ ВАЖНО про iframe:
 * По умолчанию Selenium видит только элементы основного документа (main content).
 * Чтобы взаимодействовать с элементами внутри iframe — нужно переключиться в него (switchTo().frame()).
 * Чтобы вернуться к основному документу — switchTo().defaultContent().
 * Если забыть вернуться → все последующие findElement() будут искать внутри iframe → NoSuchElementException.
 *
 * Используется для ДВУХ страниц: Frames и Nested Frames (оба через SidePanel возвращают FramesPage).
 */
public class FramesPage extends BasePage {

    public FramesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "iframe")
    List<WebElement> iframes;  // находит все iframe на странице

    // Диагностический метод: выводит количество iframe двумя способами для сравнения.
    // WebElements считает через DOM, JS — через window.frames (может отличаться для динамических iframe).
    public FramesPage returnListOfIframes() {
        System.out.println("Iframes by WebElements: " + iframes.size());

        Object jsResult = js.executeScript("return window.frames.length");
        int count = jsResult != null ? Integer.parseInt(jsResult.toString()) : 0;
        System.out.println("Iframes by JS: " + count);

        return this;
    }

    // Переключается в iframe по порядковому номеру (0-based индекс на странице).
    // Хрупкий способ: если порядок iframe изменится на странице — тест сломается.
    public FramesPage switchToIframeByIndex(int index) {
        driver.switchTo().frame(index);
        return this;
    }

    @FindBy(id = "frame1")
    WebElement frame1;

    // Переключается в конкретный iframe по его WebElement — надёжнее чем по индексу.
    public FramesPage switchToIframeById() {
        driver.switchTo().frame(frame1);
        return this;
    }

    // Возврат из iframe в основной документ. ОБЯЗАТЕЛЬНО вызывать после работы с iframe.
    public FramesPage switchToMainPage() {
        driver.switchTo().defaultContent();
        return this;
    }

    // Элемент ВНУТРИ iframe. Доступен только после switchToIframe*, иначе — NoSuchElementException.
    @FindBy(id = "sampleHeading")
    WebElement sampleHeading;

    public FramesPage verifyIframeByTitle(String title) {
        Assertions.assertTrue(isContainsText(title, sampleHeading));
        return this;
    }

    // Элемент основного документа. Доступен только после switchToMainPage(), иначе — NoSuchElementException.
    @FindBy(css = ".text-center")
    WebElement textCenter;

    public FramesPage verifyMainPageTitle(String text) {
        Assertions.assertTrue(isContainsText(text, textCenter));
        return this;
    }

    // Заглушка для вложенных фреймов. Тест с этим методом отключён через @Disabled.
    public void handleNestedFrames() {
    }
}