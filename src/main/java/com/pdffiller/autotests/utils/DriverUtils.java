package com.pdffiller.autotests.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

final public class DriverUtils {

    private final long DEFAULT_WAIT = 30L; // Waiting time in seconds
    private final By LOCATOR_FOR_CIRCLE_LOADING = By.xpath("//div[@class='g-loader']");
    private final By LOCATOR_FOR_DOCUMENT_LOADING = By.xpath("//div[@class='document-loader']");
    private WebDriver driver;

    public DriverUtils(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void closeBrowser() {
        driver.quit();
    }

    public void waitUntilPageIsLoaded() {
        waitForAbsenceOfElement(LOCATOR_FOR_CIRCLE_LOADING, DEFAULT_WAIT);
    }

    public void waitUntilDocumentIsLoaded() {
        waitForAbsenceOfElement(LOCATOR_FOR_DOCUMENT_LOADING, DEFAULT_WAIT);
    }

    public void clickButton(WebElement button) {
        if (button.isDisplayed() && button.isEnabled()) {
            button.click();
        }
        waitUntilPageIsLoaded();
    }

    public void sendKeys(WebElement element, String text) {
        if (element.isDisplayed() && element.isEnabled()) {
            element.sendKeys(text);
        }
        waitUntilPageIsLoaded();
    }

    public void doubleClick(WebElement element) {
        if (element.isDisplayed() && element.isEnabled()) {
            Actions action = new Actions(getDriver());
            action.doubleClick(element).perform();
        }
        waitUntilPageIsLoaded();
    }

    public void waitForVisibilityOfElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForAbsenceOfElement(By locator, long timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}

