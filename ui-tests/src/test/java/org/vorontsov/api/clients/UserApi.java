package org.vorontsov.api.clients;

import org.vorontsov.models.NewUser;
import org.vorontsov.api.wrappers.NewUserWrapper;

import static io.restassured.RestAssured.given;
import static org.vorontsov.config.Config.API_URL;

public class UserApi extends BaseApi {
    private String userName;
    private String userEmail;
    private String userPassword;

    public UserApi(String userName, String userEmail, String userPassword) {
        super(userEmail, userPassword);
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public UserApi(NewUser user) {
        super(user.email(), user.password());
        this.userName = user.username();
        this.userEmail = user.email();
        this.userPassword = user.password();
    }

    public void registerUser() {
        var user = new NewUserWrapper(new NewUser(userName, userEmail, userPassword));
        System.out.println(user);
        given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(API_URL + "/users")
                .then()
                .statusCode(200);
    }
}
