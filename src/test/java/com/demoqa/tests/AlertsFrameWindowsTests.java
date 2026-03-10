package com.demoqa.tests;


import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlertsFrameWindowsTests extends TestBase {

    SidePanel sidePanel;
    AlertsPage alerts;

    @BeforeEach
    public void preconditions(){
        new HomePage(driver).selectAlertsFrameWindows();
        sidePanel = new SidePanel(driver);
        alerts = new AlertsPage(driver);
    }

    @Test
    public void waitAlertTest(){
        sidePanel.selectAlerts();
        alerts.verifyAlertWithtimer();
    }
}
