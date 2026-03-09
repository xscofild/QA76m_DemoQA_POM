package com.demoqa.pages.bookStore;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * ProfilePage — страница профиля пользователя.
 * Открывается после успешного логина.
 */
public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    // Элемент, который отображает имя залогиненного пользователя
    @FindBy(id = "userName-value")
    WebElement userNameValue;

    // Проверяет, что на странице отображается нужное имя. Тест упадёт если имя не совпадёт
    public ProfilePage verifyUserName(String name) {
        Assertions.assertTrue(userNameValue.getText().contains(name));
        return this;
    }
}