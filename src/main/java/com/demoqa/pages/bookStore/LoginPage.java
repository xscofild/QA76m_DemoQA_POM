package com.demoqa.pages.bookStore;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Страница логина в разделе Book Store Application
// После успешного логина страница меняется на ProfilePage —
// поэтому clickOnLoginButton() возвращает new ProfilePage(driver)
// Это пример цепочки Page Object переходов в POM паттерне
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Поле для ввода имени пользователя
    @FindBy(id = "userName")
    WebElement userNameInput;

    // Поле для ввода пароля
    @FindBy(id = "password")
    WebElement passwordInput;

    // Кнопка Login
    @FindBy(id = "login")
    WebElement loginButton;

    // Вводит имя пользователя и пароль в соответствующие поля
    public LoginPage enterUserData(String name, String password) {
        typeWithJS(userNameInput, name);
        typeWithJS(passwordInput, password);
        return this;
    }

    // Нажимает кнопку Login и возвращает ProfilePage
    // После логина открывается страница профиля — возвращаем новый Page Object
    public ProfilePage clickOnLoginButton() {
        clickWithJS(loginButton);
        return new ProfilePage(driver);
    }
}
