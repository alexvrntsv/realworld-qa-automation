package org.vorontsov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SettingsPage extends BasePage {

    By logoutButton = By.xpath("//button[contains(text(), 'logout')]");

    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    public void logout() {
        find(logoutButton).click();
    }
}
