package com.demoqa.pages;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;

public class JSExecutor extends BasePage {
    public JSExecutor(WebDriver driver) {
        super(driver);
    }

    public JSExecutor enterPersonalData(String name, String email) {
        js.executeScript("document.getElementById('userName').value='" + name + "';");
        js.executeScript("document.getElementById('userEmail').value='" + email + "';");
        js.executeScript("document.getElementById('userEmail').style.border='2px solid red';");
        return this;
    }

    public JSExecutor clickOnSubmitButton() {
        js.executeScript("document.querySelector('#submit').click();");
        js.executeScript("document.querySelector('#submit').style.backgroundColor='red';");

        return this;
    }

    public JSExecutor getInnerText() {
        String inner = js.executeScript("return document.documentElement.innerText;").toString();
        System.out.println(inner);
        return this;
    }

    public JSExecutor verifyURL() {
        String url = js.executeScript("return document.URL").toString();
        System.out.println("URL: " + url);
        return this;
    }

    public JSExecutor refreshPage() {
        js.executeScript("history.go(0);");
        return this;
    }

    public JSExecutor navigateToNewTab(String url) {
        js.executeScript("window.location ='" + url +"';");
        return this;
    }

    public JSExecutor verifyNewPageFaveIconTitle() {
        String faveIconTitle = js.executeScript("return document.title;").toString();
        System.out.println("FaveIconTitle: " + faveIconTitle);
        return this;
    }
}
