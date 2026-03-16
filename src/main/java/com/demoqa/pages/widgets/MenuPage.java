package com.demoqa.pages.widgets;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Страница "Menu" в разделе Widgets
// Демонстрирует работу с hover-меню (выпадающими пунктами при наведении мыши)
//
// actions.moveToElement() — симулирует наведение курсора на элемент
// Это вызывает CSS :hover эффект и показывает вложенные пункты меню
//
// Цепочка наведений: Main Item 2 → SUB SUB LIST → Sub Sub Item 1
// Каждый следующий пункт появляется только после наведения на предыдущий
public class MenuPage extends BasePage {

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    // Пункт главного меню
    @FindBy(xpath = "//a[.='Main Item 2']")
    WebElement mainItem2;

    // Вложенный пункт — появляется после наведения на Main Item 2
    @FindBy(xpath = "//a[.='SUB SUB LIST »']")
    WebElement subSubList;

    // Финальный вложенный пункт — появляется после наведения на SUB SUB LIST
    @FindBy(xpath = "//a[.='Sub Sub Item 1']")
    WebElement subSubItem1;

    // Наводит мышь по цепочке пунктов вложенного меню
    // scrollIntoView — прокручиваем к элементу перед hover
    // Вся цепочка в одном .perform() — Actions не теряет фокус между шагами
    public MenuPage hoverMouseOnSubMenu() {
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", mainItem2);
        actions.moveToElement(mainItem2)
                .moveToElement(subSubList)
                .moveToElement(subSubItem1)
                .perform();
        return this;
    }

    // Проверяет что финальный пункт Sub Sub Item 1 стал видимым
    // waitOfElementVisibility() — ждёт появления элемента до 20 секунд
    // isElementVisible() — проверяет isDisplayed()
    public MenuPage verifySubMenu() {
        waitOfElementVisibility(subSubItem1, 20);
        Assertions.assertTrue(isElementVisible(subSubItem1));
        return this;
    }
}