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

public class AlertsFrameWindowsTests extends TestBase {

    SidePanel sidePanel;
    AlertsPage alerts;
    FramesPage frame;
    WindowsPage windows;

    @BeforeEach
    public void preconditions() {
        new HomePage(driver).selectAlertsFrameWindows();
        sidePanel = new SidePanel(driver);
        alerts = new AlertsPage(driver);
        frame = new FramesPage(driver);
        windows = new WindowsPage(driver);
    }

    @Test
    public void waitAlertTest() {
        sidePanel.selectAlerts();
        alerts.verifyAlertWithtimer();
    }

    @Test
    @DisplayName("Verify to -> 'Ok is displayed'")
    public void alertWithSelectResultTest() {
        sidePanel.selectAlerts();
        alerts.clickOnConfirmButton()
                .selectResult("Ok")
                .verifyResult("Ok");
    }

    @Test
    @DisplayName("Verify to -> 'Text you entered is displayed'")
    public void sendMessageToAlertTest() {
        sidePanel.selectAlerts();
        alerts.clickOnPromptButton()
                .sendMessageToAlert("Hello world !")
                .verifyMessage("Hello world !");
    }

    @Test
    public void switchToNewTabTest() {
        sidePanel.selectWindows();
        windows.clickOnNewTabButton()
                .switchToNewTab(1)
                .verifyNewTabTitle("This is a sample page");
    }

    @Test
    public void switchToNewIframeByIndex() {
        sidePanel.selectFrame();
        frame.returnListOfIframes()
                .switchToIframeByIndex(1)
                .verifyIframeByTitle("This is a sample page");
    }

    @Test
    public void switchToIframeByIdTest() {
        sidePanel.selectFrame();
        frame.switchToIframeById()
                .verifyIframeByTitle("This is a sample page")
                .switchToMainPage()
                .verifyMainPageTitle("Frames");
    }

    @Disabled("Nested frames test is temporarily disabled")
    @Test
    public void nestedIframesTest() {
        sidePanel.selectNestedFrames();
        frame.handleNestedFrames();
    }
}