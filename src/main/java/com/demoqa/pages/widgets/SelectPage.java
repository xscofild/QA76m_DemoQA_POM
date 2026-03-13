package com.demoqa.pages.widgets;

import com.demoqa.core.BasePage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SelectPage extends BasePage {

    public SelectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "oldSelectMenu")
    WebElement oldSelectMenu;

    @FindBy(id = "react-select-4-input")
    WebElement selectInput;

    @FindBy(css = "html")
    WebElement space;

    public SelectPage oldStyleSelect(String color) {
        new Select(oldSelectMenu).selectByVisibleText(color);
        return this;
    }

    public SelectPage verifyColor(String expectedColor) {
        String actualColor = new Select(oldSelectMenu)
                .getFirstSelectedOption()
                .getText();
        Assertions.assertEquals(expectedColor, actualColor);
        return this;
    }

    public SelectPage multiSelect(String[] colors) {
        for (String text : colors) {
            selectInput.sendKeys(text);
            selectInput.sendKeys(Keys.ENTER);
        }
        click(space);
        return this;
    }

    public SelectPage verifyMultiSelect(String[] colors) {
        SoftAssertions softly = new SoftAssertions();

        for (String text : colors) {
            WebElement element = driver.findElement(By.xpath("//*[.='" + text + "']"));
            softly.assertThat(isContainsText(text, element))
                    .as("Не найден выбранный цвет: " + text)
                    .isTrue();
        }

        softly.assertAll();
        return this;
    }
}