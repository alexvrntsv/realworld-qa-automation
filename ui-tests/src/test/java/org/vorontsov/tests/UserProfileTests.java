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
import org.vorontsov.utils.Seeder;
import org.vorontsov.utils.dto.NewArticle;
import org.vorontsov.utils.dto.NewUser;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserProfileTests {

    private static WebDriver driver;
    private UserProfilePage userProfilePage;
    private static NewUser user;
    private NewArticle article;

    @BeforeAll
    static void setup() {
        driver = new ChromeDriver();
    }

    @BeforeEach
    void addArticle() {
        user = AuthHelper.createAndLoginUser(driver);
    }

    @Test
    public void displayAuthorArticles() {
        // Arrange
        article = Seeder.createNewArticle(user);
        userProfilePage = new UserProfilePage(driver);

        // Act
        userProfilePage.open();

        // Assert
        assertTrue(userProfilePage.isArticleDisplayed(article.title()));
    }

    @Test
    public void displayLikedArticles() {
        // Arrange
        article = Seeder.createAndLikeArticle(user);
        userProfilePage = new UserProfilePage(driver);

        // Act
        userProfilePage.open();
        userProfilePage.openFavoriteArticles();

        // Assert
        assertTrue(userProfilePage.isArticleDisplayed(article.title()));
    }

    @Test
    public void followAuthor() {
        // Arrange
        article = Seeder.createAndLikeArticle(user);
        userProfilePage = new UserProfilePage(driver);
        ArticleFeedPage articleFeedPage = new ArticleFeedPage(driver);
        String followedUserName = user.username();
        user = AuthHelper.createAndLoginUser(driver);

        // Act
        articleFeedPage.openGlobalFeed();
        articleFeedPage.openUserDetails(followedUserName);
        userProfilePage.followAuthor(followedUserName);
        articleFeedPage.openYourFeed();

        // Assert
        assertTrue(articleFeedPage.isArticleDisplayed(article.title()));
    }
}
