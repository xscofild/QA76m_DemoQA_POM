package Homeworks.Lesson29.tests;

import Homeworks.Lesson29.core.TestBase;
import Homeworks.Lesson29.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UploadTest extends TestBase {

    // Тест сценарий:
    //  1. Переходим на страницу File Upload
    //  2. Передаём путь к файлу в input[type='file'] через sendKeys()
    //  3. Нажимаем кнопку Upload
    //  4. Проверяем что появился заголовок "File Uploaded!"
    @Test
    public void uploadFileTest() {
        boolean isUploaded = new HomePage(driver)
                .getUploadPage()
                .uploadFile("C:/Tools/QA.jpg")
                .clickUpload()
                .isFileUploaded();

        Assertions.assertTrue(isUploaded, "Файл должен успешно загрузиться");
    }
}