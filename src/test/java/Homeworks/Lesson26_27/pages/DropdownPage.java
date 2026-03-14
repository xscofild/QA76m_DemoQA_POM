package Homeworks.Lesson26_27.pages;

import Homeworks.Lesson26_27.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePage {

    public DropdownPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "dropdown")
    private WebElement dropdown;

    public DropdownPage selectOption(String value) {
        new Select(dropdown).selectByValue(value);
        return this;
    }

    public DropdownPage verifySelectedOption(String expectedText) {
        String actual = new Select(dropdown).getFirstSelectedOption().getText();
        Assertions.assertEquals(expectedText, actual);
        return this;
    }
}