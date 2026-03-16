package Homeworks.Lesson26_27.tests;

import Homeworks.Lesson26_27.core.TestBase;
import Homeworks.Lesson26_27.pages.HomePage;
import org.junit.jupiter.api.Test;

public class DropdownTests extends TestBase {

    // Тест сценарий:
    //  1. Переходим на страницу Dropdown
    //  2. Выбираем Option 1 из выпадающего списка
    //  3. Проверяем что Option 1 стал выбранным
    @Test
    public void selectOption1Test() {
        new HomePage(driver)
                .goToDropdownPage()
                .selectOption("1")
                .verifySelectedOption("Option 1");
    }

    // Тест сценарий:
    //  1. Переходим на страницу Dropdown
    //  2. Выбираем Option 2 из выпадающего списка
    //  3. Проверяем что Option 2 стал выбранным
    @Test
    public void selectOption2Test() {
        new HomePage(driver)
                .goToDropdownPage()
                .selectOption("2")
                .verifySelectedOption("Option 2");
    }
}
