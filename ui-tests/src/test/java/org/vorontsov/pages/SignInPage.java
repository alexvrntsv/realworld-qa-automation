package org.vorontsov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.vorontsov.models.NewUser;
import org.vorontsov.pages.components.NavigationBar;

import java.util.Objects;

public class SignInPage extends BasePage{

    private final By emailInput = By.cssSelector("input[placeholder='Email']");
    private final By passwordInput = By.cssSelector("input[placeholder='Password']");
    private final By signInButton = By.cssSelector("button[type='submit']");

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.visitSignInPage();
    }

    public void signIn(String username, String password) {
        type(emailInput, username);
        type(passwordInput, password);
        click(signInButton);
    }

    public boolean userIsRegistered(String username) {
        NavigationBar navBar = new NavigationBar(driver);
        return Objects.equals(username, navBar.getUsername());
    }
}
