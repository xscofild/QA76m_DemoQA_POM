package Homeworks.Lesson24_25.pages;

import Homeworks.Lesson24_25.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SecureAreaPage extends BasePage {

    public SecureAreaPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "flash")
    WebElement flashMessage;

    public SecureAreaPage verifyLoginSuccess() {
        Assertions.assertTrue(shouldHaveText(flashMessage, "You logged into a secure area", 5));
        return this;
    }

    public SecureAreaPage verifyInvalidPasswordMessage() {
        Assertions.assertTrue(shouldHaveText(flashMessage, "Your password is invalid", 5));
        return this;
    }
}