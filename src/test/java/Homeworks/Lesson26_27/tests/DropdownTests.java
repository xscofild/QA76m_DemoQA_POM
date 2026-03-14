package Homeworks.Lesson26_27.tests;

import Homeworks.Lesson26_27.core.TestBase;
import Homeworks.Lesson26_27.pages.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * DropdownTests — тесты для нативного select на the-internet.herokuapp.com/dropdown.
 *
 * Два теста — два значения dropdown ("1" и "2").
 * selectByValue("1") → verifySelectedOption("Option 1"): проверяем value→text маппинг.
 * Каждый тест независим: стартует с главной страницы, сам переходит на Dropdown.
 */
public class DropdownTests extends TestBase {

    @Test
    @DisplayName("Select Option 1 from dropdown")
    public void selectOption1Test() {
        new HomePage(driver)
                .goToDropdownPage()
                .selectByValue("1")
                .verifySelectedOption("Option 1");
    }

    @Test
    @DisplayName("Select Option 2 from dropdown")
    public void selectOption2Test() {
        new HomePage(driver)
                .goToDropdownPage()
                .selectByValue("2")
                .verifySelectedOption("Option 2");
    }
}