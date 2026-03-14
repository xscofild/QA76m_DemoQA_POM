package com.demoqa.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

/**
 * TestBase — общий родитель для ВСЕХ тестовых классов.
 *
 * Каждый тест-класс extends TestBase → автоматически получает:
 *   - свежий драйвер перед каждым тестом (@BeforeEach)
 *   - закрытие браузера после каждого теста (@AfterEach)
 *
 * Поле driver объявлено здесь и доступно во всех наследниках через protected.
 * Если убрать protected → дочерние классы потеряют доступ к driver.
 */
public class TestBase {

    protected WebDriver driver;  // доступен во всех наследниках

    @BeforeEach
    public void init() {
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // implicitlyWait(10): Selenium ждёт до 10 сек появления элемента перед выбросом NoSuchElementException.
        // Это глобальное ожидание — действует на все findElement() без явного указания времени.
        // Минус: конфликтует с явными ожиданиями (WebDriverWait) → могут суммироваться и замедлять тесты.
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // quit() закрывает ВСЕ вкладки + убивает процесс драйвера. close() — только текущую вкладку.
        }
    }
}