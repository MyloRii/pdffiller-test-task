package com.pdffiller.autotests.pages;

import com.pdffiller.autotests.utils.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage extends BasePage {

    private final String URL = "https://www.pdffiller.com";

    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Log in')]")
    private WebElement btnLogin;


    public MainPage(DriverUtils driverUtils) {
        super(driverUtils);
    }

    public void openPdffillerMainPage() {
        driverUtils.getDriver().get(URL);
        driverUtils.waitUntilPageIsLoaded();
    }

    public void clickLoginButton() {
        driverUtils.clickButton(btnLogin);
    }
}
