package Homeworks.Lesson26_27.tests;

import Homeworks.Lesson26_27.core.TestBase;
import Homeworks.Lesson26_27.pages.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * IFramesTests — тесты для Nested Frames (the-internet.herokuapp.com/nested_frames).
 *
 * Два теста покрывают две независимые ветки фреймов:
 *   - switchToTopFrame()   → заходит в frame-top → frame-left → проверяет "LEFT"
 *   - switchToBottomFrame() → defaultContent → frame-bottom → проверяет "BOTTOM"
 *
 * Каждый тест стартует с главной страницы (@BeforeEach в TestBase) — контексты не пересекаются.
 */
public class IFramesTests extends TestBase {

    @Test
    @DisplayName("Verify text inside left nested frame")
    public void topFrameTextTest() {
        new HomePage(driver)
                .goToIFramesPage()
                .switchToTopFrame()         // defaultContent → frame-top → frame-left
                .verifyFrameText("LEFT");
    }

    @Test
    @DisplayName("Verify text inside bottom frame")
    public void bottomFrameTextTest() {
        new HomePage(driver)
                .goToIFramesPage()
                .switchToBottomFrame()      // defaultContent → frame-bottom
                .verifyFrameText("BOTTOM");
    }
}