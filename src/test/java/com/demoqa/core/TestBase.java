package com.demoqa.core;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

/**
 * TestBase — базовый класс для всех тестов.
 * Отвечает за запуск браузера и открытие сайта перед каждым тестом.
 * Все тестовые классы наследуют этот класс.
 */
public class TestBase {

    protected WebDriver driver; // driver доступен во всех дочерних тестовых классах

    @BeforeEach
    public void init() {
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Ждём элементы до 10 секунд
    }
}