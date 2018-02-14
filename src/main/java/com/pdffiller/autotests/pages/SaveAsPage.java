package com.pdffiller.autotests.pages;

import com.pdffiller.autotests.utils.DriverUtils;
import com.pdffiller.autotests.utils.FileUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class SaveAsPage extends BasePage {

    private final String PDF_EXTENSION = ".pdf";

    @FindBy(how = How.XPATH, using = "//button[@class='button-apply']")
    private WebElement btnSaveAs;

    @FindBy(how = How.XPATH, using = "//i[contains(@class, 'i-close')]")
    private WebElement btnCloseDocHasBeenSavedWindow;

    @FindBy(how = How.XPATH, using = "//button[@class='button-filter-pages button-filter-pages-unselect']")
    private WebElement btnUnselectAll;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Select all')]")
    private WebElement btnSelectAll;

    @FindBy(how = How.XPATH, using = "//div[@class='flexible-list__item']//button")
    private List<WebElement> listOfDocumentPages;

    @FindBy(how = How.XPATH, using = "//div[@class='doc-collab-list-item__name']")
    private WebElement documentName;

    private FileUtils fileUtils = new FileUtils();


    public SaveAsPage(DriverUtils driverUtils) {
        super(driverUtils);
    }

    public void saveFileWithAllPagesAndCloseDocHasBeenSavedWindow() {
        clickSelectAllButton();
        driverUtils.clickButton(btnSaveAs);
        closeDocHasBeenSavedWindow();
        fileUtils.waitForFileDownload(documentName.getText() + PDF_EXTENSION);
    }

    public void saveFileWithOnlyFirstPage() {
        clickUnselectAllButton();
        driverUtils.clickButton(listOfDocumentPages.get(0));
        driverUtils.clickButton(btnSaveAs);
        closeDocHasBeenSavedWindow();
        fileUtils.waitForFileDownload(documentName.getText() + PDF_EXTENSION);
    }

    public void clickSelectAllButton() {
        driverUtils.waitForVisibilityOfElement(btnSelectAll);
        driverUtils.clickButton(btnSelectAll);
    }

    public void clickUnselectAllButton() {
        driverUtils.waitForVisibilityOfElement(btnUnselectAll);
        driverUtils.clickButton(btnUnselectAll);
    }

    public void closeDocHasBeenSavedWindow() {
        driverUtils.waitForVisibilityOfElement(btnCloseDocHasBeenSavedWindow);
        driverUtils.clickButton(btnCloseDocHasBeenSavedWindow);
    }
}
