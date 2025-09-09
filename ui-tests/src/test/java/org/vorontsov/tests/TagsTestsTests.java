package org.vorontsov.tests;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.vorontsov.api.clients.TagsApi;
import org.vorontsov.models.NewArticle;
import org.vorontsov.pages.ArticleFeedPage;
import org.vorontsov.pages.components.TagList;
import org.vorontsov.utils.AuthHelper;
import org.vorontsov.models.NewUser;
import org.vorontsov.utils.DataFaker;
import org.vorontsov.utils.Seeder;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TagsTestsTests {

    private static WebDriver driver;
    private static NewUser user;
    private static TagList tagListPage;

    @BeforeAll
    static void setup() {
        driver = new ChromeDriver();
    }

    @BeforeEach
    void logInUser() {
        user = AuthHelper.createAndLoginUser(driver);
        tagListPage = new TagList(driver);

    }

    @Test
    public void displayPopularTags() {
        // Arrange
        List<String> expectedTags = DataFaker.createTagsList();
        NewArticle article = Seeder.createNewArticleWithTags(user, expectedTags);

        // Act
        tagListPage.open();
        List<String> actualTags = tagListPage.getDisplayedTags();

        // Assert
        assertTrue(actualTags.containsAll(expectedTags));
    }

    @Test
    public void filterArticlesByTag() {
        // Arrange
        ArticleFeedPage articleFeedPage = new ArticleFeedPage(driver);
        List<String> expectedTags = DataFaker.createTagsList();
        NewArticle article = Seeder.createNewArticleWithTags(user, expectedTags);

        // Act
        tagListPage.open();
        tagListPage.clickTag(expectedTags.get(0));

        // Assert
        assertTrue(articleFeedPage.isArticleDisplayed(article.title()));
    }
}
