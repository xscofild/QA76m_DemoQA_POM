package Homeworks.Lesson24_25.tests;

import Homeworks.Lesson24_25.core.TestBase;
import Homeworks.Lesson24_25.pages.HomePage;
import org.junit.jupiter.api.Test;

public class MultipleWindowsTests extends TestBase {

    // Тест сценарий:
    //  1. Переходим на страницу Multiple Windows
    //  2. Кликаем по ссылке "Click Here" — открывается новая вкладка
    //  3. Переключаемся на новую вкладку (index=1)
    //  4. Проверяем что заголовок на новой вкладке равен "New Window"
    @Test
    public void newWindowHeadingTest() {
        new HomePage(driver)
                .goToMultipleWindowsPage()
                .clickClickHereLink()
                .switchToNewWindow(1)
                .verifyHeadingText("New Window");
    }
}
