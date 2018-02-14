package com.pdffiller.autotests.pages;

import com.pdffiller.autotests.utils.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DocumentPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//div[@class='btn-down']")
    private WebElement btnDoneDropDown;

    @FindBy(how = How.XPATH, using = "//div[@class='g-dropdown-popup-list']")
    private WebElement doneDropDown;

    @FindBy(how = How.XPATH, using = "//div[@class='g-dropdown-popup-list']/div[(contains(@class, 'save'))]")
    private WebElement btnSaveAs;

    @FindBy(how = How.XPATH, using = "//div[@class='help-dialog help-dialog--responsive']")
    private WebElement helpDialog;

    @FindBy(how = How.XPATH, using = "//button[@class='help-dialog-button help-dialog-button--close']")
    private WebElement btnCloseHelpDialog;


    public DocumentPage(DriverUtils driverUtils) {
        super(driverUtils);
    }

    public void clickSaveAsButton() {
        driverUtils.clickButton(btnDoneDropDown);
        driverUtils.waitForVisibilityOfElement(doneDropDown);
        driverUtils.clickButton(btnSaveAs);
    }

    public void handleHelpDialog() {
        driverUtils.waitForVisibilityOfElement(helpDialog);
        driverUtils.clickButton(btnCloseHelpDialog);
    }
}
