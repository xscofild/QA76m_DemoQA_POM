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

        int count = Integer.parseInt(js.executeScript("return window.frames.length").toString());
        System.out.println("Iframes by JS: " + count);

        return this;
    }

    public FramesPage switchToIframeByIndex(int index) {
        driver.switchTo().frame(index);
        return this;
    }

    @FindBy(id = "frame1")
    WebElement frame1;

    // переключение в iframe по id
    public FramesPage switchToIframeById() {
        driver.switchTo().frame(frame1);
        return this;
    }

    // возврат на основную страницу из iframe
    public FramesPage switchToDefaultContent() {
        driver.switchTo().defaultContent();
        return this;
    }

    @FindBy(id = "sampleHeading")
    WebElement sampleHeading;

    public FramesPage verifyIframeByTitle(String title) {
        Assertions.assertTrue(isContainsText(title, sampleHeading));
        return this;
    }
}