package org.vorontsov.utils;

import org.vorontsov.api.clients.ArticleApi;
import org.vorontsov.api.clients.UserApi;
import org.vorontsov.models.NewArticle;
import org.vorontsov.models.NewUser;

import java.util.List;

public class Seeder {

    public static NewUser createNewUser() {
        NewUser user = DataFaker.createNewFakeUser();

        UserApi userApi = new UserApi(user);
        userApi.registerUser();

        return user;
    }


    public static NewArticle createNewArticle(NewUser user) {
        NewArticle article = DataFaker.createNewFakeArticle();

        ArticleApi api = new ArticleApi(user);

        api.createNewArticle(article);

        return article;
    }

    public static NewArticle createNewArticleWithTags(NewUser user, List<String> tagList) {
        NewArticle article = DataFaker.createNewFakeArticleWithTags(tagList);

        ArticleApi api = new ArticleApi(user);

        api.createNewArticle(article);

        return article;
    }

    public static String createNewComment(NewUser user, NewArticle article) {
        String comment = DataFaker.createComment();

        ArticleApi api = new ArticleApi(user);

        api.createCommentToArticle(article.title(), comment);

        return comment;
    }

    public static NewArticle createAndLikeArticle(NewUser user) {
        NewArticle article = DataFaker.createNewFakeArticle();
        ArticleApi api = new ArticleApi(user);

        api.createNewArticle(article);
        api.likeArticle(article.title());

        return article;
    }

}
