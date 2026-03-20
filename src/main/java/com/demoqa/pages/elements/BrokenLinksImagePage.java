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

    // Находит все ссылки на странице через тег <a>
    // Используется для проверки валидных и сломанных ссылок
    @FindBy(css = "a")
    List<WebElement> allLinks;

    // Находит все изображения на странице через тег <img>
    // Используется для проверки загруженных и сломанных картинок
    @FindBy(css = "img")
    List<WebElement> images;

    // ============================================================
    // ACTIONS
    // ============================================================

    // Выводит в консоль все ссылки на странице
    // Вариант 1 — getAttribute("href") — возвращает URL ссылки
    // Вариант 2 — getText()            — возвращает видимый текст ссылки
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

    // Проходит по всем ссылкам на странице и проверяет их HTTP статус
    // getDomAttribute("href") — берёт точное значение атрибута href из DOM
    // verifyLinks() из BasePage выводит в консоль:
    //   код < 300   → рабочая ссылка (OK)
    //   код 300-399 → редирект
    //   код >= 400  → сломанная ссылка
    public BrokenLinksImagePage checkBrokenLinks() {
        for (WebElement element : allLinks) {
            String url = element.getDomAttribute("href");
            try {
                verifyLinks(url);
            } catch (MalformedURLException e) {
                System.out.println(url + " --> MalformedURLException");
            }
        }
        return this;
    }

    // Проверяет все изображения на странице:
    // 1. Выводит общее количество изображений
    // 2. Для каждого изображения берёт атрибут src (URL картинки)
    // 3. Отправляет HTTP запрос через verifyLinks() → проверяет статус (200 OK / 404 Broken)
    // 4. Через JS проверяет что картинка реально загрузилась в браузере
    public BrokenLinksImagePage checkBrokenImages() {

        // Выводим общее количество найденных изображений на странице
        System.out.println("Total images on the page = " + images.size());

        // Проходим по каждому изображению на странице
        for (int i = 0; i < images.size(); i++) {

            // Получаем текущий элемент <img> из списка
            WebElement image = images.get(i);

            // Получаем URL изображения из атрибута src
            String imageUrl = image.getAttribute("src");

            // Отправляем HTTP запрос и выводим статус в консоль (200 OK / 404 Broken)
            try {
                verifyLinks(imageUrl);
            } catch (MalformedURLException e) {
                System.out.println(imageUrl + " --> MalformedURLException");
            }

            // Проверяем через JS что изображение реально загрузилось в браузере
            // arguments[0]            — WebElement image, переданный в JS
            // .naturalWidth           — встроенное свойство тега <img>:
            //                           картинка загрузилась → ширина в пикселях (> 0)
            //                           картинка сломана    → возвращает 0
            // typeof ... != undefined — проверяем что свойство naturalWidth существует
            //                           (защита если элемент вдруг не <img>)
            // && naturalWidth > 0     — оба условия true → картинка загружена
            boolean imageDisplayed = (Boolean) js.executeScript(
                    "return (typeof arguments[0].naturalWidth!=undefined && arguments[0].naturalWidth>0)", image);

            // Выводим результат проверки для каждого изображения
            if (imageDisplayed) {
                System.out.println("Image " + (i + 1) + " [OK]     --> " + imageUrl);
            } else {
                System.out.println("Image " + (i + 1) + " [BROKEN] --> " + imageUrl);
            }
        }
        softAssert.assertAll(); // Собираем все soft assertions и выводим результат в конце
        return this;
    }
}