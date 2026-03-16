package com.demoqa.pages;

import com.demoqa.core.BasePage;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import com.demoqa.pages.alertsFrameWindows.FramesPage;
import com.demoqa.pages.alertsFrameWindows.WindowsPage;
import com.demoqa.pages.bookStore.LoginPage;
import com.demoqa.pages.elements.ButtonsPage;
import com.demoqa.pages.widgets.MenuPage;
import com.demoqa.pages.widgets.SelectPage;
import com.demoqa.pages.widgets.SliderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Боковое меню навигации — появляется после входа в любой раздел с HomePage
// Каждый метод кликает по пункту меню и возвращает соответствующий Page Object
// Это основа паттерна Page Object Model (POM) — каждая страница = отдельный класс
public class SidePanel extends BasePage {

    public SidePanel(WebDriver driver) {
        super(driver);
    }

    // ─── Book Store ────────────────────────────────────────

    @FindBy(xpath = "//span[.='Login']")
    WebElement login;

    // ─── Elements ──────────────────────────────────────────

    // xpath [.='Text Box'] — ищет элемент с точным текстом 'Text Box'
    @FindBy(xpath = "//span[.='Text Box']")
    WebElement textBox;

    @FindBy(xpath = "//span[.='Buttons']")
    WebElement buttons;

    // ─── Alerts, Frame & Windows ───────────────────────────

    @FindBy(xpath = "//span[.='Alerts']")
    WebElement alerts;

    @FindBy(xpath = "//span[.='Browser Windows']")
    WebElement browserWindows;

    @FindBy(xpath = "//span[.='Frames']")
    WebElement frames;

    // ─── Widgets ───────────────────────────────────────────

    @FindBy(xpath = "//span[.='Select Menu']")
    WebElement selectMenu;

    @FindBy(xpath = "//span[.='Menu']")
    WebElement menu;

    @FindBy(xpath = "//span[.='Slider']")
    WebElement slider;

    // ─── Методы навигации ─────────────────────────────────

    // Переход на страницу Login → возвращает LoginPage
    public LoginPage selectLogin() {
        clickWithJS(login);
        return new LoginPage(driver);
    }

    // Переход на страницу Text Box → возвращает JSExecutor
    // JSExecutor используется для демонстрации работы с JavaScript
    public JSExecutor selectTextBox() {
        clickWithJS(textBox);
        return new JSExecutor(driver);
    }

    // Переход на страницу Alerts → возвращает AlertsPage
    public AlertsPage selectAlert() {
        clickWithJS(alerts);
        return new AlertsPage(driver);
    }

    // Переход на страницу Browser Windows → возвращает WindowsPage
    public WindowsPage selectBrowserWindows() {
        clickWithJS(browserWindows);
        return new WindowsPage(driver);
    }

    // Переход на страницу Frames → возвращает FramesPage
    public FramesPage selectFrame() {
        clickWithJS(frames);
        return new FramesPage(driver);
    }

    // Переход на страницу Select Menu → возвращает SelectPage
    public SelectPage selectSelect() {
        clickWithJS(selectMenu);
        return new SelectPage(driver);
    }

    // Переход на страницу Menu → возвращает MenuPage
    public MenuPage getMenu() {
        clickWithJS(menu);
        return new MenuPage(driver);
    }

    // Переход на страницу Slider → возвращает SliderPage
    public SliderPage getSlider() {
        clickWithJS(slider);
        return new SliderPage(driver);
    }

    // Переход на страницу Buttons → возвращает ButtonsPage
    public ButtonsPage getButtons() {
        click(buttons);
        return new ButtonsPage(driver);
    }
}
