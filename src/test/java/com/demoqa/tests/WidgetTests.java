package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.widgets.MenuPage;
import com.demoqa.pages.widgets.SelectPage;
import com.demoqa.pages.widgets.SliderPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * WidgetTests — тесты для раздела Widgets (Select, Menu, Slider).
 *
 * sidePanel и select — поля класса, так как используются в нескольких тестах.
 * MenuPage и SliderPage создаются прямо в тесте — используются только один раз.
 *
 * Каждый тест независим: @BeforeEach открывает Widgets с нуля перед каждым тестом.
 */
public class WidgetTests extends TestBase {

    SidePanel sidePanel;
    SelectPage select;

    @BeforeEach
    public void precondition() {
        new HomePage(driver).selectWidgets();
        sidePanel = new SidePanel(driver);
        select = new SelectPage(driver);
    }

    @Test
    public void oldStyleSelectMenuTest() {
        sidePanel.selectSelect();
        select.oldStyleSelect("Yellow").verifyColor("Yellow"); // нативный HTML select
    }

    @Test
    public void multiSelectTest() {
        sidePanel.selectSelect();
        // передаём массив строк → выбирает каждый через sendKeys+Enter → проверяет наличие тегов
        select.multiSelect(new String[]{"Green", "Red"}).verifyMultiSelect(new String[]{"Green", "Red"});
    }

    @Test
    public void standardMultiSelectTest() {
        sidePanel.selectSelect();
        // кликает по option "volvo" → проверяет цвет фона в HEX формате
        select.verifySelectedCarByFormat("volvo", "#1967d2");
    }

    @Test
    public void hoverMouseOnMenuTest() {
        sidePanel.getMenu();
        // SidePanel.getMenu() уже переходит на страницу, но MenuPage создаём заново с тем же driver
        new MenuPage(driver).hoverMouseOnSubMenu().verifySubMenu();
    }

    @Test
    public void sliderTest() {
        sidePanel.getSlider();
        // verifySliderValue не вызывается — тест только проверяет что moveSlider() не падает
        // Для полноценного теста нужно добавить .verifySliderValue("ожидаемое значение")
        new SliderPage(driver).moveSlider();
    }
}