package Homeworks.Lesson26_27.pages;

import Homeworks.Lesson26_27.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DragAndDropPage extends BasePage {

    public DragAndDropPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "column-a")
    private WebElement columnA;

    @FindBy(id = "column-b")
    private WebElement columnB;

    public DragAndDropPage dragAtoB() {
        actions.dragAndDrop(columnA, columnB).perform();
        return this;
    }

    public DragAndDropPage verifyColumnsSwapped() {
        Assertions.assertEquals("B", columnA.getText());
        Assertions.assertEquals("A", columnB.getText());
        return this;
    }
}