package org.vorontsov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.vorontsov.pages.components.NavigationBar;

import java.util.List;

import static org.vorontsov.config.Config.BASE_URL;

public class UserProfilePage extends BasePage {
    private final By articlePreview = By.className("article-preview");
    private final By myArticlesButton = By.xpath("//a[text()='My Articles']");
    private final By favoriteArticlesButton = By.xpath("//a[text()='Favorited Articles']");

    private final String noArticlesMessage = "No articles are here... yet.";

    public UserProfilePage(WebDriver driver) {
        super(driver);
    }

    public Boolean hasNoArticles() {
        WebElement articleFeed = find(articlePreview);

        return noArticlesMessage.equals(articleFeed.getText());
    }

    public void open() {
        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.visitUserProfilePage();
    }

    public void openFavoriteArticles() {
        find(favoriteArticlesButton).click();
    }

    public void waitForPostsToLoad() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {}
    }

    public Boolean isArticleDisplayed(String title) {
        waitForPostsToLoad();

        List<WebElement> posts = findAll(articlePreview);

        for (WebElement post : posts) {
            String postTitle = post.findElement(By.tagName("h1")).getText();

            if (postTitle.equals(title)) {
                return true;
            }
        }

        throw new NoSuchElementException("No post found with title: " + title);
    }
}
