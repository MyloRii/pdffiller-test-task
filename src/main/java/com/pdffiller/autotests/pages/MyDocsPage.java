package com.pdffiller.autotests.pages;

import com.pdffiller.autotests.utils.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MyDocsPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'PDFfiller How To Guide')]")
    private WebElement howToGuideDoc;


    public MyDocsPage(DriverUtils driverUtils) {
        super(driverUtils);
    }

    public void openHowToGuideDoc() {
        driverUtils.doubleClick(howToGuideDoc);
        driverUtils.waitUntilDocumentIsLoaded();
    }
}
