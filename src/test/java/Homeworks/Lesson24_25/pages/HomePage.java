package Homeworks.Lesson24_25.pages;

import Homeworks.Lesson24_25.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Form Authentication")
    WebElement formAuthenticationLink;

    @FindBy(linkText = "JavaScript Alerts")
    WebElement javaScriptAlertsLink;

    @FindBy(linkText = "Multiple Windows")
    WebElement multipleWindowsLink;

    public LoginPage goToLoginPage() {
        click(formAuthenticationLink);
        return new LoginPage(driver);
    }

    public JavaScriptAlertsPage goToJavaScriptAlertsPage() {
        click(javaScriptAlertsLink);
        return new JavaScriptAlertsPage(driver);
    }

    public MultipleWindowsPage goToMultipleWindowsPage() {
        click(multipleWindowsLink);
        return new MultipleWindowsPage(driver);
    }
}