package Homeworks.Lesson26_27.pages;

import Homeworks.Lesson26_27.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HoversPage extends BasePage {

    public HoversPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".figure")
    private List<WebElement> figures;

    public HoversPage hoverOverFigure(int index) {
        WebElement figure = figures.get(index);
        actions.moveToElement(figure).perform();
        wait.until(ExpectedConditions.visibilityOf(
                figure.findElement(By.cssSelector(".figcaption"))
        ));
        return this;
    }

    public HoversPage verifyCaptionVisible(int index, String expectedText) {
        WebElement caption = figures.get(index).findElement(By.cssSelector(".figcaption h5"));
        Assertions.assertTrue(caption.isDisplayed());
        Assertions.assertEquals("name: " + expectedText, caption.getText());
        return this;
    }
}