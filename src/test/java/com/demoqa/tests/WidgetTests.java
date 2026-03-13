package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.widgets.SelectPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WidgetTests extends TestBase {

    SidePanel sidePanel;
    SelectPage select;

    @BeforeEach
    public void precondition() {
        new HomePage(driver).selectWidgets();
        sidePanel = new SidePanel(driver);
        select = new SelectPage(driver);
    }

    @Test
    public void oldStyleSelectMenuTest() {
        sidePanel.selectSelect();
        select.oldStyleSelect("Yellow")
                .verifyColor("Yellow");
    }

    @Test
    public void multiSelectTest() {
        sidePanel.selectSelect();
        select.multiSelect(new String[]{"Green", "Red"})
                .verifyMultiSelect(new String[]{"Green", "Red"});
    }
}