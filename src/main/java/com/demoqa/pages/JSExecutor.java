package com.demoqa.pages;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;

/**
 * JSExecutor — демонстрационный класс возможностей JavascriptExecutor.
 *
 * В отличие от других Page Objects, здесь нет @FindBy элементов —
 * всё взаимодействие происходит НАПРЯМУЮ через JS, минуя Selenium WebDriver API.
 *
 * Когда это нужно:
 *   - элемент недоступен через обычный Selenium (скрыт, заблокирован, вне viewport)
 *   - нужно изменить стиль/атрибут элемента (для визуальной отладки)
 *   - нужно получить данные из браузерного контекста (URL, title, innerText)
 *
 * Минус прямого JS: обходит нативные события браузера → может не триггерить
 * валидацию форм и React/Angular обработчики событий.
 */
public class JSExecutor extends BasePage {

    public JSExecutor(WebDriver driver) {
        super(driver);
    }

    // Заполняет поля напрямую через DOM (value=), не через sendKeys.
    // Изменение border — визуальный маркер для отладки (в продакшн-тестах убрать).
    public JSExecutor enterPersonalData(String name, String email) {
        js.executeScript("document.getElementById('userName').value='" + name + "';");
        js.executeScript("document.getElementById('userEmail').value='" + email + "';");
        js.executeScript("document.getElementById('userEmail').style.border='2px solid red';");
        return this;
    }

    // querySelector('#submit') — CSS-селектор. Эквивалент driver.findElement(By.id("submit")).
    // Изменение backgroundColor — визуальный маркер (убрать в реальных тестах).
    public JSExecutor clickOnSubmitButton() {
        js.executeScript("document.querySelector('#submit').click();");
        js.executeScript("document.querySelector('#submit').style.backgroundColor='red';");
        return this;
    }

    // Возвращает весь видимый текст страницы. Полезно для быстрой отладки — что вообще на странице.
    public JSExecutor getInnerText() {
        String inner = js.executeScript("return document.documentElement.innerText;").toString();
        System.out.println(inner);
        return this;
    }

    public JSExecutor verifyURL() {
        String url = js.executeScript("return document.URL").toString();
        System.out.println("URL: " + url);
        return this;
    }

    // history.go(0) — JS-аналог F5. Перезагружает текущую страницу.
    public JSExecutor refreshPage() {
        js.executeScript("history.go(0);");
        return this;
    }

    // window.location = url — навигация в ТЕКУЩЕЙ вкладке (не открывает новую).
    // Несмотря на название "navigateToNewTab" — это навигация в той же вкладке.
    public JSExecutor navigateToNewTab(String url) {
        js.executeScript("window.location ='" + url + "';");
        return this;
    }

    // document.title — заголовок вкладки (тег <title>), он же favicon title в браузере.
    public JSExecutor verifyNewPageFaveIconTitle() {
        String faveIconTitle = js.executeScript("return document.title;").toString();
        System.out.println("FaveIconTitle: " + faveIconTitle);
        return this;
    }
}