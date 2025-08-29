package org.vorontsov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.vorontsov.config.Config.BASE_URL;

public class ArticleFeedPage extends BasePage {

    By globalFeedLink = By.xpath("//a[text()='Global Feed']");
    By yourFreedLink = By.xpath("//a[text()='Your Feed']");

    public ArticleFeedPage(WebDriver driver) {
        super(driver);
    }

    public void openGlobalFeed() {
        visit(BASE_URL);
        click(globalFeedLink);
    }

    public void openPostWithTitle(String title) {
        String xpath = String.format("//h1[text()='%s']", title);
        By locator = By.xpath(xpath);
        find(locator).click();
    }


}
