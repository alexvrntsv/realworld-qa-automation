package org.vorontsov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.vorontsov.models.NewArticle;

import static org.vorontsov.config.Config.BASE_URL;

public class PublishArticlePage extends BasePage {

    private final By newArticleLink = By.cssSelector("a.nav-link[href='/editor']");
    private final By articleTitleInput = By.cssSelector("input[placeholder='Article Title']");
    private final By articleDescriptionInput = By.cssSelector("input[placeholder='What\\'s this article about?']");;
    private final By articleBodyInput = By.cssSelector("textarea[placeholder='Write your article (in markdown)']");
    private final By articleTagsInput = By.cssSelector("input[placeholder='Enter tags']");
    private final By publishArticleButton = By.xpath("//button[text()='Publish Article']");

    public PublishArticlePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        visit(BASE_URL);
        click(newArticleLink);
    }

    public void publishArticle(NewArticle articleData) {
        typeAndClear(articleTitleInput, articleData.title());
        typeAndClear(articleDescriptionInput, articleData.description());
        typeAndClear(articleBodyInput, articleData.body());
        String tags = String.join(" ", articleData.tagList());
        typeAndClear(articleTagsInput, tags);
        click(publishArticleButton);
    }
}
