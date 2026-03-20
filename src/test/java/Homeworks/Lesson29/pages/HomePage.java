package Homeworks.Lesson29.pages;

import Homeworks.Lesson29.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()='File Upload']")
    private WebElement fileUploadLink;

    public UploadPage getUploadPage() {
        click(fileUploadLink);
        return new UploadPage(driver);
    }
}