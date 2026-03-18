package com.demoqa.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.time.Duration;

// Базовый класс для всех тестов
// Все тестовые классы наследуют TestBase и получают готовый driver
public class TestBase {

    protected WebDriver driver;

    // Флаг для управления закрытием браузера после теста
    // true  — браузер закрывается (по умолчанию)
    // false — браузер остаётся открытым (удобно для отладки)
    protected boolean closeBrowser = true;

    // Запускается ОДИН РАЗ перед всеми тестами
    // Перехватывает Java Util Logging → SLF4J/Logback
    // Убирает CDP version WARNING от Selenium
    @BeforeAll
    static void setupLogging() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

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
    // Закрывает браузер если closeBrowser == true
    // Проверка на null — защита если init() не выполнился
    @AfterEach
    public void tearDown() {
        if (closeBrowser && driver != null) {
            driver.quit();
        }
    }
}