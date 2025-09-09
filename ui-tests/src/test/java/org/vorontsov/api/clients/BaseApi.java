package org.vorontsov.api.clients;

import com.jayway.jsonpath.JsonPath;

import static io.restassured.RestAssured.given;
import static org.vorontsov.config.Config.API_URL;

public class BaseApi {
    private String apiUrl = API_URL;
    private String userEmail;
    private String userPassword;

    public BaseApi(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public String getLoggedInUserData() {
        return given().
                contentType("application/json").
                body("{\"user\":{\"email\":\""+ userEmail +"\",\"password\":\""+ userPassword +"\"}}").
                when().
                post(apiUrl + "/users/login").
                asString();
    }

    public  String getTokenForBrowser() {
        String response =  getLoggedInUserData();
        String token = JsonPath.parse(response).read("$.user.token");
        return token;
    }

    public  String getTokenForApi() {
        String response =  getLoggedInUserData();
        String token = JsonPath.parse(response).read("$.user.token");
        return "Token " + token;
    }
}