package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.JSExecutor;
import com.demoqa.pages.SidePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JSElementsTests — демонстрация возможностей JavascriptExecutor.
 *
 * Один тест покрывает всю цепочку: заполнение формы → сабмит → получение данных страницы
 * → обновление → навигация на другую страницу → получение title.
 *
 * Это скорее демонстрационный тест (show-case), а не изолированный unit-тест.
 * В продакшн-тестировании лучше разбивать на отдельные тесты с чёткими assertions.
 */
public class JSElementsTests extends TestBase {

    SidePanel sidePanel;

    @BeforeEach
    public void precondition() {
        new HomePage(driver).selectElements();
        sidePanel = new SidePanel(driver);
    }

    @Test
    public void jsExecutorTest() {
        sidePanel.selectTextBox();  // переходим на Text Box страницу

        new JSExecutor(driver)
                .enterPersonalData("Serdar Kerimov", "Test@gmail.com")  // JS заполнение полей
                .clickOnSubmitButton()          // JS клик + изменение стиля кнопки
                .getInnerText()                 // выводит весь текст страницы в консоль
                .verifyURL()                    // выводит текущий URL в консоль
                .refreshPage()                  // history.go(0) → перезагрузка
                .navigateToNewTab("https://telranedu.web.app/")  // навигация в той же вкладке (название метода misleading!)
                .verifyNewPageFaveIconTitle()   // выводит <title> новой страницы в консоль
        ;
    }
}