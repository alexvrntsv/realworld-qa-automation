package org.vorontsov.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.vorontsov.pages.ArticleDetailsPage;
import org.vorontsov.pages.ArticleFeedPage;
import org.vorontsov.pages.UserProfilePage;
import org.vorontsov.utils.AuthHelper;
import org.vorontsov.utils.DataFaker;
import org.vorontsov.utils.DriverFactory;
import org.vorontsov.utils.Seeder;
import org.vorontsov.models.NewArticle;
import org.vorontsov.models.NewUser;

import static org.junit.jupiter.api.Assertions.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

@Epic("Articles")
@Feature("Article Details Management")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class ArticleDetailsTests {

    private static WebDriver driver;
    private ArticleDetailsPage articleDetailsPage;
    private ArticleFeedPage articleFeedPage;
    private static NewUser user;
    private NewArticle article;

    @BeforeAll
    static void setup() {
        driver = DriverFactory.createDriver();
    }

    @BeforeEach
    void addArticle() {
        user = AuthHelper.createAndLoginUser(driver);
        article = Seeder.createNewArticle(user);
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Tag("smoke")
    @Tag("comment")
    @Story("Add a comment to an article")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a logged-in user can add a comment to an article")
    @DisplayName("User can add a comment to an article")
    public void addComment() {
        // Arrange
        articleFeedPage = new ArticleFeedPage(driver);
        articleDetailsPage = new ArticleDetailsPage(driver);
        String comment = DataFaker.createComment();

        // Act
        articleFeedPage.openGlobalFeed();
        articleFeedPage.openPostWithTitle(article.title());
        articleDetailsPage.addComment(comment);

        // Assert
        assertTrue(articleDetailsPage.isCommentVisible(comment));
    }

    @Test
    @Tag("comment")
    @Story("Delete a comment from an article")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a logged-in user can delete their comment from an article")
    @DisplayName("User can delete a comment from an article")
    public void deleteComment() {
        // Arrange
        articleFeedPage = new ArticleFeedPage(driver);
        articleDetailsPage = new ArticleDetailsPage(driver);
        String comment = Seeder.createNewComment(user, article);

        // Act
        articleFeedPage.openGlobalFeed();
        articleFeedPage.openPostWithTitle(article.title());
        articleDetailsPage.deleteComment(user.username());

        // Assert
        assertTrue(articleDetailsPage.isCommentAbsent(user.username()));
    }

    @Test
    @Tag("article")
    @Story("Like an article")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a logged-in user can like an article")
    @DisplayName("User can like an article")
    public void likeArticle() throws InterruptedException {
        // Arrange
        articleFeedPage = new ArticleFeedPage(driver);
        articleDetailsPage = new ArticleDetailsPage(driver);
        var amountOfLikes = 1;

        // Act
        articleFeedPage.openGlobalFeed();
        articleFeedPage.likeAnArticle(article.title());

        // Assert
        assertEquals(amountOfLikes, articleFeedPage.getAmountOfLikes(article.title()));
    }

    @Test
    @Tag("article")
    @Story("Delete an article")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that a logged-in user can delete their article")
    @DisplayName("User can delete an article")
    public void deleteArticle() {
        // Arrange
        articleFeedPage = new ArticleFeedPage(driver);
        articleDetailsPage = new ArticleDetailsPage(driver);
        UserProfilePage userProfilePage = new UserProfilePage(driver);

        // Act
        articleFeedPage.openGlobalFeed();
        articleFeedPage.openPostWithTitle(article.title());
        articleDetailsPage.deleteArticle();
        userProfilePage.open();

        // Assert
        assertTrue(userProfilePage.hasNoArticles());
    }
}