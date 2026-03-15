package Homeworks.Lesson26_27.pages;

import Homeworks.Lesson26_27.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IFramePage extends BasePage {

    public IFramePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[href='/frames']")
    WebElement framesLink;

    @FindBy(css = "h3")
    WebElement header;

    @FindBy(css = "a[href='/iframe']")
    WebElement iFrameLink;

    @FindBy(id = "mce_0_ifr")
    WebElement editorFrame;

    @FindBy(id = "tinymce")
    WebElement editorBody;

    public IFramePage clickFrames() {
        click(framesLink);
        Assertions.assertEquals("Frames", header.getText());
        return this;
    }

    public IFramePage clickIFrame() {
        click(iFrameLink);
        Assertions.assertEquals("An iFrame containing the TinyMCE WYSIWYG Editor", header.getText());
        return this;
    }

    public IFramePage switchToIFrame() {
        driver.switchTo().frame(editorFrame);
        return this;
    }

    public IFramePage verifyTextInIFrame(String expectedText) {
        Assertions.assertTrue(editorBody.getText().contains(expectedText));
        return this;
    }

    public IFramePage switchToMainPage() {
        driver.switchTo().defaultContent();
        return this;
    }
}