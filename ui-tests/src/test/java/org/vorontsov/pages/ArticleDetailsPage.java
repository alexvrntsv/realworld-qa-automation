package org.vorontsov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ArticleDetailsPage extends BasePage{

    private final By articleTitle = By.cssSelector("h1");
    private final By articleBody = By.cssSelector("div.article-content p");
    private final By editArticleButton = By.xpath("//a[text()=' Edit Article']");

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
}
