package Homeworks.Lesson26_27.tests;

import Homeworks.Lesson26_27.core.TestBase;
import Homeworks.Lesson26_27.pages.IFramePage;
import org.junit.jupiter.api.Test;

public class IFrameTests extends TestBase {

    @Test
    public void typeTextInIFrameTest() {
        new IFramePage(driver)
                .open()
                .switchToEditor()
                .clearAndType("Hello QA!")
                .verifyText("Hello QA!");
    }
}