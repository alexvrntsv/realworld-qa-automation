package org.vorontsov.utils;

import com.github.javafaker.Faker;
import org.vorontsov.models.NewArticle;
import org.vorontsov.models.NewUser;
import org.vorontsov.models.UserSettings;

import java.util.Arrays;
import java.util.List;

public class DataFaker {
    public static NewUser createNewFakeUser() {
        Faker faker = new Faker();
        String userName = faker.name().username();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        return new NewUser(userName, email, password);
    }

    public static NewArticle createNewFakeArticle() {
        Faker faker = new Faker();
        String title = faker.lorem().sentence();
        String description = faker.lorem().sentence();
        String body = faker.lorem().paragraph();
        List<String> tags = null;

        return new NewArticle(title, description, body, tags);
    }

    public static NewArticle createNewFakeArticleWithTags(List<String> tagList) {
        Faker faker = new Faker();
        String title = faker.lorem().sentence();
        String description = faker.lorem().sentence();
        String body = faker.lorem().paragraph();
        List<String> tags = tagList;

        return new NewArticle(title, description, body, tags);
    }

    public static List<String> createTagsList() {
        Faker faker = new Faker();
        return Arrays.asList(faker.name().firstName(), faker.name().firstName());
    }

    public static String createComment() {
        Faker faker = new Faker();

        return faker.hitchhikersGuideToTheGalaxy().quote();
    }

    public static UserSettings createUserSettings() {
        var faker = new Faker();
        var image = faker.internet().avatar();
        var username = faker.name().username();
        var bio = faker.lorem().sentence();
        var email = faker.internet().emailAddress();
        var password = faker.internet().password();

        return new UserSettings(image, bio, username, email, password);
    }
}
