package Homeworks.Lesson26_27.tests;

import Homeworks.Lesson26_27.core.TestBase;
import Homeworks.Lesson26_27.pages.HomePage;
import org.junit.jupiter.api.Test;

public class SliderTests extends TestBase {

    // Тест сценарий:
    //  1. Переходим на страницу Horizontal Slider
    //  2. Нажимаем ARROW_RIGHT 10 раз — каждый шаг = 0.5
    //  3. Проверяем что значение слайдера стало 5
    @Test
    public void moveSliderTest() {
        new HomePage(driver)
                .goToSliderPage()
                .moveSliderRight(10)
                .verifySliderValue("5");
    }
}
