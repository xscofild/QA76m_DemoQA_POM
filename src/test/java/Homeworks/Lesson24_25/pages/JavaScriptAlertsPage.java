package Homeworks.Lesson24_25.pages;

import Homeworks.Lesson24_25.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JavaScriptAlertsPage extends BasePage {

    public JavaScriptAlertsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[text()='Click for JS Alert']")
    WebElement jsAlertButton;

    @FindBy(xpath = "//button[text()='Click for JS Confirm']")
    WebElement jsConfirmButton;

    @FindBy(xpath = "//button[text()='Click for JS Prompt']")
    WebElement jsPromptButton;

    @FindBy(id = "result")
    WebElement resultText;

    public JavaScriptAlertsPage clickJsAlertButton() {
        click(jsAlertButton);
        return this;
    }

    public JavaScriptAlertsPage clickJsConfirmButton() {
        click(jsConfirmButton);
        return this;
    }

    public JavaScriptAlertsPage clickJsPromptButton() {
        click(jsPromptButton);
        return this;
    }

    public JavaScriptAlertsPage acceptAlert() {
        getAlert(5).accept();
        return this;
    }

    public JavaScriptAlertsPage dismissAlert() {
        getAlert(5).dismiss();
        return this;
    }

    public JavaScriptAlertsPage sendTextToPromptAndAccept(String text) {
        Alert alert = getAlert(5);
        alert.sendKeys(text);
        alert.accept();
        return this;
    }

    public JavaScriptAlertsPage verifyResultText(String text) {
        Assertions.assertTrue(shouldHaveText(resultText, text, 5));
        return this;
    }
}