package Homeworks.Lesson26_27.pages;

import Homeworks.Lesson26_27.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * IFramesPage — страница с вложенными фреймами (Nested Frames).
 *
 * Структура фреймов на странице:
 *   frame-top
 *     ├── frame-left   ("LEFT")
 *     ├── frame-middle ("MIDDLE")
 *     └── frame-right  ("RIGHT")
 *   frame-bottom       ("BOTTOM")
 *
 * Для работы с вложенными фреймами важна последовательность switchTo():
 *   - Нельзя прыгнуть сразу в frame-left из defaultContent → сначала нужно войти в frame-top.
 *   - После работы с top → defaultContent() ОБЯЗАТЕЛЬНО перед переходом в frame-bottom,
 *     иначе Selenium ищет frame-bottom ВНУТРИ frame-top → NoSuchFrameException.
 *
 * @FindBy(tagName = "body") ищет <body> текущего активного фрейма (не основного документа!).
 * Тот же @FindBy работает и в top, и в bottom — потому что PageFactory резолвит элемент
 * в момент обращения, в текущем контексте драйвера.
 */
public class IFramesPage extends BasePage {

    public IFramesPage(WebDriver driver) {
        super(driver);
    }

    // <body> текущего активного фрейма — резолвится в том контексте, в котором сейчас находится driver
    @FindBy(tagName = "body")
    private WebElement frameBody;

    /**
     * Переходит в левый вложенный фрейм через два шага:
     *   1. defaultContent → frame-top  (входим в родительский фрейм)
     *   2. frame-top → frame-left      (входим во вложенный)
     * После этого frameBody.getText() вернёт "LEFT".
     */
    public IFramesPage switchToTopFrame() {
        driver.switchTo().frame("frame-top");   // шаг 1: в родительский
        driver.switchTo().frame("frame-left");  // шаг 2: во вложенный
        return this;
    }

    /**
     * Переходит в bottom фрейм.
     * defaultContent() ОБЯЗАТЕЛЕН — сбрасывает контекст в основной документ,
     * иначе driver будет искать frame-bottom внутри текущего фрейма.
     */
    public IFramesPage switchToBottomFrame() {
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-bottom");
        return this;
    }

    public IFramesPage switchToDefaultContent() {
        driver.switchTo().defaultContent();
        return this;
    }

    public IFramesPage verifyFrameText(String expectedText) {
        Assertions.assertTrue(frameBody.getText().contains(expectedText));
        return this;
    }
}