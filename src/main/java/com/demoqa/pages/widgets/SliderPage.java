package com.demoqa.pages.widgets;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * SliderPage — работа со слайдером через Actions (drag & hold).
 *
 * Слайдер нельзя заполнить через sendKeys или click — только через мышиное перетаскивание.
 * moveByOffset(x, y): смещение в пикселях относительно текущей позиции мыши.
 * x=40 → сдвигает вправо на 40px. Результирующее значение зависит от ширины слайдера на странице.
 *
 * Хрупкость: offset в пикселях зависит от разрешения/зума браузера → тест может давать разные значения.
 * Для стабильности лучше рассчитывать offset динамически от ширины элемента.
 */
public class SliderPage extends BasePage {

    public SliderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".range-slider")
    WebElement rangeSlider;

    public SliderPage moveSlider() {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", rangeSlider);

        actions.clickAndHold(rangeSlider)   // зажимаем левую кнопку мыши на слайдере
                .moveByOffset(40, 0)        // тянем вправо на 40px (y=0 — не двигаем по вертикали)
                .release()                  // отпускаем мышь
                .perform();

        return this;
    }

    // getAttribute("value") — читает текущее значение input[type=range] из DOM-атрибута.
    public SliderPage verifySliderValue(String value) {
        Assertions.assertEquals(value, rangeSlider.getAttribute("value"));
        return this;
    }
}