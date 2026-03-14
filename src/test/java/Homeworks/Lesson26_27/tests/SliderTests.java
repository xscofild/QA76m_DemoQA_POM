package Homeworks.Lesson26_27.tests;

import Homeworks.Lesson26_27.core.TestBase;
import Homeworks.Lesson26_27.pages.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * SliderTests — тест для the-internet.herokuapp.com/horizontal_slider.
 *
 * moveSlider(200) → тянет слайдер на 200px вправо → ожидаем значение "5" (максимум).
 * verifySliderValue("5") → читает getAttribute("value") из input[type=range].
 *
 * Значение "5" зависит от offsetX=200 и ширины слайдера на конкретном экране.
 * При изменении разрешения браузера тест может давать другой результат.
 */
public class SliderTests extends TestBase {

    @Test
    @DisplayName("Move slider to the right and verify value")
    public void moveSliderTest() {
        new HomePage(driver)
                .goToSliderPage()
                .moveSlider(200)        // 200px вправо → значение "5"
                .verifySliderValue("5");
    }
}