package Homeworks.Lesson26_27.core;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage — общий родитель для всех Page Objects этого HW-проекта.
 *
 * Отличие от BasePage в основном проекте (demoqa):
 *   - нет JavascriptExecutor (js) — этот сайт не требует JS-кликов
 *   - нет scrollToElement / clickWithJS — элементы доступны без скролла
 *   - более простая база: только click, type, wait, alert
 *
 * Всё остальное — та же архитектура: driver + Actions + PageFactory.
 */
public class BasePage {

    protected WebDriver driver;
    protected Actions actions;  // для drag&drop, hover, slider

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);  // инициализирует все @FindBy поля — обязательно!
        actions = new Actions(driver);
    }

    public void click(WebElement element) {
        element.click();
    }

    // clear() перед sendKeys — чтобы не дописывать поверх старого текста
    public void type(WebElement element, String text) {
        if (text != null) {
            element.clear();
            element.sendKeys(text);
        }
    }

    public WebDriverWait getWait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public Alert getAlert(int seconds) {
        return getWait(seconds).until(ExpectedConditions.alertIsPresent());
    }

    public boolean shouldHaveText(WebElement element, String text, int seconds) {
        return getWait(seconds).until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}