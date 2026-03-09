package com.demoqa.pages.bookStore;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * LoginPage — страница авторизации.
 * Содержит поля для ввода логина/пароля и кнопку Login.
 *
 * Методы возвращают Page Object — это позволяет писать тест цепочкой вызовов
 * (паттерн Method Chaining / Fluent Interface).
 */
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "userName")
    WebElement userNameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    // Вводит имя и пароль. Возвращает this — чтобы продолжить цепочку вызовов
    public LoginPage enterUserData(String name, String password) {
        typeWithJS(userNameInput, name, 0, 200);
        typeWithJS(passwordInput, password, 0, 200);
        return this;
    }

    @FindBy(id = "login")
    WebElement loginButton;

    // Кликает по кнопке Login → после логина открывается ProfilePage
    public ProfilePage clickOnLoginButton() {
        clickWithJS(loginButton, 0, 500);
        return new ProfilePage(driver);
    }
}