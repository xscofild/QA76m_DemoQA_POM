package Homeworks.Lesson28.tests;

import Homeworks.Lesson28.core.TestBase;
import Homeworks.Lesson28.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContextMenuTest extends TestBase {

    // Тест сценарий:
    //  1. Переходим на страницу Context Menu
    //  2. Делаем правый клик по элементу с пунктирной линией
    //  3. Нажимаем OK в появившемся alert
    //  4. Нажимаем ARROW_DOWN затем ENTER через класс Keys
    //  5. Проверяем что вернулись на домашнюю страницу
    @Test
    public void contextMenuTest() {
        HomePage homePage = new HomePage(driver)
                .getContextMenuPage()
                .rightClickOnBox()
                .acceptAlert()
                .goBackWithKeys();

        Assertions.assertTrue(homePage.isHomePage());
    }
}