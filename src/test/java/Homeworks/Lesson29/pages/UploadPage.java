package Homeworks.Lesson29.pages;

import Homeworks.Lesson29.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UploadPage extends BasePage {

    public UploadPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "file-upload")
    private WebElement fileInput;

    @FindBy(id = "file-submit")
    private WebElement uploadButton;

    @FindBy(tagName = "h3")
    private WebElement uploadedTitle;

    public UploadPage uploadFile(String absoluteFilePath) {
        fileInput.sendKeys(absoluteFilePath);
        return this;
    }

    public UploadPage clickUpload() {
        click(uploadButton);
        return this;
    }

    public boolean isFileUploaded() {
        return uploadedTitle.getText().contains("File Uploaded!");
    }
}