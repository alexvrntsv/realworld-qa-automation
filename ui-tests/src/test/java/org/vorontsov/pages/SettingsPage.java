package org.vorontsov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.vorontsov.pages.components.NavigationBar;

public class SettingsPage extends BasePage {

    By logoutButton = By.xpath("//button[contains(text(), 'logout')]");
    By profileImageInput = By.cssSelector("input[placeholder='URL of profile picture']");
    By usernameInput = By.cssSelector("input[placeholder='Username']");
    By userBioInput = By.cssSelector("textarea[placeholder='Short bio about you']");
    By emailInput = By.cssSelector("input[type='email']");
    By passwordInput = By.cssSelector("input[type='password']");
    By submitButton = By.cssSelector("button[type='submit']");


    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.visitSettingsPage();
    }

    public void logout() {
        find(logoutButton).click();
    }

    public void setProfileImage(String imageUrl) {
        typeAndClear(profileImageInput, imageUrl);
    }

    public void setUsername(String username) {
        typeAndClear(usernameInput, username);
    }

    public void setUserBio(String bio) {
        typeAndClear(userBioInput, bio);
    }

    public void setEmail(String email) {
        typeAndClear(emailInput, email);
    }

    public void setPassword(String password) {
        typeAndClear(passwordInput, password);
    }

    public void submitForm() {
        click(submitButton);
    }

    public String getProfileImage() {
        return find(profileImageInput).getAttribute("value");
    }

    public String getUsername() {
        return find(usernameInput).getAttribute("value");
    }

    public String getUserBio() {
        return find(userBioInput).getAttribute("value");
    }

    public String getEmail() {
        return find(emailInput).getAttribute("value");
    }

    public String getPassword() {
        return find(passwordInput).getAttribute("value");
    }

    public void clickSubmit() {
        click(submitButton);
    }
}
