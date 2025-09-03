package org.vorontsov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserProfilePage extends BasePage {
    private final By articlePreview = By.className("article-preview");
    private final By myArticlesButton = By.xpath("//a[text()='My Articles']");

    private final String noArticlesMessage = "No articles are here... yet.";

    public UserProfilePage(WebDriver driver) {
        super(driver);
    }

    public Boolean hasNoArticles() {
        WebElement articleFeed = find(articlePreview);

        return noArticlesMessage.equals(articleFeed.getText());
    }
}
