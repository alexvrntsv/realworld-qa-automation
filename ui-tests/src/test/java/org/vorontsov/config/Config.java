package org.vorontsov.config;

public class Config {
    public static final String BASE_URL = System.getenv().getOrDefault("BASE_URL", "http://localhost:3000");
    public static final String API_URL = System.getenv().getOrDefault("API_URL", "http://localhost:8080");
}
