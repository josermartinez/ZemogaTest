package co.zemoga.www.zemogatest.remote;

import java.util.List;

import co.zemoga.www.zemogatest.database.entities.Post;
import co.zemoga.www.zemogatest.model.PostComment;
import co.zemoga.www.zemogatest.model.UserInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ZemogaService {

    @GET
    Call<List<Post>> getPosts(@Url String path);

    @GET
    Call<UserInfo> getUserInfo(@Url String path);

    @GET
    Call<List<PostComment>> getComments(@Url String path, @Query("origin") int postId);
}
