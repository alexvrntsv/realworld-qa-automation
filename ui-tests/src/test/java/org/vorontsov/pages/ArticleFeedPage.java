package org.vorontsov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.vorontsov.config.Config.BASE_URL;

public class ArticleFeedPage extends BasePage {

    private final By globalFeedLink = By.xpath("//a[text()='Global Feed']");
    private final By yourFreedLink = By.xpath("//a[text()='Your Feed']");
    private final By articlePreview = By.className("article-preview");
    private final By likeArticleButton = By.cssSelector("button.btn.btn-sm");

    private By authorProfileLink(String username) {
        return By.xpath("//a[@class='author' and text()='" + username + "']");
    }

    private By articleTitle(String title) {
        return By.xpath(String.format("//h1[text()='" + title + "']"));
    }

    public ArticleFeedPage(WebDriver driver) {
        super(driver);
    }

    public void openGlobalFeed() {
        visit(BASE_URL);
        click(globalFeedLink);
    }

    public void openYourFeed() {
        visit(BASE_URL);
    }

    public WebElement findPostWithTitle(String title) {
        return find(articleTitle(title));
    }

    public void openPostWithTitle(String title) {
        findPostWithTitle(title).click();
    }

    public void likeAnArticle(String title) {
        findPostWithTitle(title);

        List<WebElement> posts = findAll(articlePreview);

        for (WebElement post : posts) {
            String postTitle = post.findElement(By.tagName("h1")).getText();

            if (postTitle.equals(title)) {
                WebElement likeButton = post.findElement(likeArticleButton);
                likeButton.click();
                return;
            }
        }

        throw new NoSuchElementException("No post found with title: " + title);
    }

    public int getAmountOfLikes(String title) {
        findPostWithTitle(title);

        List<WebElement> posts = findAll(articlePreview);

        for (WebElement post : posts) {
            String postTitle = post.findElement(By.tagName("h1")).getText();

            if (postTitle.equals(title)) {
                WebElement likeButton = post.findElement(likeArticleButton);
                return Integer.parseInt(likeButton.getText());
            }
        }

        throw new NoSuchElementException("No post found with title: " + title);
    }

    public void openUserDetails(String authorName) {
        find(authorProfileLink(authorName)).click();
    }

    public boolean isArticleDisplayed(String title) {
        return isDisplayed(articleTitle(title));
    }
}
