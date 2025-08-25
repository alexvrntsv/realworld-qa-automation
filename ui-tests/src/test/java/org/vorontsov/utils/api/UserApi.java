package org.vorontsov.utils.api;

import org.vorontsov.utils.dto.NewUser;

import static io.restassured.RestAssured.given;

public class UserApi extends BaseApi {

    public UserApi(String userEmail, String userPassword) {
        super(userEmail, userPassword);
    }

    public void registerUser(NewUser user) {
        given()
                .contentType("application/json")
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(201);
    }

}
