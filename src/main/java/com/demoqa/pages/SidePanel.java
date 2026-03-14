package com.demoqa.pages;

import com.demoqa.core.BasePage;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import com.demoqa.pages.alertsFrameWindows.FramesPage;
import com.demoqa.pages.alertsFrameWindows.WindowsPage;
import com.demoqa.pages.bookStore.LoginPage;
import com.demoqa.pages.widgets.MenuPage;
import com.demoqa.pages.widgets.SelectPage;
import com.demoqa.pages.widgets.SliderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * SidePanel — боковое меню навигации (одно на все разделы сайта).
 *
 * Структурно важно: SidePanel содержит элементы ВСЕХ разделов (Alerts, Widgets, BookStore и т.д.)
 * на одном классе. Это работает потому что @FindBy ищет элемент только в момент обращения к нему,
 * а не при создании объекта — если элемента нет на текущей странице, получим NoSuchElementException.
 *
 * Каждый select/get-метод:
 *   1. Кликает по пункту меню → переходит на страницу
 *   2. Создаёт и возвращает соответствующий Page Object → тест продолжает цепочку
 *
 * Если нужно добавить новый пункт меню — добавь @FindBy + метод по аналогии.
 * Если убрать метод — тест, который его вызывает, не скомпилируется.
 */
public class SidePanel extends BasePage {

    public SidePanel(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[.='Login']")
    WebElement login;

    @FindBy(xpath = "//span[.='Text Box']")
    WebElement textBox;

    @FindBy(xpath = "//span[.='Alerts']")
    WebElement alerts;

    @FindBy(xpath = "//span[.='Browser Windows']")
    WebElement browserWindows;

    @FindBy(xpath = "//span[.='Frames']")
    WebElement frames;

    @FindBy(xpath = "//span[.='Nested Frames']")
    WebElement nestedFrames;

    @FindBy(xpath = "//span[.='Select Menu']")
    WebElement selectMenu;

    @FindBy(xpath = "//span[.='Menu']")
    WebElement menu;

    @FindBy(xpath = "//span[.='Slider']")
    WebElement slider;

    // Каждый метод возвращает Page Object той страницы, на которую ведёт пункт меню.
    // Тип возврата жёстко связан с конкретной страницей — изменишь структуру сайта, нужно менять здесь.

    public LoginPage selectLogin() {
        clickWithJS(login);
        return new LoginPage(driver);
    }

    public JSExecutor selectTextBox() {
        clickWithJS(textBox);
        return new JSExecutor(driver);
    }

    public AlertsPage selectAlerts() {
        clickWithJS(alerts);
        return new AlertsPage(driver);
    }

    public WindowsPage selectWindows() {
        clickWithJS(browserWindows);
        return new WindowsPage(driver);
    }

    public FramesPage selectFrame() {
        clickWithJS(frames);
        return new FramesPage(driver);
    }

    public FramesPage selectNestedFrames() {
        clickWithJS(nestedFrames);
        return new FramesPage(driver);  // тот же FramesPage — разные страницы, но один класс для обоих
    }

    public SelectPage selectSelect() {
        clickWithJS(selectMenu);
        return new SelectPage(driver);
    }

    public MenuPage getMenu() {
        clickWithJS(menu);
        return new MenuPage(driver);
    }

    public SliderPage getSlider() {
        clickWithJS(slider);
        return new SliderPage(driver);
    }
}