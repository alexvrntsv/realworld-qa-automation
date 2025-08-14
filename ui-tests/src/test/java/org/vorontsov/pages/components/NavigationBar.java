package org.vorontsov.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.vorontsov.pages.BasePage;

public class NavigationBar extends BasePage {

    By userProfileLink = By.className("user-pic");
    By settingsLink = By.cssSelector("a.nav-link[href='/settings']");

    public NavigationBar(WebDriver driver) {
        super(driver);
    }

    public String getUsername() {
        return this.find(userProfileLink).get;
    }

    public void performLogout() {
        this.find(settingsLink).click();
        this.find(By.xpath("//button[contains(text(), 'logout')]")).click();
    }
}
