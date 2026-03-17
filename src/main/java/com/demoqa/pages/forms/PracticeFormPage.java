package com.demoqa.pages.forms;

import com.demoqa.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Object для страницы "Practice Form" (раздел Forms на DemoQA).
 *
 * Форма регистрации: имя, email, пол, телефон, дата рождения,
 * предметы, хобби, фото, адрес.
 *
 * Все методы возвращают this для fluent-цепочки вызовов:
 *   new PracticeFormPages(driver)
 *       .fillForm(...)
 *       .selectGenderMale("Male")
 *       .clickSubmit()
 *       .verifyFormSubmitted();
 */
public class PracticeFormPage extends BasePage {

    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    // ============================================================
    // LOCATORS — текстовые поля
    // ============================================================

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "userEmail")
    private WebElement email;

    @FindBy(id = "userNumber")
    private WebElement phoneNumber;

    @FindBy(id = "currentAddress")
    private WebElement currentAddress;

    // ============================================================
    // LOCATORS — выбор пола (radio скрыт стилями → клик по label)
    // ============================================================

    /** Male   = gender-radio-1 */
    @FindBy(css = "label[for='gender-radio-1']")
    private WebElement genderMale;

    /** Female = gender-radio-2 */
    @FindBy(css = "label[for='gender-radio-2']")
    private WebElement genderFemale;

    /** Other  = gender-radio-3 */
    @FindBy(xpath = "//label[@for='gender-radio-3']")
    private WebElement genderOther;

    // ============================================================
    // LOCATORS — React DatePicker
    // ============================================================

    /** Поле ввода даты — клик открывает календарь */
    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirth;

    /** Выпадающий список месяца внутри открытого календаря */
    @FindBy(css = ".react-datepicker__month-select")
    private WebElement monthSelect;

    /** Выпадающий список года внутри открытого календаря */
    @FindBy(css = ".react-datepicker__year-select")
    private WebElement yearSelect;

    // ============================================================
    // LOCATORS — предметы (Subjects)
    // ============================================================

    /** Autocomplete-поле: вводим текст + Enter для добавления тега */
    @FindBy(id = "subjectsInput")
    private WebElement subjectsInput;

    // ============================================================
    // LOCATORS — хобби (checkbox скрыт стилями → клик по label)
    // ============================================================

    /** Sports  = hobbies-checkbox-1 */
    @FindBy(css = "label[for='hobbies-checkbox-1']")
    private WebElement hobbySports;

    /** Reading = hobbies-checkbox-2 */
    @FindBy(css = "label[for='hobbies-checkbox-2']")
    private WebElement reading;

    /** Music   = hobbies-checkbox-3 */
    @FindBy(css = "label[for='hobbies-checkbox-3']")
    private WebElement music;

    // ============================================================
    // LOCATORS — загрузка файла и отправка формы
    // ============================================================

    /**
     * input[type='file'] — НЕ нужно кликать по кнопке.
     * Selenium передаёт путь напрямую через sendKeys(), минуя системный диалог.
     */
    @FindBy(id = "uploadPicture")
    private WebElement uploadPicture;

    @FindBy(id = "submit")
    private WebElement submit;

    // ============================================================
    // LOCATORS — модальное окно с результатами
    // ============================================================

    /** Заголовок модалки, появляющейся после успешного Submit */
    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement modalTitle;

    // ============================================================
    // ACTIONS
    // ============================================================

    /**
     * Заполняет основные текстовые поля формы.
     */
    public PracticeFormPage fillForm(String first, String last, String emailVal,
                                     String phone, String address) {
        type(firstName, first);
        type(lastName, last);
        type(email, emailVal);
        type(phoneNumber, phone);
        type(currentAddress, address);
        return this;
    }

    /**
     * Выбирает пол через клик по label (radio-кнопки скрыты стилями).
     *
     * @param gender "Male" | "Female" | любое другое значение → Other
     */
    public PracticeFormPage selectGender(String gender) {
        if (gender.equals("Male")) {
            click(genderMale);
        } else if (gender.equals("Female")) {
            click(genderFemale);
        } else {
            click(genderOther);
        }
        return this;
    }

    /**
     * Выбирает дату рождения через React DatePicker:
     * 1. Клик по полю  → открывается календарь
     * 2. Select месяца → по видимому тексту ("January"…"December")
     * 3. Select года   → по видимому тексту ("1900"…"2100")
     * 4. Клик по дню   → XPath исключает дни соседних месяцев (outside-month)
     */
    public PracticeFormPage selectDateOfBirth(String month, String year, String day) {
        click(dateOfBirth);
        new Select(monthSelect).selectByVisibleText(month);
        new Select(yearSelect).selectByVisibleText(year);
        driver.findElement(By.xpath(
                "//div[contains(@class,'react-datepicker__day')" +
                        " and not(contains(@class,'outside-month'))" +
                        " and text()='" + day + "']"
        )).click();
        return this;
    }

    // Альтернатива: ввод даты напрямую в поле (быстрее, но зависит от формата/браузера)
    //
    // @FindBy(css = "#dateOfBirthInput")
    // private WebElement dateOfBirthInput;
    //
    // public PracticeFormPages typeDateOfBirth(String date) {
    //     click(dateOfBirthInput);
    //     String os = System.getProperty("os.name");
    //     if (os.startsWith("Windows")) {
    //         dateOfBirthInput.sendKeys(Keys.CONTROL, "a");
    //     } else {
    //         dateOfBirthInput.sendKeys(Keys.COMMAND, "a");
    //     }
    //     dateOfBirthInput.sendKeys(date);
    //     dateOfBirthInput.sendKeys(Keys.ENTER);
    //     return this;
    // }

    /**
     * Добавляет предметы в autocomplete-поле.
     * Для каждого предмета вводит текст и нажимает Enter для подтверждения тега.
     *
     * @param subjects массив предметов, например: {"Maths", "Physics"}
     */
    public PracticeFormPage addSubject(String[] subjects) {
        if (subjects == null) return this;
        for (String subject : subjects) {
            type(subjectsInput, subject);
            subjectsInput.sendKeys(Keys.ENTER);
        }
        return this;
    }

    /**
     * Выбирает хобби "Sports" (клик по label, checkbox скрыт стилями).
     */
    public PracticeFormPage selectHobbySports() {
        click(hobbySports);
        return this;
    }

    // Альтернатива: выбор нескольких хобби через массив
    //
    // public PracticeFormPages selectHobby(String[] hobbies) {
    //     for (String hobby : hobbies) {
    //         if (hobby.equalsIgnoreCase("Sports"))  click(hobbySports);
    //         if (hobby.equalsIgnoreCase("Reading")) click(reading);
    //         if (hobby.equalsIgnoreCase("Music"))   click(music);
    //     }
    //     return this;
    // }

    /**
     * Загружает файл через input[type='file'].
     * Selenium не открывает системный диалог — путь передаётся напрямую через sendKeys().
     *
     * @param absoluteFilePath абсолютный путь к файлу, например:
     *                         "C:\\Users\\user\\Pictures\\photo.jpg"
     */
    public PracticeFormPage uploadPicture(String absoluteFilePath) {
        uploadPicture.sendKeys(absoluteFilePath);
        return this;
    }

    /**
     * Нажимает кнопку Submit через JavaScript (обходит возможное перекрытие баннерами DemoQA).
     * После клика появляется модальное окно с введёнными данными.
     */
    public PracticeFormPage clickSubmit() {
        clickWithJS(submit);
        return this;
    }

    // ============================================================
    // ASSERTIONS
    // ============================================================

    /**
     * Проверяет, что модальное окно с результатами появилось после Submit.
     */
    public PracticeFormPage verifyFormSubmitted() {
        assertTrue(modalTitle.isDisplayed(), "Модальное окно с результатами не появилось");
        return this;
    }
}