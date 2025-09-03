package org.vorontsov.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.vorontsov.pages.ArticleDetailsPage;
import org.vorontsov.pages.ArticleFeedPage;
import org.vorontsov.pages.UserProfilePage;
import org.vorontsov.pages.components.NavigationBar;
import org.vorontsov.utils.AuthHelper;
import org.vorontsov.utils.DataFaker;
import org.vorontsov.utils.Seeder;
import org.vorontsov.utils.dto.NewArticle;
import org.vorontsov.utils.dto.NewUser;

import static org.junit.jupiter.api.Assertions.*;

public class ArticleDetailsTests {

    private static WebDriver driver;
    private ArticleDetailsPage articleDetailsPage;
    private ArticleFeedPage articleFeedPage;
    private static NewUser user;
    private NewArticle article;

    @BeforeAll
    static void setup() {
        driver = new ChromeDriver();

    }

    @BeforeEach
    void addArticle() {
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

        //Assert
        assertTrue(articleDetailsPage.isCommentVisible(comment));
    }

    @Test
    public void deleteComment() {
        //Arrange
        articleFeedPage = new ArticleFeedPage(driver);
        articleDetailsPage = new ArticleDetailsPage(driver);
        String comment = Seeder.createNewComment(user, article);

        //Act
        articleFeedPage.openGlobalFeed();
        articleFeedPage.openPostWithTitle(article.title());

        articleDetailsPage.deleteComment(comment);

        //Assert
        assertFalse(articleDetailsPage.isCommentVisible(comment));
    }

    @Test
    public void  likeArticle() throws InterruptedException {
        //Arrange
        articleFeedPage = new ArticleFeedPage(driver);
        articleDetailsPage = new ArticleDetailsPage(driver);
        var amountOfLikes = 1;

        //Act
        articleFeedPage.openGlobalFeed();
        articleFeedPage.likeAnArticle(article.title());

        //Assert
        assertEquals(amountOfLikes, articleFeedPage.getAmountOfLikes(article.title()));
    }

    @Test
    public void deleteArticle() {
        //Arrange
        articleFeedPage = new ArticleFeedPage(driver);
        articleDetailsPage = new ArticleDetailsPage(driver);
        NavigationBar navigationBar = new NavigationBar(driver);
        UserProfilePage userProfilePage = new UserProfilePage(driver);

        //Act
        articleFeedPage.openGlobalFeed();
        articleFeedPage.openPostWithTitle(article.title());
        articleDetailsPage.deleteArticle();
        navigationBar.visitUserProfilePage();

        //Assert
        assertTrue(userProfilePage.hasNoArticles());
    }
}
