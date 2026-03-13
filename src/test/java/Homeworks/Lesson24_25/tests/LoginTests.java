package Homeworks.Lesson24_25.tests;

import Homeworks.Lesson24_25.core.TestBase;
import Homeworks.Lesson24_25.pages.HomePage;
import Homeworks.Lesson24_25.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    @BeforeEach
    public void precondition() {
        new HomePage(driver).goToLoginPage();
    }

    @Test
    @DisplayName("Successful login shows secure area message")
    public void loginPositiveTest() {
        new LoginPage(driver)
                .enterCredentials("tomsmith", "SuperSecretPassword!")
                .clickLogin()
                .verifyLoginSuccess();
    }

    @Test
    @DisplayName("Wrong password shows error message")
    public void loginNegativeTest() {
        new LoginPage(driver)
                .enterCredentials("tomsmith", "wrongpassword")
                .clickLogin()
                .verifyInvalidPasswordMessage();
    }
}