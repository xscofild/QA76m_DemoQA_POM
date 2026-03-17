package Homeworks.Lesson28.pages;

import Homeworks.Lesson28.core.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContextMenuPage extends BasePage {

    public ContextMenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "hot-spot")
    WebElement contextMenuBox;

    public ContextMenuPage rightClickOnBox() {
        actions.contextClick(contextMenuBox).perform();
        return this;
    }

    public ContextMenuPage acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return this;
    }

    public HomePage goBackWithKeys() {
        actions.sendKeys(Keys.ARROW_DOWN).perform();
        actions.sendKeys(Keys.ENTER).perform();
        driver.navigate().back();
        return new HomePage(driver);
    }
}