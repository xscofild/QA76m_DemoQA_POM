package Homeworks.Lesson26_27.pages;

import Homeworks.Lesson26_27.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IFramePage extends BasePage {

    public IFramePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "mce_0_ifr")
    private WebElement editorFrame;

    public IFramePage open() {
        driver.get("https://the-internet.herokuapp.com/iframe");
        return this;
    }

    public IFramePage switchToEditor() {
        driver.switchTo().frame(editorFrame);
        return this;
    }

    public IFramePage clearAndType(String text) {
        WebElement body = driver.findElement(By.id("tinymce"));
        js.executeScript("arguments[0].innerHTML = '<p>' + arguments[1] + '</p>';", body, text);
        return this;
    }

    public IFramePage verifyText(String expectedText) {
        WebElement body = driver.findElement(By.id("tinymce"));
        Assertions.assertEquals(expectedText, body.getText().trim());
        return this;
    }
}