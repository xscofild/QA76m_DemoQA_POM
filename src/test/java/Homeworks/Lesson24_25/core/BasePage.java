package Homeworks.Lesson24_25.core;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            element.clear();
            element.sendKeys(text);
        }
    }

    public WebDriverWait getWait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public Alert getAlert(int seconds) {
        return getWait(seconds).until(ExpectedConditions.alertIsPresent());
    }

    public boolean shouldHaveText(WebElement element, String text, int seconds) {
        return getWait(seconds)
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}