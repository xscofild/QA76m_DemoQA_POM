package Homeworks.Lesson26_27.tests;

import Homeworks.Lesson26_27.core.TestBase;
import Homeworks.Lesson26_27.pages.HomePage;
import org.junit.jupiter.api.Test;

public class HoversTests extends TestBase {

    // Тест сценарий:
    //  1. Переходим на страницу Hovers
    //  2. Наводим курсор на первую картинку (index=0)
    //  3. Проверяем что появилась подпись "name: user1"
    @Test
    public void hoverFirstImageTest() {
        new HomePage(driver)
                .goToHoversPage()
                .hoverOverFigure(0)
                .verifyCaptionVisible(0, "user1");
    }

    // Тест сценарий:
    //  1. Переходим на страницу Hovers
    //  2. Наводим курсор на вторую картинку (index=1)
    //  3. Проверяем что появилась подпись "name: user2"
    @Test
    public void hoverSecondImageTest() {
        new HomePage(driver)
                .goToHoversPage()
                .hoverOverFigure(1)
                .verifyCaptionVisible(1, "user2");
    }

    // Тест сценарий:
    //  1. Переходим на страницу Hovers
    //  2. Наводим курсор на третью картинку (index=2)
    //  3. Проверяем что появилась подпись "name: user3"
    @Test
    public void hoverThirdImageTest() {
        new HomePage(driver)
                .goToHoversPage()
                .hoverOverFigure(2)
                .verifyCaptionVisible(2, "user3");
    }
}
