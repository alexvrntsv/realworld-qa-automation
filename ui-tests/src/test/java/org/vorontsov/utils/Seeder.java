package org.vorontsov.utils;

import com.github.javafaker.Faker;
import org.vorontsov.utils.api.UserApi;
import org.vorontsov.utils.dto.NewUser;

public class Seeder {
    public UserApi userApi;
    public NewUser lastNewUser;

    public void createNewUser() {
        lastNewUser = DataFaker.createNewFakeUser();

        userApi = new UserApi(lastNewUser.username(), lastNewUser.email(), lastNewUser.password());
        userApi.registerUser();
    }

    public NewUser getLastNewUser() {
        return lastNewUser;
    }
}
