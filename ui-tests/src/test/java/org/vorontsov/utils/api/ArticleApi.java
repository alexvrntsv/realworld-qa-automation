package org.vorontsov.utils.api;

import io.restassured.http.ContentType;
import org.vorontsov.utils.dto.NewArticle;
import org.vorontsov.utils.dto.NewArticleWrapper;
import org.vorontsov.utils.dto.NewUser;

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

    public void createCommentToArticle(NewArticle article, String message) {
        String slug = article.title()
                .toLowerCase()
                .trim()
                .replaceAll("\\.$", "")
                .replaceAll("[.\\s]", "-");

        given()
                .header("Authorization", getTokenForApi())
                .contentType(ContentType.JSON)
                .body("{\"comment\": {\"body\": \"" + message + "\"}}")
                .when()
                .post("/articles/" + slug + "/comments")
                .then()
                .statusCode(201);
    }
}
