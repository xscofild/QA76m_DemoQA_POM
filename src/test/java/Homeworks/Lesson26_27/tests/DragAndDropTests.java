package Homeworks.Lesson26_27.tests;

import Homeworks.Lesson26_27.core.TestBase;
import Homeworks.Lesson26_27.pages.HomePage;
import org.junit.jupiter.api.Test;

public class DragAndDropTests extends TestBase {

    // Тест сценарий:
    //  1. Переходим на страницу Drag and Drop
    //  2. Перетаскиваем колонку A на место колонки B
    //  3. Проверяем что колонки поменялись местами (A→B, B→A)
    @Test
    public void dragAndDropTest() {
        new HomePage(driver)
                .goToDragAndDropPage()
                .dragAtoB()
                .verifyColumnsSwapped();
    }
}
