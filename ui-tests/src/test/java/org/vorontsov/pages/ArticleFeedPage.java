package org.vorontsov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.vorontsov.config.Config.BASE_URL;

public class ArticleFeedPage extends BasePage {

    By globalFeedLink = By.xpath("//a[text()='Global Feed']");
    By yourFreedLink = By.xpath("//a[text()='Your Feed']");
    By articlePreview = By.className("article-preview");
    By likeArticleButton = By.cssSelector("button.btn.btn-sm");

    String authorProfileLinkTemplate = "//a[@class='author' and text()='%s']";
    String articleTitleTemplate = "//h1[text()='%s']";

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
        By articleTitle = By.xpath(String.format(articleTitleTemplate, title));

        return find(articleTitle);
    }

    public void openPostWithTitle(String title) {
        findPostWithTitle(title).click();
    }

    public void waitForPostsToLoad() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {}
    }


    public void likeAnArticle(String title) {
        waitForPostsToLoad();

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
        waitForPostsToLoad();

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
        By authorProfileLink = By.xpath(String.format(authorProfileLinkTemplate, authorName));
        find(authorProfileLink).click();
    }

    public boolean isArticleDisplayed(String title) {
        By articleTitle = By.xpath(String.format(articleTitleTemplate, title));
        return isDisplayed(articleTitle);
    }
}
