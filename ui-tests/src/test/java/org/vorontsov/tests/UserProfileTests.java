package org.vorontsov.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.vorontsov.pages.ArticleFeedPage;
import org.vorontsov.pages.UserProfilePage;
import org.vorontsov.utils.AuthHelper;
import org.vorontsov.utils.Seeder;
import org.vorontsov.models.NewArticle;
import org.vorontsov.models.NewUser;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("User Profiles")
@Feature("User Profile Management")
@TestMethodOrder(MethodOrderer.DisplayName.class)
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

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Tag("profile")
    @Story("Display author's own articles")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a logged-in user can see their authored articles on the profile page")
    @DisplayName("User can see their own articles")
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
    @Tag("profile")
    @Story("Display liked articles")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a logged-in user can see their liked articles on the profile page")
    @DisplayName("User can see their liked articles")
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
    @Tag("profile")
    @Story("Follow an author")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a logged-in user can follow another author and see their articles in the feed")
    @DisplayName("User can follow another author")
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
