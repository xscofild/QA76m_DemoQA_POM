package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FramesPage extends BasePage {

    public FramesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "iframe")
    List<WebElement> iframes;

    public FramesPage returnListOfIframes() {
        System.out.println("Iframes by WebElements: " + iframes.size());

        Object jsResult = js.executeScript("return window.frames.length");
        int count = jsResult != null ? Integer.parseInt(jsResult.toString()) : 0;
        System.out.println("Iframes by JS: " + count);

        return this;
    }

    public FramesPage switchToIframeByIndex(int index) {
        driver.switchTo().frame(index);
        return this;
    }

    @FindBy(id = "frame1")
    WebElement frame1;

    public FramesPage switchToIframeById() {
        driver.switchTo().frame(frame1);
        return this;
    }

    public FramesPage switchToMainPage() {
        driver.switchTo().defaultContent();
        return this;
    }

    @FindBy(id = "sampleHeading")
    WebElement sampleHeading;

    public FramesPage verifyIframeByTitle(String title) {
        Assertions.assertTrue(isContainsText(title, sampleHeading));
        return this;
    }

    @FindBy(css = ".text-center")
    WebElement textCenter;

    public FramesPage verifyMainPageTitle(String text) {
        Assertions.assertTrue(isContainsText(text, textCenter));
        return this;
    }

    public void handleNestedFrames() {
    }
}