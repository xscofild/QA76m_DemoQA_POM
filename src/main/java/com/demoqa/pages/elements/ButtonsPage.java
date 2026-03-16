package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Страница "Buttons" в разделе Elements
// Демонстрирует три типа кликов через Selenium Actions:
//  1. Double Click — actions.doubleClick()  — двойной клик
//  2. Right Click  — contextmenu event через JS (надёжнее чем actions.contextClick())
//  3. Single Click — обычный click()
//
// Actions — класс Selenium для симуляции сложных взаимодействий мышью и клавиатурой
// .perform() — обязательный вызов в конце — выполняет накопленную цепочку действий
public class ButtonsPage extends BasePage {

    public ButtonsPage(WebDriver driver) {
        super(driver);
    }

    // Кнопка для двойного клика
    @FindBy(id = "doubleClickBtn")
    WebElement doubleClickBtn;

    // Сообщение которое появляется после двойного клика
    @FindBy(id = "doubleClickMessage")
    WebElement doubleClickMessage;

    // Кнопка для правого клика
    @FindBy(id = "rightClickBtn")
    WebElement rightClickBtn;

    // Сообщение которое появляется после правого клика
    @FindBy(id = "rightClickMessage")
    WebElement rightClickMessage;

    // ─── Двойной клик ─────────────────────────────────────

    // moveToElement() — сам прокручивает и перемещает мышь к кнопке
    // НЕ используем scrollToElement перед Actions — JS скролл сбивает координаты мыши
    // .doubleClick() — симулирует двойной клик
    // .perform()     — выполняет всю цепочку
    public ButtonsPage doubleClick() {
        actions.moveToElement(doubleClickBtn).doubleClick().perform();
        return this;
    }

    // Проверяет сообщение после двойного клика
    public ButtonsPage verifyDoubleClick(String text) {
        Assertions.assertTrue(isContainsText(text, doubleClickMessage));
        return this;
    }

    // ─── Правый клик ──────────────────────────────────────

    // Правый клик через JavaScript dispatchEvent
    // ПОЧЕМУ НЕ actions.contextClick():
    //   actions.contextClick() иногда открывает системное меню браузера
    //   вместо того чтобы вызвать событие на элементе страницы
    // РЕШЕНИЕ: диспатчим событие 'contextmenu' напрямую через JS —
    //   это гарантированно вызывает событие на элементе → появляется сообщение
    public ButtonsPage rightClick() {
        js.executeScript(
                "arguments[0].dispatchEvent(new MouseEvent('contextmenu', {bubbles: true}));",
                rightClickBtn
        );
        return this;
    }

    // Проверяет сообщение после правого клика
    public ButtonsPage verifyRightClick(String text) {
        Assertions.assertTrue(isContainsText(text, rightClickMessage));
        return this;
    }
}