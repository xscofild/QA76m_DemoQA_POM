package com.demoqa.pages.widgets;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

/**
 * MenuPage — работа с вложенным hover-меню.
 *
 * Меню раскрывается только при наведении мыши (CSS hover), поэтому нужен Actions.
 * Цепочка: hover на mainItem2 → появляется subSubList → hover на него → появляется subSubItem1.
 * pause() между шагами — имитация человеческой задержки, без неё меню не успевает раскрыться.
 *
 * Если убрать pause() → подменю может не успеть появиться → MoveTargetOutOfBoundsException или NoSuchElementException.
 * Если убрать scrollTo(0,0) → страница может быть прокручена вниз → hover не попадёт в нужную позицию.
 */
public class MenuPage extends BasePage {

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[.='Main Item 2']")
    private WebElement mainItem2;

    @FindBy(xpath = "//a[.='SUB SUB LIST »']")
    private WebElement subSubList;

    @FindBy(xpath = "//a[.='Sub Sub Item 1']")
    private WebElement subSubItem1;

    public MenuPage hoverMouseOnSubMenu() {
        js.executeScript("window.scrollTo(0, 0)");  // прокрутка в начало страницы перед hover

        actions
                .moveToElement(mainItem2)               // наводим на Main Item 2 → открывается подменю
                .pause(Duration.ofMillis(100))          // ждём рендер подменю
                .moveToElement(subSubList)              // наводим на SUB SUB LIST → открывается вложенное подменю
                .pause(Duration.ofMillis(100))
                .moveToElement(subSubItem1)             // наводим на конечный пункт
                .perform();                             // perform() выполняет всю накопленную цепочку actions
        return this;
    }

    public MenuPage verifySubMenu() {
        Assertions.assertTrue(isElementVisible(subSubItem1));
        return this;
    }
}