package com.demoqa.pages;

import com.demoqa.core.BasePage;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import com.demoqa.pages.alertsFrameWindows.FramesPage;
import com.demoqa.pages.alertsFrameWindows.WindowsPage;
import com.demoqa.pages.bookStore.LoginPage;
import com.demoqa.pages.elements.ButtonsPage;
import com.demoqa.pages.forms.PracticeFormPage;
import com.demoqa.pages.widgets.MenuPage;
import com.demoqa.pages.widgets.SelectPage;
import com.demoqa.pages.widgets.SliderPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Боковое меню навигации — появляется после входа в любой раздел с HomePage.
 *
 * Каждый метод кликает по пункту меню и возвращает соответствующий Page Object.
 * Это основа паттерна Page Object Model (POM): каждая страница = отдельный класс.
 *
 * XPath [.='Text'] ищет элемент с точным текстом — надёжнее, чем поиск по классу,
 * который может меняться при обновлении сайта.
 */
public class SidePanel extends BasePage {

    public SidePanel(WebDriver driver) {
        super(driver);
    }

    // ============================================================
    // LOCATORS — раздел Elements
    // ============================================================

    @FindBy(xpath = "//span[.='Text Box']")
    private WebElement textBox;

    @FindBy(xpath = "//span[.='Buttons']")
    private WebElement buttons;

    // ============================================================
    // LOCATORS — раздел Alerts, Frame & Windows
    // ============================================================

    @FindBy(xpath = "//span[.='Alerts']")
    private WebElement alerts;

    @FindBy(xpath = "//span[.='Browser Windows']")
    private WebElement browserWindows;

    @FindBy(xpath = "//span[.='Frames']")
    private WebElement frames;

    // ============================================================
    // LOCATORS — раздел Widgets
    // ============================================================

    @FindBy(xpath = "//span[.='Select Menu']")
    private WebElement selectMenu;

    @FindBy(xpath = "//span[.='Menu']")
    private WebElement menu;

    @FindBy(xpath = "//span[.='Slider']")
    private WebElement slider;

    // ============================================================
    // LOCATORS — раздел Book Store
    // ============================================================

    @FindBy(xpath = "//span[.='Login']")
    private WebElement login;

    // ============================================================
    // LOCATORS — раздел Forms
    // ============================================================

    @FindBy(xpath = "//span[text()='Practice Form']")
    private WebElement practiceForm;

    // ============================================================
    // METHODS — раздел Elements
    // ============================================================

    /**
     * Переход на Text Box.
     * Возвращает JSExecutor — используется для демонстрации работы с JavaScript.
     */
    public JSExecutor selectTextBox() {
        clickWithJS(textBox);
        return new JSExecutor(driver);
    }

    /** Переход на страницу Buttons. */
    public ButtonsPage getButtons() {
        click(buttons);
        return new ButtonsPage(driver);
    }

    // ============================================================
    // METHODS — раздел Alerts, Frame & Windows
    // ============================================================

    /** Переход на страницу Alerts. */
    public AlertsPage selectAlert() {
        clickWithJS(alerts);
        return new AlertsPage(driver);
    }

    /** Переход на страницу Browser Windows. */
    public WindowsPage selectBrowserWindows() {
        clickWithJS(browserWindows);
        return new WindowsPage(driver);
    }

    /** Переход на страницу Frames. */
    public FramesPage selectFrame() {
        clickWithJS(frames);
        return new FramesPage(driver);
    }

    // ============================================================
    // METHODS — раздел Widgets
    // ============================================================

    /** Переход на страницу Select Menu. */
    public SelectPage selectSelect() {
        clickWithJS(selectMenu);
        return new SelectPage(driver);
    }

    /** Переход на страницу Menu. */
    public MenuPage getMenu() {
        clickWithJS(menu);
        return new MenuPage(driver);
    }

    /** Переход на страницу Slider. */
    public SliderPage getSlider() {
        clickWithJS(slider);
        return new SliderPage(driver);
    }

    // ============================================================
    // METHODS — раздел Book Store
    // ============================================================

    /** Переход на страницу Login. */
    public LoginPage selectLogin() {
        clickWithJS(login);
        return new LoginPage(driver);
    }

    // ============================================================
    // METHODS — раздел Forms
    // ============================================================

    /** Переход на страницу Practice Form. */
    public PracticeFormPage getPracticeForm() {
        click(practiceForm);
        return new PracticeFormPage(driver);
    }
}