package Homeworks.Lesson26_27.tests;

import Homeworks.Lesson26_27.core.TestBase;
import Homeworks.Lesson26_27.pages.HomePage;
import org.junit.jupiter.api.Test;

public class DragAndDropTests extends TestBase {

    @Test
    public void dragAndDropTest() {
        new HomePage(driver)
                .goToDragAndDropPage()
                .dragAtoB()
                .verifyColumnsSwapped();
    }
}