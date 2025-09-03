package org.vorontsov.utils;

import com.github.javafaker.Faker;
import org.vorontsov.utils.dto.NewArticle;
import org.vorontsov.utils.dto.NewUser;

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
        List<String> tags = Arrays.asList(faker.name().firstName(), faker.name().firstName());

        return new NewArticle(title, description, body, tags);
    }

    public static String createComment() {
        Faker faker = new Faker();

        return faker.hitchhikersGuideToTheGalaxy().quote();
    }
}
