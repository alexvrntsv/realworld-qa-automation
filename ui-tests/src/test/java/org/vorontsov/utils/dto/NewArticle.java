package org.vorontsov.utils.dto;

import java.util.List;

public record NewArticle(
        String title,
        String description,
        String body,
        List<String> tagList
) {}