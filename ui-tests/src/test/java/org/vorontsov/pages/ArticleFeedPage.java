package org.vorontsov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FeedPage extends BasePage {

    By globalFeedLink = By.xpath("//a[text()='Global Feed']");
    By yourFreedLink = By.xpath("//a[text()='Your Feed']");

    public FeedPage(WebDriver driver) {
        super(driver);
    }

    public void openGlobalFeed() {
        click(globalFeedLink);
    }

    public void openPostWithTitle(String title) {
        String xpath = String.format("//h1[text()='%s']", title);
        By locator = By.xpath(xpath);
        find(locator).click();
    }


}
