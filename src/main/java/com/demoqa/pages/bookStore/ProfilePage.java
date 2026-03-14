package com.demoqa.pages.bookStore;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * ProfilePage — страница профиля после успешного логина.
 *
 * Минимальный Page Object: одна проверка — что имя пользователя отображается корректно.
 * shouldHaveText с таймаутом 5 сек нужен потому что страница профиля грузится после редиректа —
 * без ожидания тест может упасть, не дождавшись рендера элемента.
 */
public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "userName-value")
    WebElement userNameValue;

    public ProfilePage verifyUserName(String name) {
        Assertions.assertTrue(shouldHaveText(userNameValue, name, 5));
        return this;
    }
}