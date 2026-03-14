package Homeworks.Lesson26_27.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

/**
 * TestBase — родитель для всех тестов HW-проекта.
 *
 * Сайт: https://the-internet.herokuapp.com/ — учебный сайт с готовыми виджетами.
 * Каждый тест стартует с главной страницы → HomePage кликает на нужный раздел.
 *
 * implicitlyWait(5) — 5 сек ожидание элементов (меньше чем в demoqa, сайт простой и быстрый).
 */
public class TestBase {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://the-internet.herokuapp.com/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}