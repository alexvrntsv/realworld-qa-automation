package org.vorontsov.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.vorontsov.pages.components.NavigationBar;

import java.util.List;
import java.util.Objects;

import static org.vorontsov.config.Config.BASE_URL;

public class RegistrationPage extends BasePage {

    private final By usernameInput = By.cssSelector("input[placeholder='Username']");
    private final By emailInput = By.cssSelector("input[placeholder='Email']");
    private final By passwordInput = By.cssSelector("input[placeholder='Password']");
    private final By signUpButton = By.cssSelector("button[type='submit'].btn-primary");
    private final By signUpLink = By.xpath("//a[@href='/register']");
    private final By errorMessagesList = By.cssSelector("ul.error-messages li");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        visit(BASE_URL);
        click(signUpLink);
    }

    @Step("Register with username: {username}, email: {email}")
    public void registerWith(String username, String email, String password) {
        type(usernameInput, username);
        type(emailInput, email);
        type(passwordInput, password);
        click(signUpButton);
    }

    @Step("Verify that user {username} is registered")
    public boolean userIsRegistered(String username) {
        NavigationBar navBar = new NavigationBar(driver);
        return Objects.equals(username, navBar.getUsername());
    }

    @Step("Check if error message '{errorMessage}' is displayed")
    public boolean isErrorMessageDisplayed(String errorMessage) {
        try {
            List<WebElement> errorElements = findAll(errorMessagesList);
            for (WebElement element : errorElements) {
                if (element.getText().trim().equalsIgnoreCase(errorMessage.trim())) {
                    return true;
                }
            }
        } catch (TimeoutException e) {
            return false;
        }
        return false;
    }
}
