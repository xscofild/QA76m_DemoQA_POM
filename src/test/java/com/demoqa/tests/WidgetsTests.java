package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.widgets.MenuPage;
import com.demoqa.pages.widgets.SelectPage;
import com.demoqa.pages.widgets.SliderPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Тесты для раздела "Widgets"
// Покрывает: Old Style Select, Multi Select, Standard Multi Select, Slider, Menu hover
// @BeforeEach — создаём Page Objects и переходим в раздел Widgets
public class WidgetsTests extends TestBase {

    SidePanel sidePanel;
    SelectPage selectPage;

    @BeforeEach
    public void precondition() {
        new HomePage(driver).selectWidgets();
        sidePanel = new SidePanel(driver);
        selectPage = new SelectPage(driver);
    }

    // Тест: выбор значения в стандартном HTML select
    // Select.selectByVisibleText() — выбирает опцию по видимому тексту
    // Select.getFirstSelectedOption() — проверяет что опция выбрана
    @Test
    public void oldStyleSelectMenuTest() {
        sidePanel.selectSelect();
        selectPage.oldStyleSelect("Aqua")
                .verifyColor();
    }

    // Тест: множественный выбор в react-select
    // sendKeys(text) + Enter для каждого значения
    // SoftAssertions — проверяет все значения и показывает все ошибки сразу
    @Test
    public void multiSelectTest() {
        sidePanel.selectSelect();
        selectPage.multiSelect(new String[]{"Green", "Red"})
                .verifyMultiSelect(new String[]{"Green", "Red"});
    }

    // Тест: проверка цвета выбранной опции в HEX формате
    // Color.fromString().asHex() — конвертирует rgba → #hex
    @Test
    public void standardMultiSelect() {
        sidePanel.selectSelect();
        selectPage.verifySelectCarByFormat("volvo", "#1967d2");
    }

    // Тест: наведение мыши на вложенное меню
    // actions.moveToElement() — симулирует hover для каждого пункта цепочки
    // Результат: Sub Sub Item 1 становится видимым
    @Test
    public void hoverMouseOnMenuTest() {
        sidePanel.getMenu();
        new MenuPage(driver).hoverMouseOnSubMenu()
                .verifySubMenu();
    }

    // Тест: установка значения слайдера
    // moveSlider(100) — нажимает ARROW_RIGHT пока не достигнет 100
    // verifySliderValue("100") — проверяет что числовое поле показывает 100
    @Test
    public void sliderTest() {
        sidePanel.getSlider();
        new SliderPage(driver).moveSlider(100)
                .verifySliderValue("100");
    }
}
