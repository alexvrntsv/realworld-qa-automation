package org.vorontsov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.vorontsov.pages.components.NavigationBar;

import java.util.List;
import java.util.Objects;

import static org.vorontsov.config.Config.BASE_URL;

public class RegistrationPage extends BasePage{

    By usernameInput = By.cssSelector("input[placeholder='Username']");
    By emailInput = By.cssSelector("input[placeholder='Email']");
    By passwordInput = By.cssSelector("input[placeholder='Password']");
    By signUpButton = By.cssSelector("button[type='submit'].btn-primary");
    By signUpLink = By.xpath("//a[@href='/register']");
    By errorMessagesList = By.cssSelector("ul.error-messages li");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPage open() {
        visit(BASE_URL);
        click(signUpLink);
        return this;
    }


    public void registerWith(String username, String email, String password) {
        type(usernameInput, username);
        type(emailInput, email);
        type(passwordInput, password);
        click(signUpButton);
    }

    public boolean userIsRegistered(String username) {
        NavigationBar navBar = new NavigationBar(driver);
        return Objects.equals(username, navBar.getUsername());
    }

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
