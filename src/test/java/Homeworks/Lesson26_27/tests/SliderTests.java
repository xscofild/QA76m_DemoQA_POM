package Homeworks.Lesson26_27.tests;

import Homeworks.Lesson26_27.core.TestBase;
import Homeworks.Lesson26_27.pages.HomePage;
import org.junit.jupiter.api.Test;

public class SliderTests extends TestBase {

    @Test
    public void moveSliderTest() {
        new HomePage(driver)
                .goToSliderPage()
                .moveSliderRight(10)
                .verifySliderValue("5");
    }
}