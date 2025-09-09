package org.vorontsov.models;

import java.util.List;

public record NewArticle(
        String title,
        String description,
        String body,
        List<String> tagList
) {
    public NewArticle {
        if (tagList == null) {
            tagList = List.of();
        }
    }
}
