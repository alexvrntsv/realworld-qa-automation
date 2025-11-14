package org.vorontsov.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver createDriver() {

        WebDriver driver;
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        if (isHeadless) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("disable-infobars");
            options.addArguments("--disable-extensions");
            options.addArguments("--window-size=1920,1080");
            driver = WebDriverManager.chromedriver().capabilities(options).create();
        } else {
            driver = WebDriverManager.chromedriver().create();
        }

        return driver;
    }
}
