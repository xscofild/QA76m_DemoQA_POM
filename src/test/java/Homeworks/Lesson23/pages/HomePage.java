package Homeworks.Lesson23.pages;

import Homeworks.Lesson23.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Form Authentication")
    WebElement formAuthenticationLink;

    public LoginPage goToLoginPage() {
        click(formAuthenticationLink);
        return new LoginPage(driver);
    }
}