package Homeworks.Lesson26_27.pages;

import Homeworks.Lesson26_27.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Dropdown")
    private WebElement dropdownLink;

    @FindBy(linkText = "Drag and Drop")
    private WebElement dragAndDropLink;

    @FindBy(linkText = "Horizontal Slider")
    private WebElement sliderLink;

    @FindBy(linkText = "Hovers")
    private WebElement hoversLink;

    public DropdownPage goToDropdownPage() {
        click(dropdownLink);
        return new DropdownPage(driver);
    }

    public DragAndDropPage goToDragAndDropPage() {
        click(dragAndDropLink);
        return new DragAndDropPage(driver);
    }

    public SliderPage goToSliderPage() {
        click(sliderLink);
        return new SliderPage(driver);
    }

    public HoversPage goToHoversPage() {
        click(hoversLink);
        return new HoversPage(driver);
    }
}