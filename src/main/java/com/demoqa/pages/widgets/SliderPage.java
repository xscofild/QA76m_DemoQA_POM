package com.demoqa.pages.widgets;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Страница "Slider" в разделе Widgets
// Демонстрирует управление HTML range input через клавиши Arrow
//
// Почему не dragAndDropBy() как было на лекции:
//   dragAndDropBy() ненадёжен — результат зависит от разрешения экрана и браузера
// Более стабильный способ:
//   1. Читаем текущее значение слайдера
//   2. Нажимаем ARROW_RIGHT (увеличить) или ARROW_LEFT (уменьшить)
//   3. Повторяем пока не достигнем нужного значения
//
// getAttribute("value") — читает текущее значение input[type='range'] из DOM
public class SliderPage extends BasePage {

    public SliderPage(WebDriver driver) {
        super(driver);
    }

    // Сам слайдер — input[type='range']
    // .range-slider — CSS класс слайдера на странице demoqa
    @FindBy(css = ".range-slider")
    WebElement rangeSlider;

    // Числовое поле которое отображает текущее значение слайдера
    @FindBy(id = "sliderValue")
    WebElement sliderValue;

    // Устанавливает значение слайдера нажатиями клавиш Arrow
    // Шаги:
    //  1. Читаем текущее значение через getAttribute("value")
    //  2. Если текущее < целевого — жмём ARROW_RIGHT (увеличить на 1)
    //  3. Если текущее > целевого — жмём ARROW_LEFT  (уменьшить на 1)
    //  4. Повторяем пока не достигнем targetValue
    public SliderPage moveSlider(int targetValue) {
        scrollToElement(rangeSlider);
        int currentValue = Integer.parseInt(rangeSlider.getAttribute("value"));
        while (currentValue < targetValue) {
            rangeSlider.sendKeys(Keys.ARROW_RIGHT);
            currentValue++;
        }
        while (currentValue > targetValue) {
            rangeSlider.sendKeys(Keys.ARROW_LEFT);
            currentValue--;
        }
        return this;
    }

    // Проверяет что числовое поле отображает ожидаемое значение
    // getDomAttribute("value") — читает value атрибут элемента напрямую из DOM
    public SliderPage verifySliderValue(String number) {
        Assertions.assertEquals(number, sliderValue.getDomAttribute("value"));
        return this;
    }
}
