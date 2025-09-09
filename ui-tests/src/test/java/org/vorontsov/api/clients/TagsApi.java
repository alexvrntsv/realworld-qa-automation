package org.vorontsov.api.clients;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.vorontsov.models.NewUser;

import java.util.List;
import static io.restassured.RestAssured.get;

public class TagsApi extends BaseApi {

    public TagsApi(String userEmail, String userPassword) { super(userEmail, userPassword); }

    public TagsApi(NewUser user) {
        super(user.email(), user.password());
    }

    public static String getTags() {

        return RestAssured
                .given()
                .when()
                .get("/tags")
                .then()
                .statusCode(200)
                .extract()
                .asString();
    }
}
