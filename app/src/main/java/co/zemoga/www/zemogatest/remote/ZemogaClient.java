package co.zemoga.www.zemogatest.remote;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import co.zemoga.www.zemogatest.database.entities.Post;
import co.zemoga.www.zemogatest.model.PostComment;
import co.zemoga.www.zemogatest.model.UserInfo;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZemogaClient {

    private final static String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private final static String POST_PATH = "posts";
    private final static String USER_INFO_PATH = "users";
    private final static String COMMENTS_PATH = "users";
    private final static long TIMEOUT = TimeUnit.SECONDS.toMillis(20);

    private ZemogaService zemogaService;

    public ZemogaClient() {
        this.zemogaService = createService();
    }

    private ZemogaService createService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        return retrofit.create(ZemogaService.class);
    }

    private OkHttpClient buildOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().
                connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS);
        return builder.build();
    }

    public void getPosts(Callback callback) {
        Call<List<Post>> call = zemogaService.getPosts(POST_PATH);
        call.enqueue(callback);
    }

    public Response<UserInfo> getUserInfo(int userId) throws IOException {
        String path = String.format("%s/%d", USER_INFO_PATH, userId);
        Call<UserInfo> call = zemogaService.getUserInfo(path);
        return call.execute();
    }

    public Response<List<PostComment>> getComments(int postId) throws IOException {
        Call<List<PostComment>> call = zemogaService.getComments(COMMENTS_PATH, postId);
        return call.execute();
    }
}
