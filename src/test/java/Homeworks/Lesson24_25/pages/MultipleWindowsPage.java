package Homeworks.Lesson24_25.pages;

import Homeworks.Lesson24_25.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class MultipleWindowsPage extends BasePage {

    public MultipleWindowsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Click Here")
    WebElement clickHereLink;

    @FindBy(tagName = "h3")
    WebElement heading;

    public MultipleWindowsPage clickClickHereLink() {
        click(clickHereLink);
        return this;
    }

    public MultipleWindowsPage switchToNewWindow(int index) {
        getWait(5).until(ExpectedConditions.numberOfWindowsToBe(2));
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(index));
        return this;
    }

    public MultipleWindowsPage verifyHeadingText(String expectedText) {
        getWait(5).until(ExpectedConditions.visibilityOf(heading));
        Assertions.assertEquals(expectedText, heading.getText());
        return this;
    }
}