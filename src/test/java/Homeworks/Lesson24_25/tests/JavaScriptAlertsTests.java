package Homeworks.Lesson24_25.tests;

import Homeworks.Lesson24_25.core.TestBase;
import Homeworks.Lesson24_25.pages.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JavaScriptAlertsTests extends TestBase {

    @Test
    @DisplayName("Verify result text after accepting JS Alert")
    public void jsAlertTest() {
        new HomePage(driver)
                .goToJavaScriptAlertsPage()
                .clickJsAlertButton()
                .acceptAlert()
                .verifyResultText("You successfully clicked an alert");
    }

    @Test
    @DisplayName("Verify result text after accepting JS Confirm")
    public void jsConfirmOkTest() {
        new HomePage(driver)
                .goToJavaScriptAlertsPage()
                .clickJsConfirmButton()
                .acceptAlert()
                .verifyResultText("You clicked: Ok");
    }

    @Test
    @DisplayName("Verify result text after dismissing JS Confirm")
    public void jsConfirmCancelTest() {
        new HomePage(driver)
                .goToJavaScriptAlertsPage()
                .clickJsConfirmButton()
                .dismissAlert()
                .verifyResultText("You clicked: Cancel");
    }

    @Test
    @DisplayName("Verify entered text after accepting JS Prompt")
    public void jsPromptTest() {
        new HomePage(driver)
                .goToJavaScriptAlertsPage()
                .clickJsPromptButton()
                .sendTextToPromptAndAccept("hello1")
                .verifyResultText("You entered: hello1");
    }
}