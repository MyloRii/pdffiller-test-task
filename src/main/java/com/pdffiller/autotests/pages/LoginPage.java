package com.pdffiller.autotests.pages;

import com.pdffiller.autotests.utils.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

    private final String startPatternForRegisterFormXpath = "//input[@value='register']/" +
            "../form[@id='login-register']/div[@class='login-block-form-fields']";

    @FindBy(how = How.XPATH, using = "//button[@id='button-register']")
    private WebElement btnRegister;

    @FindBy(how = How.XPATH, using = startPatternForRegisterFormXpath + "/div/following-sibling::div/input[@type='email']")
    private WebElement inputEmail;

    @FindBy(how = How.XPATH, using = startPatternForRegisterFormXpath + "/div/following-sibling::div/input[@type='password']")
    private WebElement inputPassword;

    @FindBy(how = How.XPATH, using = startPatternForRegisterFormXpath + "/button[@type='submit']")
    private WebElement btnRegisterSubmit;


    public LoginPage(DriverUtils driverUtils) {
        super(driverUtils);
    }

    public void registerNewUser(String email, String password) {
        driverUtils.waitForVisibilityOfElement(btnRegister);
        driverUtils.clickButton(btnRegister);
        driverUtils.sendKeys(inputEmail, email);
        driverUtils.sendKeys(inputPassword, password);
        driverUtils.clickButton(btnRegisterSubmit);
    }
}
