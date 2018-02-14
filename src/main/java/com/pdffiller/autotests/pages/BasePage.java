package com.pdffiller.autotests.pages;

import com.pdffiller.autotests.utils.DriverUtils;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    protected DriverUtils driverUtils;

    public BasePage(DriverUtils driverUtils) {
        this.driverUtils = driverUtils;
        PageFactory.initElements(driverUtils.getDriver(), this);
    }
}
