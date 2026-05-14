# 🖥️ QA76m_DemoQA_POM

UI automation framework for **demoqa.com** built with **Selenium WebDriver**, **JUnit 5**, and **Page Object Model**.

> Demonstrates fluent-style POM, `PageFactory` with `@FindBy`, and reusable page navigation across multiple form types.

---

## 🎯 What's Covered

| Test Suite | Pages Tested |
|---|---|
| **Forms** | Practice Form (text, radio, checkbox, date picker, file upload, dropdown) |
| **Elements** | Text Box, Check Box, Radio Button, Buttons, Links |
| **Alerts/Frames/Windows** | JS Alerts, Frames, New Windows/Tabs |
| **Widgets** | Date Picker, Slider, Progress Bar, Tabs, Tool Tips |
| **JS Elements** | Dynamic properties, hidden elements |
| **Book Store** | Login, search, profile |

---

## 🧰 Tech Stack

- **Java 17**
- **Selenium WebDriver 4.41.0**
- **JUnit 5 (Jupiter)**
- **AssertJ 3.27.7** — fluent assertions
- **Logback + SLF4J** — clean console output (no Selenium CDP warnings)
- **Maven** — build & dependency management

---

## 🏗️ Project Structure

```
src/test/java/com/demoqa/
├── core/
│   ├── BasePage.java           # Shared driver, PageFactory init, common methods
│   └── TestBase.java           # Driver lifecycle (@BeforeEach, @AfterEach)
├── pages/
│   ├── HomePage.java
│   ├── SidePanel.java          # Reusable sub-menu navigation
│   ├── forms/
│   │   └── PracticeFormPage.java
│   ├── elements/
│   ├── widgets/
│   ├── alerts/
│   └── bookstore/
└── tests/
    ├── PracticeFormTests.java
    ├── ElementsTests.java
    ├── WidgetsTests.java
    ├── AlertsFrameWindowsTests.java
    ├── BookStoreTests.java
    └── JSElementsTests.java
```

---

## 🔑 Key Patterns

### Fluent Page Object Model
Every page method returns `this` (or the next page), enabling readable test chains:

```java
new PracticeFormPage(driver)
    .fillForm("John", "Smith", "john@mail.com", "1234567890", "Baker St 221b")
    .selectGender("Male")
    .typeDateOfBirth("22 Feb 1996")
    .addSubject(new String[]{"Maths", "Physics"})
    .selectHobbySports()
    .uploadPicture("path/to/file.jpg")
    .enterState("NCR")
    .enterCity("Delhi")
    .clickSubmit()
    .verifyFormSubmitted("Thanks for submitting the form");
```

### PageFactory + @FindBy
Locators declared as fields, initialized via `PageFactory.initElements()` in `BasePage` constructor.

### Test isolation
`@BeforeEach` opens a fresh `ChromeDriver`, `@AfterEach` quits — no state leaks between tests.

### Debugging toggle
`protected boolean closeBrowser = true;` — set to `false` in a test class to keep the browser open after run.

---

## 🚀 How to Run

### Prerequisites
- Java 17+
- Maven 3.6+
- Chrome browser

### Run all tests
```bash
mvn test
```

### Run a specific test class
```bash
mvn test -Dtest=PracticeFormTests
```

### Run a single test method
```bash
mvn test -Dtest=PracticeFormTests#createAccountPositiveTest
```

---

## 📝 Notes

- Tests run against **demoqa.com** — a public QA training site.
- File upload tests require a local image file path — adjust `uploadPicture(...)` in tests to point to a local file.
- Implicit wait set to 10 seconds; for unstable elements, explicit waits should be added at the page level.

---

## 🎓 Author

**Serdar Kerimov** — [github.com/xscofild](https://github.com/xscofild) · [LinkedIn](https://www.linkedin.com/in/serdarkerimov/)
QA Engineer | Java · Selenium · REST Assured · SQL
