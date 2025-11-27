package org.vorontsov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ArticleDetailsPage extends BasePage{

    private final By articleTitle = By.cssSelector("h1");
    private final By articleBody = By.cssSelector("div.article-content p");
    private final By editArticleButton = By.xpath("//a[text()=' Edit Article']");
    private final By commentInput = By.cssSelector("textarea[placeholder='Write a comment...']");
    private final By addCommentButton = By.xpath("//button[text()='Post Comment']");
    private final By deleteArticleButton = By.xpath("//button[normalize-space()='Delete Article']");

    private By deleteCommentButton(String username) {
        return By.xpath(
                "//div[contains(@class,'card-footer')]" +
                        "[.//a[@class='comment-author' and text()='" + username + "']]" +
                        "//span[@class='mod-options']/i"
        );
    }

    private By userComment(String username) {
        return By.xpath("//div[@class='card']" +
                "[.//a[@class='comment-author' and contains(@href, '@"+ username +"')]]");
    }

    public ArticleDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void openEditArticleForm() {
        click(editArticleButton);
    }

    public String getArticleTitle() {
        return find(articleTitle).getText();
    }

    public String getArticleBody() {
        return find(articleBody).getText();
    }

    public void addComment(String comment) {
        type(commentInput, comment);
        click(addCommentButton);
    }

    public boolean isCommentVisible(String username) {
        return isDisplayed(userComment(username));
    }

    public boolean isCommentAbsent(String username) {
        return findAllNoWait(userComment(username)).isEmpty();
    }

    public void deleteComment(String username) {
        find(deleteCommentButton(username)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(deleteCommentButton(username)));
    }

    public void deleteArticle() {
        find(deleteArticleButton).click();
    }


}
