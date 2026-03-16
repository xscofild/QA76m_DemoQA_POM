package com.demoqa.pages;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;

// Page Object для страницы Text Box
// Демонстрирует работу с JavascriptExecutor — альтернативный способ управления страницей
// Вместо Selenium-локаторов используем JS команды напрямую через document.*
public class JSExecutor extends BasePage {

    public JSExecutor(WebDriver driver) {
        super(driver);
    }

    // Заполняет поля userName и userEmail через JavaScript
    // document.getElementById('id').value = '...' — устанавливает значение поля напрямую в DOM
    public JSExecutor enterPersonalData(String name, String email) {
        js.executeScript("document.getElementById('userName').value='" + name + "';");
        js.executeScript("document.getElementById('userEmail').value='" + email + "';");
        js.executeScript("document.getElementById('userEmail').style.border='3px solid red';"); // меняем стиль через JS
        return this;
    }

    // Кликает по кнопке Submit через JavaScript
    // document.querySelector('#submit').click() — JS клик когда Selenium не может достучаться
    public JSExecutor clickOnSubmitButton() {
        js.executeScript("document.querySelector('#submit').click();");
        js.executeScript("document.querySelector('#submit').style.backgroundColor='red';"); // меняем фон кнопки
        return this;
    }

    // Получает и выводит весь текст страницы в консоль
    // document.documentElement.innerText — весь видимый текст на странице
    public JSExecutor getInnerText() {
        String inner = js.executeScript("return document.documentElement.innerText;").toString();
        System.out.println(inner);
        return this;
    }

    // Выводит текущий URL страницы в консоль
    // document.URL — аналог driver.getCurrentUrl() но через JavaScript
    public JSExecutor verifyURL() {
        String url = js.executeScript("return document.URL;").toString();
        System.out.println("URL: " + url);
        return this;
    }

    // Обновляет текущую страницу через JavaScript
    // history.go(0) — перезагрузка, history.go(1) — вперёд, history.go(-1) — назад
    public JSExecutor refreshPage() {
        js.executeScript("history.go(0);");
        return this;
    }

    // Переходит по заданному URL в том же окне браузера
    // window.location — JS свойство для управления навигацией браузера
    public JSExecutor navigateToNewPage(String url) {
        js.executeScript("window.location='" + url + "';");
        return this;
    }

    // Выводит title страницы (текст вкладки браузера) в консоль
    // document.title — текст который отображается в заголовке вкладки
    public JSExecutor verifyNewPageFaveIconTitle() {
        String faveIconTitle = js.executeScript("return document.title;").toString();
        System.out.println("Fave Icon Title: " + faveIconTitle);
        return this;
    }
}
