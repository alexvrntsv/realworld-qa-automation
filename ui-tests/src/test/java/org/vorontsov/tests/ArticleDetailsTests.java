package org.vorontsov.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.vorontsov.pages.ArticleDetailsPage;
import org.vorontsov.pages.ArticleFeedPage;
import org.vorontsov.pages.RegistrationPage;
import org.vorontsov.utils.AuthHelper;
import org.vorontsov.utils.DataFaker;
import org.vorontsov.utils.Seeder;
import org.vorontsov.utils.dto.NewArticle;
import org.vorontsov.utils.dto.NewUser;

public class ArticleDetailsTests {

    private static WebDriver driver;
    private ArticleDetailsPage articleDetailsPage;
    private ArticleFeedPage articleFeedPage;
    private static NewUser user;
    private static NewArticle article;

    @BeforeAll
    static void setup() {
        driver = new ChromeDriver();
        user = AuthHelper.createAndLoginUser(driver);
        article = Seeder.createNewArticle(user);
    }

    @Test
    public void addComment() {
        //Arrange
        articleFeedPage = new ArticleFeedPage(driver);
        articleDetailsPage = new ArticleDetailsPage(driver);
        String comment = DataFaker.createComment();

        //Act
        articleFeedPage.openGlobalFeed();
        articleFeedPage.openPostWithTitle(article.title());

        articleDetailsPage.addComment(comment);

        // Assert
        articleDetailsPage.isCommentVisible(comment);
    }
}
