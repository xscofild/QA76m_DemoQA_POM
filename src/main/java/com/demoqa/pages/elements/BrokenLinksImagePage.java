package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

public class BrokenLinksImagePage extends BasePage {

    public BrokenLinksImagePage(WebDriver driver) {
        super(driver);
    }

    // ============================================================
    // LOCATORS
    // ============================================================

    /**
     * Находит все ссылки на странице через тег <a>.
     * Используется для проверки валидных и сломанных ссылок.
     */
    @FindBy(css = "a")
    List<WebElement> allLinks;

    // ============================================================
    // ACTIONS
    // ============================================================

    /**
     * Выводит в консоль все ссылки на странице.
     * <p>
     * Вариант 1 — getAttribute("href") — возвращает URL ссылки:
     *   url = iterator.next().getAttribute("href");
     *   например: "https://demoqa.com/broken"
     * <p>
     * Вариант 2 — getText() — возвращает видимый текст ссылки:
     *   url = iterator.next().getText();
     *   например: "Click Here for Valid Link"
     */
    public BrokenLinksImagePage getAllLinks() {
        System.out.println("All links on the page: " + allLinks.size());

        Iterator<WebElement> iterator = allLinks.iterator();
        while (iterator.hasNext()) {
            // Вариант 1: URL ссылки
            // String url = iterator.next().getAttribute("href");

            // Вариант 2: видимый текст ссылки
            String url = iterator.next().getText();

            System.out.println(url);
        }
        return this;
    }

    /**
     * Проходит по всем ссылкам на странице и проверяет их HTTP статус.
     * Результат выводится в консоль через verifyLinks() из BasePage:
     * - код < 300 → рабочая ссылка
     * - код 300-399 → редирект
     * - код >= 400 → сломанная ссылка
     */
    public BrokenLinksImagePage checkBrokenLinks() throws MalformedURLException {
        for (WebElement element : allLinks) {
            String url = element.getDomAttribute("href");
            verifyLinks(url);
        }
        return this;
    }
}