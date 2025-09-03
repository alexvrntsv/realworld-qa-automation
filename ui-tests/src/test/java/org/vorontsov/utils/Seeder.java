package org.vorontsov.utils;

import org.vorontsov.utils.api.ArticleApi;
import org.vorontsov.utils.api.UserApi;
import org.vorontsov.utils.dto.NewArticle;
import org.vorontsov.utils.dto.NewUser;

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

    public static String createNewComment(NewUser user, NewArticle article) {
        String comment = DataFaker.createComment();

        ArticleApi api = new ArticleApi(user);

        api.createCommentToArticle(article.title(), comment);

        return comment;
    }

}
