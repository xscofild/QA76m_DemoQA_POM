package Homeworks.Lesson23.pages;

import Homeworks.Lesson23.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SecurePage extends BasePage {

    public SecurePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "flash")
    WebElement flashMessage;

    public SecurePage verifyLoginSuccess() {
        getWait(5).until(ExpectedConditions.visibilityOf(flashMessage));
        Assertions.assertTrue(flashMessage.getText().contains("You logged into a secure area"));
        return this;
    }

    public SecurePage verifyLoginFailed() {
        getWait(5).until(ExpectedConditions.visibilityOf(flashMessage));
        Assertions.assertTrue(flashMessage.getText().contains("Your password is invalid"));
        return this;
    }
}
