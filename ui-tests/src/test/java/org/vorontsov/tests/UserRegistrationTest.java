package org.vorontsov.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.vorontsov.pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRegistrationTest {

    private static WebDriver driver;
    private RegistrationPage regPage;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @BeforeEach
    void openRegistrationPage() {
        regPage = new RegistrationPage(driver);
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void registerValidNewUser() {
        String userName = "testUser";
        String email = "test@test.com";
        String password = "test123";

        regPage.open().registerWith(userName, email, password);

        assertTrue(regPage.userIsRegistered(userName));
    }

    @Test
    public void registerWithExistingEmail() {
        String userName = "anotherUser";
        String email = "test@test.com";
        String password = "test123";

        regPage.open().registerWith(userName, email, password);

        assertTrue(regPage.isErrorMessageDisplayed("email has already been taken"));
    }
}
