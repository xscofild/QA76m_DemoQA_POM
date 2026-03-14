package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * AlertsPage — страница работы с браузерными алертами (alert / confirm / prompt).
 *
 * Все методы возвращают this (AlertsPage) → поддерживают Method Chaining в тесте.
 * Selenium работает с алертами через driver.switchTo().alert() — это отдельный контекст,
 * пока алерт открыт, нельзя взаимодействовать с элементами страницы.
 */
public class AlertsPage extends BasePage {

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "timerAlertButton")
    WebElement timerAlertButton;

    // Кнопка запускает алерт с задержкой (таймер на сайте ~5 сек).
    // isAlertPresent(5) ждёт алерт 5 сек → если появился, принимает и возвращает true.
    public AlertsPage verifyAlertWithTimer() {
        click(timerAlertButton);
        Assertions.assertTrue(isAlertPresent(5));
        return this;
    }

    @FindBy(id = "confirmButton")
    WebElement confirmButton;

    public AlertsPage clickOnConfirmButton() {
        clickWithJS(confirmButton);
        return this;
    }

    /**
     * Принимает или отклоняет confirm-алерт в зависимости от параметра.
     * accept() → нажимает "Ok", dismiss() → нажимает "Cancel".
     * getAlert(3) — явное ожидание появления алерта, защита от гонки условий.
     */
    public AlertsPage selectResult(String result) {
        if ("Ok".equals(result)) {
            getAlert(3).accept();
        } else if ("Cancel".equals(result)) {
            getAlert(3).dismiss();
        }
        return this;
    }

    @FindBy(id = "confirmResult")
    WebElement confirmResult;

    // Проверяет что на странице появился текст с результатом выбора (Ok/Cancel).
    public AlertsPage verifyResult(String text) {
        Assertions.assertTrue(shouldHaveText(confirmResult, text, 5));
        return this;
    }

    @FindBy(id = "promtButton")
    WebElement promtButton;  // опечатка в id на сайте — "promt" вместо "prompt", оставляем как есть

    public AlertsPage clickOnPromptButton() {
        clickWithJS(promtButton);
        return this;
    }

    /**
     * Вводит текст в prompt-алерт и принимает его.
     * getAlert(3).sendKeys() — вводит текст в поле алерта.
     * driver.switchTo().alert().accept() — второй переход к алерту для принятия.
     * Внимание: это два обращения к одному и тому же алерту — избыточно.
     * Можно упростить: Alert a = getAlert(3); a.sendKeys(text); a.accept();
     */
    public AlertsPage sendMessageToAlert(String text) {
        if (text != null) {
            getAlert(3).sendKeys(text);
            driver.switchTo().alert().accept();
        }
        return this;
    }

    @FindBy(id = "promptResult")
    WebElement promptResult;

    // Проверяет что введённый в алерт текст отобразился на странице.
    public AlertsPage verifyMessage(String text) {
        Assertions.assertTrue(shouldHaveText(promptResult, text, 5));
        return this;
    }
}