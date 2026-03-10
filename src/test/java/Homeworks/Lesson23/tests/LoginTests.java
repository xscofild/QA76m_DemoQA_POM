package Homeworks.Lesson23.tests;

import Homeworks.Lesson23.core.TestBase;
import Homeworks.Lesson23.pages.HomePage;
import Homeworks.Lesson23.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    @BeforeEach
    public void precondition() {
        new HomePage(driver).goToLoginPage();
    }

    @Test
    public void loginPositiveTest() {
        new LoginPage(driver)
                .enterCredentials("tomsmith", "SuperSecretPassword!")
                .clickLogin();
    }

    @Test
    public void loginNegativeTest() {
        new LoginPage(driver)
                .enterCredentials("tomsmith", "wrongpassword")
                .clickLogin();
    }
}