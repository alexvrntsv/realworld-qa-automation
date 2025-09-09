package org.vorontsov.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.vorontsov.api.clients.UserApi;
import org.vorontsov.models.NewUser;

import static org.vorontsov.config.Config.BASE_URL;

public class AuthHelper {

    public static NewUser createAndLoginUser(WebDriver driver) {
        NewUser user = DataFaker.createNewFakeUser();
        UserApi api = new UserApi(user);
        api.registerUser();

        String token = api.getTokenForBrowser();

        driver.get(BASE_URL);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);", "jwt", token);

        driver.navigate().refresh();

        return user;
    }
}
