package org.vorontsov.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;
import java.util.List;

public class BasePage {

    static final Logger log = getLogger(lookup().lookupClass());

    public WebDriver driver;
    WebDriverWait wait;
    int timeoutSec = 5;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
    }

    public void setTimeoutSec(int timeoutSec) {
        this.timeoutSec = timeoutSec;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
    }

    public void visit(String url) {
        driver.get(url);
    }

    public WebElement findWithoutWait(By element) {
        return driver.findElement(element);
    }

    public WebElement find(By element) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public List<WebElement> findAll(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> findAllNoWait(By locator) {
        return driver.findElements(locator); // Без ожиданий
    }

    public void click(By element) {
        find(element).click();
    }

    public void type(By element, String text) {
        find(element).sendKeys(text);
    }

    public void typeAndClear(By element, String text) {
        WebElement el = find(element);
        el.clear();
        el.sendKeys(text);
    }

    public String getText(By element) {
        return find(element).getText();
    }

    public boolean isDisplayed(By locator) {
        try {
            find(locator);
        } catch (TimeoutException e) {
            log.warn("Timeout of {} wait for {}", timeoutSec, locator);
            return false;
        }
        return true;
    }

    public void scrollTo(By locator) {
        WebElement element = find(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
