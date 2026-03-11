package com.demoqa.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    // scrollIntoView + клик
    public void clickWithJS(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", element);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public WebDriverWait getWait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    // ожидание появления alert
    public Alert getAlert(int seconds) {
        return getWait(seconds).until(ExpectedConditions.alertIsPresent());
    }

    public boolean isAlertPresent(int seconds) {
        try {
            getAlert(seconds).accept();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isContainsText(String text, WebElement element) {
        getWait(5).until(ExpectedConditions.visibilityOf(element));
        return element.getText().contains(text);
    }

    public boolean shouldHaveText(WebElement element, String text, int seconds) {
        return getWait(seconds).until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void switchToNewTabWindow(int index) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(index));
    }
}