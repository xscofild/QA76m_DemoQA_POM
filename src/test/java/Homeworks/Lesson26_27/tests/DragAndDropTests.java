package Homeworks.Lesson26_27.tests;

import Homeworks.Lesson26_27.core.TestBase;
import Homeworks.Lesson26_27.pages.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * DragAndDropTests — тест для the-internet.herokuapp.com/drag_and_drop.
 *
 * После dragAtoB() колонки меняются местами:
 * element с id="column-a" теперь показывает текст "B" → это и проверяем.
 *
 * Потенциальная нестабильность: HTML5 Drag & Drop плохо поддерживается через Actions.
 * Если тест нестабилен — решение: JS-диспатч событий drag/drop вручную.
 */
public class DragAndDropTests extends TestBase {

    @Test
    @DisplayName("Drag column A to column B and verify swap")
    public void dragAndDropTest() {
        new HomePage(driver)
                .goToDragAndDropPage()
                .dragAtoB()
                .verifyColumnAText("B");
    }
}