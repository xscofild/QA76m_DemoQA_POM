package Homeworks.Lesson26_27.pages;

import Homeworks.Lesson26_27.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * DragAndDropPage — страница the-internet.herokuapp.com/drag_and_drop.
 *
 * После перетаскивания columnA → columnB колонки меняются местами:
 *   - columnA теперь содержит текст "B"
 *   - columnB теперь содержит текст "A"
 *
 * ВАЖНО: actions.dragAndDrop() работает НЕ на всех сайтах.
 * the-internet.herokuapp.com использует HTML5 Drag & Drop API,
 * с которым у Selenium есть известные проблемы совместимости —
 * тест может быть нестабильным в некоторых браузерах/версиях.
 * Альтернатива: JS-имитация через dispatchEvent (dragstart, dragover, drop).
 */
public class DragAndDropPage extends BasePage {

    public DragAndDropPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "column-a")
    private WebElement columnA;

    @FindBy(id = "column-b")
    private WebElement columnB;

    // dragAndDrop(source, target) — перетаскивает source на target.
    // После выполнения columnA и columnB визуально меняются местами.
    public DragAndDropPage dragAtoB() {
        actions.dragAndDrop(columnA, columnB).perform();
        return this;
    }

    // После свапа: элемент с id="column-a" теперь показывает текст "B".
    public DragAndDropPage verifyColumnAText(String expectedText) {
        Assertions.assertEquals(expectedText, columnA.getText());
        return this;
    }
}