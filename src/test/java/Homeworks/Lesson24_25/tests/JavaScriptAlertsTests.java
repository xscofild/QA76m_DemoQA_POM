package Homeworks.Lesson24_25.tests;

import Homeworks.Lesson24_25.core.TestBase;
import Homeworks.Lesson24_25.pages.HomePage;
import org.junit.jupiter.api.Test;

public class JavaScriptAlertsTests extends TestBase {

    // Тест сценарий:
    //  1. Переходим на страницу JavaScript Alerts
    //  2. Нажимаем кнопку "Click for JS Alert"
    //  3. Принимаем alert (нажимаем OK)
    //  4. Проверяем что результат содержит "You successfully clicked an alert"
    @Test
    public void jsAlertTest() {
        new HomePage(driver)
                .goToJavaScriptAlertsPage()
                .clickJsAlertButton()
                .acceptAlert()
                .verifyResultText("You successfully clicked an alert");
    }

    // Тест сценарий:
    //  1. Переходим на страницу JavaScript Alerts
    //  2. Нажимаем кнопку "Click for JS Confirm"
    //  3. Принимаем confirm alert (нажимаем OK)
    //  4. Проверяем что результат содержит "You clicked: Ok"
    @Test
    public void jsConfirmOkTest() {
        new HomePage(driver)
                .goToJavaScriptAlertsPage()
                .clickJsConfirmButton()
                .acceptAlert()
                .verifyResultText("You clicked: Ok");
    }

    // Тест сценарий:
    //  1. Переходим на страницу JavaScript Alerts
    //  2. Нажимаем кнопку "Click for JS Confirm"
    //  3. Отклоняем confirm alert (нажимаем Cancel)
    //  4. Проверяем что результат содержит "You clicked: Cancel"
    @Test
    public void jsConfirmCancelTest() {
        new HomePage(driver)
                .goToJavaScriptAlertsPage()
                .clickJsConfirmButton()
                .dismissAlert()
                .verifyResultText("You clicked: Cancel");
    }

    // Тест сценарий:
    //  1. Переходим на страницу JavaScript Alerts
    //  2. Нажимаем кнопку "Click for JS Prompt"
    //  3. Вводим текст "hello1" в поле prompt и нажимаем OK
    //  4. Проверяем что результат содержит "You entered: hello1"
    @Test
    public void jsPromptTest() {
        new HomePage(driver)
                .goToJavaScriptAlertsPage()
                .clickJsPromptButton()
                .sendTextToPromptAndAccept("hello1")
                .verifyResultText("You entered: hello1");
    }
}
