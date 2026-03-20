package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.forms.PracticeFormPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тесты для страницы "Practice Form" (раздел Forms на DemoQA).
 *
 * @BeforeEach: навигация HomePage → Forms → Practice Form
 * @Test: fluent-цепочка действий + финальная проверка
 */
public class PracticeFormTests extends TestBase {

    // ============================================================
    // SETUP
    // ============================================================

    @BeforeEach
    public void precondition() {
        new HomePage(driver).goToForms();
        new SidePanel(driver).getPracticeForm();
    }

    // ============================================================
    // TESTS
    // ============================================================

    @Test
    public void createAccountPositiveTest() {
        new PracticeFormPage(driver)
                .fillForm("John", "Smith", "john.smith@mail.com", "1234567890", "Baker Street 221b")
                .selectGender("Male")
                //.selectDateOfBirth("March", "2000", "15")
                .typeDateOfBirth("22 Feb 1996")         // альтернатива: ввод даты текстом
                .addSubject(new String[]{"Maths", "Physics"})
                .selectHobbySports()
                // .selectHobby(new String[]{"Sports", "Music"})  // альтернатива: массив хобби
                .uploadPicture("C:/Tools/QA.jpg")
                .enterState("NCR")
                .enterCity("Delhi")
                .clickSubmit()
                .verifyFormSubmitted("Thanks for submitting the form");
    }

    @Test
    public void createAccountNegativeWithInvalidPhoneTest() {
        new PracticeFormPage(driver)
                .fillForm("John", "Smith", "john.smith@mail.com", "1234", "Baker Street 221b")
                .selectGender("Male")
                .selectDateOfBirth("March", "2000", "15")
                // .typeDateOfBirth("22 Feb 1996")         // альтернатива: ввод даты текстом
                .addSubject(new String[]{"Maths", "Physics"})
                .selectHobbySports()
                // .selectHobby(new String[]{"Sports", "Music"})  // альтернатива: массив хобби
                .uploadPicture("C:/Tools/QA.jpg")
                .enterState("NCR")
                .enterCity("Delhi")
                .clickSubmit()
                .verifyFromTitle();
    }
}