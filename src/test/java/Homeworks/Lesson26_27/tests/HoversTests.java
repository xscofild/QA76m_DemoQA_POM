package Homeworks.Lesson26_27.tests;

import Homeworks.Lesson26_27.core.TestBase;
import Homeworks.Lesson26_27.pages.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * HoversTests — тесты для the-internet.herokuapp.com/hovers.
 *
 * Три теста — три аватара (индексы 0, 1, 2).
 * Каждый тест: hover на фигуру → проверяем что caption видим и содержит имя пользователя.
 *
 * Тесты независимы друг от друга — каждый открывает HoversPage заново через @BeforeEach.
 * Индекс в hoverOverFigure(n) и verifyCaptionVisible(n, ...) должны совпадать!
 */
public class HoversTests extends TestBase {

    @Test
    @DisplayName("Hover over first figure and verify caption")
    public void hoverOverFirstFigureTest() {
        new HomePage(driver)
                .goToHoversPage()
                .hoverOverFigure(0)
                .verifyCaptionVisible(0, "user1");
    }

    @Test
    @DisplayName("Hover over second figure and verify caption")
    public void hoverOverSecondFigureTest() {
        new HomePage(driver)
                .goToHoversPage()
                .hoverOverFigure(1)
                .verifyCaptionVisible(1, "user2");
    }

    @Test
    @DisplayName("Hover over third figure and verify caption")
    public void hoverOverThirdFigureTest() {
        new HomePage(driver)
                .goToHoversPage()
                .hoverOverFigure(2)
                .verifyCaptionVisible(2, "user3");
    }
}