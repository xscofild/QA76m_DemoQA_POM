package Homeworks.Lesson26_27.pages;

import Homeworks.Lesson26_27.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * SliderPage — страница the-internet.herokuapp.com/horizontal_slider.
 *
 * Слайдер: input[type='range'], диапазон 0–5 с шагом 0.5.
 *
 * dragAndDropBy(element, offsetX, offsetY) — перетаскивает элемент на offsetX пикселей по X.
 * Отличие от demoqa SliderPage: там использовался clickAndHold().moveByOffset().release(),
 * здесь — dragAndDropBy() — более компактная запись того же действия.
 *
 * Хрупкость: offsetX=200 → значение "5" зависит от ширины слайдера в браузере.
 * При другом разрешении или зуме результат может отличаться.
 * Для стабильности: рассчитывать offset динамически через element.getSize().getWidth().
 */
public class SliderPage extends BasePage {

    public SliderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[type='range']")
    private WebElement slider;

    // dragAndDropBy: тянет slider на offsetX пикселей вправо (y=0 — без вертикального движения)
    public SliderPage moveSlider(int offsetX) {
        actions.dragAndDropBy(slider, offsetX, 0).perform();
        return this;
    }

    // getAttribute("value") читает текущее значение input[type=range] из DOM
    public SliderPage verifySliderValue(String expectedValue) {
        Assertions.assertEquals(expectedValue, slider.getAttribute("value"));
        return this;
    }
}