package com.pdffiller.autotests.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getChromeDriverForWindows() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "browser_drivers\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriver getChromeDriverMacOS() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "browser_drivers/chromedriver_mac64/chromedriver");
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().fullscreen();
        }
        return driver;
    }
}
