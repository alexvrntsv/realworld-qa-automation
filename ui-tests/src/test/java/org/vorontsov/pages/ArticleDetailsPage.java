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
    private final String commentLocatorTemplate = "//p[@class='card-text' and normalize-space(text())='%s']";
    private final String deleteCommentButtonTemplate = "//p[@class='card-text' and text()='%s']/ancestor::div[@class='card']//span[@class='mod-options']/i";
    private final By deleteArticleButton = By.xpath("//button[normalize-space()='Delete Article']");

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

    public boolean isCommentVisible(String text) {
        By commentLocator = By.xpath(
                String.format(commentLocatorTemplate, text)
        );
        return isDisplayed(commentLocator);
    }

    public void deleteComment(String text) {
        find(By.xpath(String.format(deleteCommentButtonTemplate, text))).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(String.format(deleteCommentButtonTemplate, text))));

    }

    public void deleteArticle() {
        find(deleteArticleButton).click();
    }


}
