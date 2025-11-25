package org.vorontsov.tests;


import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.vorontsov.pages.ArticleDetailsPage;
import org.vorontsov.pages.ArticleFeedPage;
import org.vorontsov.pages.PublishArticlePage;
import org.vorontsov.utils.AuthHelper;
import org.vorontsov.utils.DataFaker;
import org.vorontsov.utils.DriverFactory;
import org.vorontsov.utils.Seeder;
import org.vorontsov.models.NewArticle;
import org.vorontsov.models.NewUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Epic("Articles")
@Feature("Post Management")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class NewArticleTests {

    private static WebDriver driver;
    private PublishArticlePage publishArticlePage;
    private static NewUser user;


    @BeforeAll
    static void setup() {
        driver = DriverFactory.createDriver();
        user = AuthHelper.createAndLoginUser(driver);
    }

    @BeforeEach
    void openNewArticlePage() {
        publishArticlePage = new PublishArticlePage(driver);
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Tag("smoke")
    @Tag("article")
    @Story("Successful creation of a new article")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a logged-in user can create a new article with valid data")
    @DisplayName("User can create a new article")
    public void testCreateNewArticle() {
        // Arrange
        List<String> tags = DataFaker.createTagsList();
        NewArticle newArticle = DataFaker.createNewFakeArticleWithTags(tags);
        ArticleDetailsPage articleDetails = new ArticleDetailsPage(driver);

        // Act
        publishArticlePage.open();
        publishArticlePage.publishArticle(newArticle);

        String articleTitle = articleDetails.getArticleTitle();
        String articleBody = articleDetails.getArticleBody();

        // Assert
        assertAll(
                () -> assertEquals(newArticle.title(), articleTitle),
                () -> assertEquals(newArticle.body(), articleBody)
        );
    }

    @Test
    @Tag("regression")
    @Tag("article")
    @Story("Edit an existing article")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a user can edit an existing article and changes are saved correctly")
    @DisplayName("User can edit an existing article")
    public void testEditArticle() {
        // Arrange
        List<String> tags = DataFaker.createTagsList();
        NewArticle article = Seeder.createNewArticleWithTags(user, tags);
        ArticleFeedPage articleFeedPage= new ArticleFeedPage(driver);
        ArticleDetailsPage articleDetails = new ArticleDetailsPage(driver);

        // Act
        articleFeedPage.openGlobalFeed();
        articleFeedPage.openPostWithTitle(article.title());

        articleDetails.openEditArticleForm();

        NewArticle editedArticle = DataFaker.createNewFakeArticle();

        publishArticlePage.publishArticle(editedArticle);

        String articleTitle = articleDetails.getArticleTitle();
        String articleBody = articleDetails.getArticleBody();

        // Assert
        assertAll(
                () -> assertEquals(editedArticle.title(), articleTitle),
                () -> assertEquals(editedArticle.body(), articleBody)
        );
    }
}

