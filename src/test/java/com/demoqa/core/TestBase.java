package com.demoqa.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

// Базовый класс для всех тестов
// Все тестовые классы наследуют TestBase и получают готовый driver
public class TestBase {

    protected WebDriver driver;

    // Запускается ПЕРЕД каждым тестом
    // Открывает браузер, разворачивает на весь экран, переходит на сайт
    @BeforeEach
    public void init() {
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Запускается ПОСЛЕ каждого теста
    // Закрывает браузер и освобождает ресурсы
    // Проверка на null — защита если init() не выполнился
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
