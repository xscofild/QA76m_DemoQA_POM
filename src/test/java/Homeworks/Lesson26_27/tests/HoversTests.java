package Homeworks.Lesson26_27.tests;

import Homeworks.Lesson26_27.core.TestBase;
import Homeworks.Lesson26_27.pages.HomePage;
import org.junit.jupiter.api.Test;

public class HoversTests extends TestBase {

    @Test
    public void hoverFirstImageTest() {
        new HomePage(driver)
                .goToHoversPage()
                .hoverOverFigure(0)
                .verifyCaptionVisible(0, "user1");
    }

    @Test
    public void hoverSecondImageTest() {
        new HomePage(driver)
                .goToHoversPage()
                .hoverOverFigure(1)
                .verifyCaptionVisible(1, "user2");
    }

    @Test
    public void hoverThirdImageTest() {
        new HomePage(driver)
                .goToHoversPage()
                .hoverOverFigure(2)
                .verifyCaptionVisible(2, "user3");
    }
}