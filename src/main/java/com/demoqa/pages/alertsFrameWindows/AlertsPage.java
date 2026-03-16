package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Страница "Alerts" в разделе Alerts, Frame & Windows
// Демонстрирует три типа browser alerts:
//  1. Timer Alert   — появляется через 5 секунд, только кнопка OK
//  2. Confirm Alert — кнопки OK и Cancel
//  3. Prompt Alert  — поле ввода текста + кнопки OK/Cancel
// Для работы с alert используется driver.switchTo().alert() —
// переключение контекста с DOM страницы на диалоговое окно браузера
public class AlertsPage extends BasePage {

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    // ─── Timer Alert ───────────────────────────────────────

    // Кнопка которая вызывает alert через 5 секунд
    @FindBy(id = "timerAlertButton")
    WebElement timerAlertButton;

    // Нажимает кнопку и ждёт появления alert до 5 секунд
    // isAlertPresent(5) из BasePage — explicit wait на появление alert
    public AlertsPage verifyAlertWithTimer() {
        click(timerAlertButton);
        Assertions.assertTrue(isAlertPresent(5));
        return this;
    }

    // ─── Confirm Alert ─────────────────────────────────────

    // Кнопка которая вызывает confirm alert (OK / Cancel)
    @FindBy(id = "confirmButton")
    WebElement confirmButton;

    // Кликает по кнопке confirm alert со скроллом
    public AlertsPage clickOnConfirmButton() {
        clickWithJS(confirmButton);
        return this;
    }

    // Выбирает действие в confirm alert
    // "Ok"     → accept()  — нажать кнопку OK
    // "Cancel" → dismiss() — нажать кнопку Cancel
    public AlertsPage selectResult(String result) {
        if (result != null && result.equals("Ok")) {
            driver.switchTo().alert().accept();
        } else if (result != null && result.equals("Cancel")) {
            driver.switchTo().alert().dismiss(); // dismiss() — нажимает Cancel
        }
        return this;
    }

    // Элемент с текстом результата после confirm alert
    @FindBy(id = "confirmResult")
    WebElement confirmResult;

    // Проверяет что текст результата содержит ожидаемую строку
    public AlertsPage verifyResult(String text) {
        Assertions.assertTrue(isContainsText(text, confirmResult));
        return this;
    }

    // ─── Prompt Alert ──────────────────────────────────────

    // Кнопка которая вызывает prompt alert (поле ввода текста)
    @FindBy(id = "promtButton") // намеренная опечатка в id на сайте demoqa
    WebElement promtButton;

    // Кликает по кнопке prompt alert со скроллом
    public AlertsPage clickOnPromptButton() {
        clickWithJS(promtButton);
        return this;
    }

    // Вводит текст в поле prompt alert и подтверждает
    // driver.switchTo().alert() — переключаемся на диалоговое окно
    // .sendKeys() — вводим текст в поле alert
    // .accept()   — нажимаем OK
    public AlertsPage sendMessageToAlert(String text) {
        if (text != null) {
            driver.switchTo().alert().sendKeys(text);
            driver.switchTo().alert().accept();
        }
        return this;
    }

    // Элемент с текстом результата после prompt alert
    @FindBy(id = "promptResult")
    WebElement promptResult;

    // Проверяет что введённый текст отображается в результате
    public AlertsPage verifyMessage(String text) {
        Assertions.assertTrue(isContainsText(text, promptResult));
        return this;
    }
}
