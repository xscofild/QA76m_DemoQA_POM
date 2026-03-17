package Homeworks.Lesson28.pages;

import Homeworks.Lesson28.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()='Context Menu']")
    WebElement contextMenuLink;

    @FindBy(xpath = "//h1[text()='Welcome to the-internet']")
    WebElement homeTitle;

    public ContextMenuPage getContextMenuPage() {
        click(contextMenuLink);
        return new ContextMenuPage(driver);
    }

    public boolean isHomePage() {
        return homeTitle.isDisplayed();
    }
}