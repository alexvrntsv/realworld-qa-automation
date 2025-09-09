package org.vorontsov.api.clients;

import io.restassured.http.ContentType;
import org.vorontsov.models.NewArticle;
import org.vorontsov.api.wrappers.NewArticleWrapper;
import org.vorontsov.models.NewUser;

import static io.restassured.RestAssured.given;

public class ArticleApi extends BaseApi {

    public ArticleApi(String userEmail, String userPassword) {
        super(userEmail, userPassword);
    }

    public ArticleApi(NewUser user) {
        super(user.email(), user.password());
    }

    public void createNewArticle(NewArticle article) {
        given()
                .header("Authorization", getTokenForApi())
                .contentType(ContentType.JSON)
                .body(new NewArticleWrapper(article))
                .when()
                .post("/articles")
                .then()
                .statusCode(201);
    }

    public void createCommentToArticle(String title, String message) {
        String slug = toSlug(title);

        given()
                .header("Authorization", getTokenForApi())
                .contentType(ContentType.JSON)
                .body("{\"comment\": {\"body\": \"" + message + "\"}}")
                .when()
                .post("/articles/" + slug + "/comments")
                .then()
                .statusCode(201);
    }

    public int getAmountOfLikes(String title) {
        String slug = toSlug(title);

        return given()
                .header("Authorization", getTokenForApi())
                .contentType(ContentType.JSON)
                .when()
                .get("/articles/{slug}", slug)
                .then()
                .statusCode(201)
                .extract()
                .path("article.favoritesCount");
    }

    public void likeArticle(String title) {
        String slug = toSlug(title);

        given()
                .header("Authorization", getTokenForApi())
                .contentType(ContentType.JSON)
                .when()
                .post("/articles/{slug}/favorite", slug)
                .then()
                .statusCode(200);
    }

    private String toSlug(String title) {
        return title.toLowerCase()
                .trim()
                .replaceAll("\\.$", "")
                .replaceAll("[.\\s]", "-");
    }
}
