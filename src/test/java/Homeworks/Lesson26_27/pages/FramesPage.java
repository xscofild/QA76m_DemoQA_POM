package Homeworks.Lesson26_27.pages;

import Homeworks.Lesson26_27.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FramesPage extends BasePage {

    public FramesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "iFrame")
    WebElement iFrameLink;

    @FindBy(id = "tinymce")
    WebElement editorBody;

    public FramesPage goToIFrame() {
        click(iFrameLink);
        // ждём пока TinyMCE toolbar загрузится — это значит страница готова
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[role='menubar']")));
        return this;
    }

    public FramesPage switchToIFrame() {
        driver.switchTo().frame("mce_0_ifr");
        return this;
    }

    public FramesPage verifyTextInIFrame(String expectedText) {
        Assertions.assertTrue(editorBody.getText().contains(expectedText));
        return this;
    }

    public FramesPage switchToMainPage() {
        driver.switchTo().defaultContent();
        return this;
    }
}