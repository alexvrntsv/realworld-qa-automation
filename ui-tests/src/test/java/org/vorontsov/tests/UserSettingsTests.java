package org.vorontsov.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.vorontsov.models.NewUser;
import org.vorontsov.models.UserSettings;
import org.vorontsov.pages.SettingsPage;
import org.vorontsov.pages.SignInPage;
import org.vorontsov.utils.AuthHelper;
import org.vorontsov.utils.DataFaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("User Profiles")
@Feature("User Settings Management")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class UserSettingsTests {

    private static WebDriver driver;
    private static NewUser user;
    private static UserSettings userSettingsData;
    private static SettingsPage settingsPage;

    @BeforeAll
    static void setup() {
        driver = new ChromeDriver();
    }

    @BeforeEach
    void initUserAndOpenSettings() {
        user = AuthHelper.createAndLoginUser(driver);
        userSettingsData = DataFaker.createUserSettings();
        settingsPage = new SettingsPage(driver);
        settingsPage.open();
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Tag("settings")
    @Story("Update profile picture")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a logged-in user can update their profile picture")
    @DisplayName("User can update profile picture")
    public void updateProfilePicture() {
        // Act
        settingsPage.setProfileImage(userSettingsData.image());
        settingsPage.clickSubmit();
        settingsPage.open();
        String currentProfileImage = settingsPage.getProfileImage();

        // Assert
        assertEquals(userSettingsData.image(), currentProfileImage);
    }

    @Test
    @Tag("settings")
    @Story("Update username")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a logged-in user can update their username")
    @DisplayName("User can update username")
    public void updateUsername() {
        // Act
        settingsPage.setUsername(userSettingsData.username());
        settingsPage.clickSubmit();
        settingsPage.open();
        String currentUsername = settingsPage.getUsername();

        // Assert
        assertEquals(userSettingsData.username(), currentUsername);
    }

    @Test
    @Tag("settings")
    @Story("Update bio")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a logged-in user can update their bio")
    @DisplayName("User can update bio")
    public void updateBio() {
        // Act
        settingsPage.setUserBio(userSettingsData.bio());
        settingsPage.clickSubmit();
        settingsPage.open();
        String currentBio = settingsPage.getUserBio();

        // Assert
        assertEquals(userSettingsData.bio(), currentBio);
    }

    @Test
    @Tag("settings")
    @Story("Update email")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a logged-in user can update their email")
    @DisplayName("User can update email")
    public void updateEmail() {
        // Act
        settingsPage.setEmail(userSettingsData.email());
        settingsPage.clickSubmit();
        settingsPage.open();
        String currentEmail = settingsPage.getEmail();

        // Assert
        assertEquals(userSettingsData.email(), currentEmail);
    }

    @Test
    @Disabled("Blocked: login fails after password change — known bug")
    @Tag("settings")
    @Story("Update password")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a logged-in user can update their password and login with the new password")
    @DisplayName("User can update password")
    public void updatePassword() {
        // Arrange
        SignInPage signInPage = new SignInPage(driver);

        // Act
        settingsPage.setPassword(userSettingsData.password());
        settingsPage.clickSubmit();
        settingsPage.open();
        settingsPage.logout();
        signInPage.open();
        signInPage.signIn(user.email(), userSettingsData.password());

        // Assert
        assertTrue(signInPage.userIsRegistered(user.username()));
    }
}
