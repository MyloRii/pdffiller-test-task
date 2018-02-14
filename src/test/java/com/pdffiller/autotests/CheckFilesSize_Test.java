package com.pdffiller.autotests;

import com.pdffiller.autotests.pages.*;
import com.pdffiller.autotests.utils.DataGenerator;
import com.pdffiller.autotests.utils.DriverFactory;
import com.pdffiller.autotests.utils.DriverUtils;
import com.pdffiller.autotests.utils.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckFilesSize_Test {

    //TestData
    private final String EMAIL = DataGenerator.generateEmailAddress();
    private final String PASSWORD = DataGenerator.getRandomString();

    private WebDriver driver;
    private DriverUtils driverUtils;
    private MainPage mainPage;
    private LoginPage loginPage;
    private MyDocsPage myDocsPage;
    private DocumentPage documentPage;
    private SaveAsPage saveAsPage;
    private FileUtils fileUtils;

    @BeforeTest
    public void setup() {
        //Change geChromeDriverMacOS() to getChromeDriverWindows() if you're using Windows
        driver = DriverFactory.getChromeDriverMacOS();
        driverUtils = new DriverUtils(driver);
        mainPage = new MainPage(driverUtils);
        loginPage = new LoginPage(driverUtils);
        myDocsPage = new MyDocsPage(driverUtils);
        documentPage = new DocumentPage(driverUtils);
        saveAsPage = new SaveAsPage(driverUtils);
        fileUtils = new FileUtils();
    }

    //Please use following maven config to run this test: test -PSmoke
    @Test
    public void checkThatPDFfilesWithDifferentPagesHaveDifferentSizeTest() {
        mainPage.openPdffillerMainPage();
        mainPage.clickLoginButton();

        loginPage.registerNewUser(EMAIL, PASSWORD);

        myDocsPage.openHowToGuideDoc();

        documentPage.handleHelpDialog();
        documentPage.clickSaveAsButton();

        fileUtils.deleteTestFilesInDownloadsFolder();
        saveAsPage.saveFileWithAllPagesAndCloseDocHasBeenSavedWindow();
        saveAsPage.saveFileWithOnlyFirstPage();

        assertEquals(fileUtils.checkIfTwoTestFilesHaveDifferentLength(), true);
    }

    @AfterTest
    public void tearDown() {
        driverUtils.closeBrowser();
    }
}
