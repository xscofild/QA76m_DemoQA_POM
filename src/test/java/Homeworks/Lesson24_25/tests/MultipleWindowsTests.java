package Homeworks.Lesson24_25.tests;

import Homeworks.Lesson24_25.core.TestBase;
import Homeworks.Lesson24_25.pages.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MultipleWindowsTests extends TestBase {

    @Test
    @DisplayName("Verify heading text in new window")
    public void newWindowHeadingTest() {
        new HomePage(driver)
                .goToMultipleWindowsPage()
                .clickClickHereLink()
                .switchToNewWindow(1)
                .verifyHeadingText("New Window");
    }
}