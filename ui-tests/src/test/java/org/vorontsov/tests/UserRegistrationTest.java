package org.vorontsov.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.vorontsov.pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.vorontsov.pages.components.NavigationBar;
import org.vorontsov.utils.Seeder;
import org.vorontsov.utils.dto.NewUser;

@Epic("Authentication")
@Feature("User Registration")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class UserRegistrationTest {

    private static WebDriver driver;
    private RegistrationPage regPage;

    @BeforeAll
    static void setupDriver() {
        driver = new ChromeDriver();
    }

    @BeforeEach
    void openRegistrationPage() {
        regPage = new RegistrationPage(driver);
        regPage.open();
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Tag("smoke")
    @Tag("user")
    @Story("Successful registration with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a new user can successfully sign up with unique credentials")
    @DisplayName("User can register with valid credentials")
    void registerValidNewUser() {
        String userName = "testUser";
        String email = "test@test.com";
        String password = "test123";

        regPage.registerWith(userName, email, password);

        assertTrue(regPage.userIsRegistered(userName));

        new NavigationBar(driver).visitSettingsPage().logout();
    }

    @Test
    @Tag("regression")
    @Tag("user")
    @Story("Prevent registration with an existing email")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that registration fails when using an already registered email address")
    @DisplayName("User cannot register with an existing email")
    void registerWithExistingEmail() {
        Seeder seeder = new Seeder();
        seeder.createNewUser();

        NewUser existingUserUser = seeder.getLastNewUser();

        String userName = "anotherUser";
        String email = existingUserUser.email();
        String password = "test123";

        regPage.registerWith(userName, email, password);

        assertTrue(regPage.isErrorMessageDisplayed("email has already been taken"));
    }
}
