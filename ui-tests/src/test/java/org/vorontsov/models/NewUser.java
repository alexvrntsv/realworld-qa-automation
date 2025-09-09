package org.vorontsov.models;

public record NewUser(
        String username,
        String email,
        String password) {}