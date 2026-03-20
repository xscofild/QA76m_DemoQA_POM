package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import com.demoqa.pages.alertsFrameWindows.FramesPage;
import com.demoqa.pages.alertsFrameWindows.WindowsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Тесты для раздела "Alerts, Frame & Windows"
// Покрывает: Timer Alert, Confirm Alert, Prompt Alert, Browser Windows, Frames
// @BeforeEach — переходим в раздел и создаём нужные Page Objects для всех тестов
public class AlertsFrameWindowsTests extends TestBase {

    SidePanel sidePanel;
    AlertsPage alerts;
    FramesPage frame;

    @BeforeEach
    public void precondition() {
        new HomePage(driver).goToAlertsFrameWindows();
        sidePanel = new SidePanel(driver);
        alerts = new AlertsPage(driver);
        frame = new FramesPage(driver);
    }

    // Тест: timer alert появляется через 5 секунд после клика
    // isAlertPresent(5) — ждёт alert до 5 секунд, принимает его и возвращает true
    @Test
    public void waitAlertTest() {
        sidePanel.selectAlert();
        alerts.verifyAlertWithTimer();
    }

    // Тест: confirm alert с выбором Cancel
    // Проверяем что текст результата содержит "Cancel"
    @Test
    @DisplayName("Verify to -> 'Cancel is displayed'")
    public void alertWithSelectResultTest() {
        sidePanel.selectAlert();
        alerts.clickOnConfirmButton()
                .selectResult("Cancel")
                .verifyResult("Cancel");
    }

    // Тест: prompt alert — вводим текст и проверяем что он отобразился
    // sendMessageToAlert() — вводит текст в поле alert и нажимает OK
    @Test
    @DisplayName("Verify to -> 'Text you entered is displayed'")
    public void sendMessageToAlertTest() {
        sidePanel.selectAlert();
        alerts.clickOnPromptButton()
                .sendMessageToAlert("Hello World!")
                .verifyMessage("Hello World!");
    }

    // Тест: открытие новой вкладки и проверка её заголовка
    // switchToNewTab(1) — переключаемся на вторую вкладку (index=1)
    @Test
    public void switchToNewTabTest() {
        sidePanel.selectBrowserWindows();
        new WindowsPage(driver).clickOnNewTabButton()
                .switchToNewTab(1)
                .verifyNewTabTitle("This is a sample page");
    }

    // Тест: переключение в iframe по индексу
    // switchToIframeByIndex(1) — переходим во второй iframe (index=1)
    @Test
    public void switchToNewIframeByIndex() {
        sidePanel.selectFrame();
        frame.returnListOfIframes()
                .switchToIframeByIndex(1)
                .verifyIframeByTitle("This is a sample page");
    }

    // Тест: переключение в iframe по id, проверка заголовка, возврат на основную страницу
    // switchToIframeById() — переходим в frame1 через WebElement
    // switchToMainPage() — возвращаемся в основной DOM через defaultContent()
    @Test
    public void switchToIframeByIdTest() {
        sidePanel.selectFrame();
        frame.switchToIframeById()
                .verifyIframeByTitle("This is a sample page")
                .switchToMainPage() // возврат в основной DOM, не на главную страницу сайта
                .verifyMainPageTitle("Frames");
    }
}