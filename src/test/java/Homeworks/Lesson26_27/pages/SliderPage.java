package Homeworks.Lesson26_27.pages;

import Homeworks.Lesson26_27.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SliderPage extends BasePage {

    public SliderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[type='range']")
    private WebElement slider;

    public SliderPage moveSliderRight(int times) {
        for (int i = 0; i < times; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
        return this;
    }

    public SliderPage verifySliderValue(String expectedValue) {
        Assertions.assertEquals(expectedValue, slider.getAttribute("value"));
        return this;
    }
}