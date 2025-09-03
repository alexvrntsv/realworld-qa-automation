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
        System.out.println(posts.size());

        for (WebElement post : posts) {
            String postTitle = post.findElement(By.tagName("h1")).getText();

            if (postTitle.equals(title)) {
                WebElement likeButton = post.findElement(likeArticleButton);
                return Integer.parseInt(likeButton.getText());
            }
        }

        throw new NoSuchElementException("No post found with title: " + title);
    }
}
