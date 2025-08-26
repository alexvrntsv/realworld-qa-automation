package org.vorontsov.utils;

import com.github.javafaker.Faker;
import org.vorontsov.utils.dto.NewUser;

public class DataFaker {
    static public NewUser createNewFakeUser() {
        Faker faker = new Faker();
        String userName = faker.name().username();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        return new NewUser(userName, email, password);
    }
}
