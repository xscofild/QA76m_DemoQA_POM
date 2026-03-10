package Homeworks.Lesson23.pages;

import Homeworks.Lesson23.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "username")
    WebElement usernameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    WebElement loginButton;

    public LoginPage enterCredentials(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        return this;
    }

    public LoginPage clickLogin() {
        click(loginButton);
        return this;
    }
}