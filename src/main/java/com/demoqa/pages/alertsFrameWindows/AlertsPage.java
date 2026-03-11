package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlertsPage extends BasePage {

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "timerAlertButton")
    WebElement timerAlertButton;

    public AlertsPage verifyAlertWithtimer() {
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

    public AlertsPage verifyResult(String text) {
        Assertions.assertTrue(shouldHaveText(confirmResult, text, 5));
        return this;
    }

    @FindBy(id = "promtButton")
    WebElement promtButton;

    public AlertsPage clickOnPromptButton() {
        clickWithJS(promtButton);
        return this;
    }

    public AlertsPage sendMessageToAlert(String text) {
        if (text != null) {
            // получаем alert один раз, вводим текст и подтверждаем
            getAlert(3).sendKeys(text);
            driver.switchTo().alert().accept();
        }
        return this;
    }

    @FindBy(id = "promptResult")
    WebElement promptResult;

    public AlertsPage verifyMessage(String text) {
        Assertions.assertTrue(shouldHaveText(promptResult, text, 5));
        return this;
    }
}