package Homeworks.Lesson26_27.tests;

import Homeworks.Lesson26_27.core.TestBase;
import Homeworks.Lesson26_27.pages.HomePage;
import org.junit.jupiter.api.Test;

public class IFrameTests extends TestBase {

    // Тест сценарий:
    //  1. Переходим на страницу Frames → выбираем iFrame
    //  2. Переключаемся внутрь iframe редактора TinyMCE
    //  3. Проверяем что внутри iframe есть текст "Your content goes here."
    //  4. Возвращаемся в основной DOM страницы
    @Test
    public void iFrameTextTest() {
        new HomePage(driver)
                .goToFramesPage()
                .goToIFrame()
                .switchToIFrame()
                .verifyTextInIFrame("Your content goes here.")
                .switchToMainPage();
    }
}
