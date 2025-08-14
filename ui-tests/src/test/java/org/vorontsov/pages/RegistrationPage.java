package org.vorontsov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.vorontsov.pages.components.NavigationBar;

import java.util.Objects;

import static org.vorontsov.config.Config.BASE_URL;

public class RegistrationPage extends BasePage{

    By usernameInput = By.cssSelector("input[placeholder='Username']");
    By emailInput = By.cssSelector("input[placeholder='Email']");
    By passwordInput = By.cssSelector("input[placeholder='Password']");
    By signUpButton = By.cssSelector("button[type='submit'].btn-primary");
    By signUpLink = By.xpath("//a[@href='/register']");

    public RegistrationPage(WebDriver driver) {
        super(driver);
        visit(BASE_URL);
        find(signUpLink).click();
    }

    public void visitRegistrationPage() {
        visit(BASE_URL);
        find(signUpLink).click();
    }

    public void registerWith(String username, String email, String password) {
        type(usernameInput, username);
        type(emailInput, email);
        type(passwordInput, password);
        click(signUpButton);
    }

    public boolean userIsRegistered(String username){
        NavigationBar navBar = new NavigationBar(driver);
        return Objects.equals(username, navBar.getUsername());
    }
}
