package com.demoqa.pages;

import com.demoqa.core.BasePage;
import com.demoqa.pages.bookStore.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * SidePanel — боковое меню раздела Book Store.
 * Содержит ссылки на подразделы: Login, Books, Profile и т.д.
 */
public class SidePanel extends BasePage {

    public SidePanel(WebDriver driver) {
        super(driver);
    }

    // XPath: находит элемент <span>, текст которого равен 'Login'
    @FindBy(xpath = "//span[.='Login']")
    WebElement login;

    // Кликает по пункту меню Login → возвращает LoginPage
    public LoginPage selectLogin() {
        clickWithJS(login, 0, 500);
        return new LoginPage(driver);
    }
}