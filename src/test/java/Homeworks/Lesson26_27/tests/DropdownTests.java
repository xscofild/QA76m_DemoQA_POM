package Homeworks.Lesson26_27.tests;

import Homeworks.Lesson26_27.core.TestBase;
import Homeworks.Lesson26_27.pages.HomePage;
import org.junit.jupiter.api.Test;

public class DropdownTests extends TestBase {

    @Test
    public void selectOption1Test() {
        new HomePage(driver)
                .goToDropdownPage()
                .selectOption("1")
                .verifySelectedOption("Option 1");
    }

    @Test
    public void selectOption2Test() {
        new HomePage(driver)
                .goToDropdownPage()
                .selectOption("2")
                .verifySelectedOption("Option 2");
    }
}