package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import com.demoqa.pages.alertsFrameWindows.FramesPage;
import com.demoqa.pages.alertsFrameWindows.WindowsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * AlertsFrameWindowsTests — тесты для раздела Alerts, Frame & Windows.
 *
 * Архитектурный момент: объекты страниц создаются ДВАЖДЫ:
 *   1. В preconditions() через new HomePage(driver).selectAlertsFrameWindows() → возвращает SidePanel
 *      (но этот SidePanel не сохраняется — результат selectAlertsFrameWindows() игнорируется!)
 *   2. Отдельно: sidePanel = new SidePanel(driver), alerts = new AlertsPage(driver) и т.д.
 *
 * Это избыточно но работает — PageFactory инициализирует @FindBy при создании объекта,
 * и все объекты работают с одним и тем же driver (одна и та же вкладка браузера).
 * Можно было сохранить результат selectAlertsFrameWindows() как sidePanel и не создавать заново.
 */
public class AlertsFrameWindowsTests extends TestBase {

    SidePanel sidePanel;
    AlertsPage alerts;
    FramesPage frame;
    WindowsPage windows;

    @BeforeEach
    public void preconditions() {
        new HomePage(driver).selectAlertsFrameWindows(); // кликает по карточке раздела → открывает SidePanel
        sidePanel = new SidePanel(driver);
        alerts = new AlertsPage(driver);
        frame = new FramesPage(driver);
        windows = new WindowsPage(driver);
    }

    @Test
    public void waitAlertTest() {
        sidePanel.selectAlerts();
        alerts.verifyAlertWithTimer();  // ждёт алерт с таймером (~5 сек)
    }

    @Test
    @DisplayName("Verify to -> 'Ok is displayed'")
    public void alertWithSelectResultTest() {
        sidePanel.selectAlerts();
        alerts.clickOnConfirmButton()
                .selectResult("Ok")    // принимает confirm-алерт
                .verifyResult("Ok");   // проверяет текст результата на странице
    }

    @Test
    @DisplayName("Verify to -> 'Text you entered is displayed'")
    public void sendMessageToAlertTest() {
        sidePanel.selectAlerts();
        alerts.clickOnPromptButton()
                .sendMessageToAlert("Hello world !")  // вводит текст в prompt и принимает
                .verifyMessage("Hello world !");       // проверяет что текст появился на странице
    }

    @Test
    public void switchToNewTabTest() {
        sidePanel.selectWindows();
        windows.clickOnNewTabButton()
                .switchToNewTab(1)                          // переключается на новую вкладку (index=1)
                .verifyNewTabTitle("This is a sample page");
    }

    @Test
    public void switchToNewIframeByIndex() {
        sidePanel.selectFrame();
        frame.returnListOfIframes()
                .switchToIframeByIndex(1)                   // переключается во второй iframe (index=1)
                .verifyIframeByTitle("This is a sample page");
    }

    @Test
    public void switchToIframeByIdTest() {
        sidePanel.selectFrame();
        frame.switchToIframeById()                          // в iframe
                .verifyIframeByTitle("This is a sample page")
                .switchToMainPage()                         // назад в основной документ
                .verifyMainPageTitle("Frames");             // проверяем элемент основного документа
    }

    // @Disabled — тест полностью пропускается при запуске. handleNestedFrames() пустой метод-заглушка.
    @Disabled("Nested frames test is temporarily disabled")
    @Test
    public void nestedIframesTest() {
        sidePanel.selectNestedFrames();
        frame.handleNestedFrames();
    }
}