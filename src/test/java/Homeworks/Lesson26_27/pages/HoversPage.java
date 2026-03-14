package Homeworks.Lesson26_27.pages;

import Homeworks.Lesson26_27.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

/**
 * HoversPage — страница the-internet.herokuapp.com/hovers.
 *
 * На странице 3 аватара. При наведении на каждый появляется подпись (caption) с именем пользователя.
 *
 * List<WebElement> figures — все аватары сразу. Доступ по индексу: 0=user1, 1=user2, 2=user3.
 * List<WebElement> captions — подписи в том же порядке что и figures.
 *
 * pause(300ms) после moveToElement — даёт время CSS-анимации появления caption.
 * Без паузы caption может ещё не быть видимым в момент проверки → тест упадёт.
 */
public class HoversPage extends BasePage {

    public HoversPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".figure")
    private List<WebElement> figures;       // все три аватара

    @FindBy(css = ".figcaption h5")
    private List<WebElement> captions;      // подписи под аватарами (скрыты до hover)

    // moveToElement() имитирует наведение мыши — триггерит CSS :hover → показывает caption.
    public HoversPage hoverOverFigure(int index) {
        actions.moveToElement(figures.get(index))
                .pause(Duration.ofMillis(300))   // ждём появления caption
                .perform();
        return this;
    }

    // Две проверки: caption видим + содержит ожидаемый текст ("user1", "user2", "user3").
    public HoversPage verifyCaptionVisible(int index, String expectedText) {
        Assertions.assertTrue(captions.get(index).isDisplayed());
        Assertions.assertTrue(captions.get(index).getText().contains(expectedText));
        return this;
    }
}