package com.demoqa.pages;

import com.demoqa.core.BasePage;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import com.demoqa.pages.alertsFrameWindows.FramesPage;
import com.demoqa.pages.alertsFrameWindows.WindowsPage;
import com.demoqa.pages.bookStore.LoginPage;
import com.demoqa.pages.widgets.SelectPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SidePanel extends BasePage {

    public SidePanel(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[.='Login']")
    WebElement login;

    public LoginPage selectLogin() {
        clickWithJS(login);
        return new LoginPage(driver);
    }

    @FindBy(xpath = "//span[.='Text Box']")
    WebElement textBox;

    public JSExecutor selectTextBox() {
        clickWithJS(textBox);
        return new JSExecutor(driver);
    }

    @FindBy(xpath = "//span[.='Alerts']")
    WebElement alerts;

    public AlertsPage selectAlerts() {
        clickWithJS(alerts);
        return new AlertsPage(driver);
    }

    @FindBy(xpath = "//span[.='Browser Windows']")
    WebElement browserWindows;

    public WindowsPage selectWindows() {
        clickWithJS(browserWindows);
        return new WindowsPage(driver);
    }

    @FindBy(xpath = "//span[.='Frames']")
    WebElement frames;

    public FramesPage selectFrame() {
        clickWithJS(frames);
        return new FramesPage(driver);
    }

    @FindBy(xpath = "//span[.='Select Menu']")
    WebElement selectMenu;

    public SelectPage selectSelect() {
        js.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", selectMenu);
        return new SelectPage(driver);
    }

    @FindBy(xpath = "//span[.='Nested Frames']")
    WebElement nestedFrames;

    public FramesPage selectNestedFrames() {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", nestedFrames);
        return new FramesPage(driver);
    }
}