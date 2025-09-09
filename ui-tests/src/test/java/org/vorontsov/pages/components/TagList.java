package org.vorontsov.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.vorontsov.pages.BasePage;

import java.util.List;

import static org.vorontsov.config.Config.BASE_URL;

public class TagList extends BasePage {

    By tagList = By.className("tag-list");
    By tag = By.cssSelector("a.tag-default.tag-pill");

    public TagList(WebDriver driver) {
        super(driver);
    }

    public void open() {
        visit(BASE_URL);
    }

    public List<String> getDisplayedTags() {
        List<WebElement> elements = findAll(tag);
        return elements.stream()
                .map(WebElement::getText)
                .toList();
    }

    public void clickTag(String tagName) {
        List<WebElement> elements = findAll(tag);
        for (WebElement element : elements) {
            if (element.getText().equals(tagName)) {
                element.click();
            }
        }
    }
}
