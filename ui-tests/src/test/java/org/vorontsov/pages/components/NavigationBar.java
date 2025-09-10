package org.vorontsov.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.vorontsov.pages.BasePage;
import org.vorontsov.pages.SettingsPage;

public class NavigationBar extends BasePage {

    By userProfilePic = By.className("user-pic");
    By settingsLink = By.cssSelector("a.nav-link[href='/settings']");
    By loginLink = By.cssSelector("a.nav-link[href='/login']");

    public NavigationBar(WebDriver driver) {
        super(driver);
    }

    public String getUsername() {
        return find(userProfilePic).getAttribute("alt");
    }

    public SettingsPage visitSettingsPage() {
        find(settingsLink).click();
        return new SettingsPage(driver);
    }

    public void visitUserProfilePage() {
        find(userProfilePic).click();
    }

    public void visitSignInPage() {
        find(loginLink).click();
    }
}
