package org.vorontsov.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.vorontsov.models.NewUser;
import org.vorontsov.models.UserSettings;
import org.vorontsov.pages.SettingsPage;
import org.vorontsov.utils.AuthHelper;
import org.vorontsov.utils.DataFaker;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
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
    public void updateUsername() {
        // Act
        settingsPage.setUsername(userSettingsData.username());
        settingsPage.clickSubmit();
        settingsPage.open();
        String currentUsername = settingsPage.getUsername();

        // Assert
        assertEquals(userSettingsData.username(), currentUsername);
    }
}
