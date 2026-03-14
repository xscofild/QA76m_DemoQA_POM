package com.demoqa.pages.bookStore;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * LoginPage — форма входа Book Store.
 *
 * Обрати внимание на clickOnLoginButton() — он возвращает ProfilePage, а не this (LoginPage).
 * Это "Page Transition": метод моделирует переход на следующую страницу после действия.
 * Если после логина сайт будет редиректить на другую страницу — тип возврата нужно менять здесь.
 */
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "userName")
    WebElement userNameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    public LoginPage enterUserData(String name, String password) {
        typeWithJS(userNameInput, name);
        typeWithJS(passwordInput, password);
        return this;
    }

    @FindBy(id = "login")
    WebElement loginButton;

    // Клик → переход на ProfilePage. Возвращает новый объект ProfilePage — дальше цепочка продолжается там.
    public ProfilePage clickOnLoginButton() {
        clickWithJS(loginButton);
        return new ProfilePage(driver);
    }
}