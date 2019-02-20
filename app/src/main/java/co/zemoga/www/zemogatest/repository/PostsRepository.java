package co.zemoga.www.zemogatest.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import co.zemoga.www.zemogatest.database.ZemogaDatabase;
import co.zemoga.www.zemogatest.database.entities.Post;
import co.zemoga.www.zemogatest.remote.ZemogaClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsRepository {

    private final ZemogaClient zemogaClient;
    private final ZemogaDatabase zemogaDatabase;

    public PostsRepository(ZemogaClient zemogaClient, ZemogaDatabase zemogaDatabase) {
        this.zemogaClient = zemogaClient;
        this.zemogaDatabase = zemogaDatabase;
    }

    public LiveData<Resource<List<Post>>> getPostsData() {
        final MutableLiveData<Resource<List<Post>>> resourceMutableLiveData = new MutableLiveData<>();
        resourceMutableLiveData.setValue(Resource.loading(null));
        final Executor executor = Executors.newSingleThreadExecutor();
        zemogaClient.getPosts(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                executor.execute(() -> {
                    if (!response.isSuccessful()) {
                        resourceMutableLiveData.postValue(Resource.error(null, null));
                    }

                    List<Post> body = (List<Post>) response.body();
                    zemogaDatabase.postDao().insertAll(body);
                    zemogaDatabase.postDao().changeReadState(20);
                    resourceMutableLiveData.postValue(Resource.success(body));
                });
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                resourceMutableLiveData.setValue(Resource.error(t.getMessage(), null));
            }
        });
        return resourceMutableLiveData;
    }

    public LiveData<List<Post>> getAllItems() {
        return zemogaDatabase.postDao().getPostList();
    }

    public LiveData<List<Post>> getFavoritesItems() {
        return zemogaDatabase.postDao().getFavoritePostList();
    }

    public void removeAllData() {
        final Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> zemogaDatabase.postDao().deleteAll());
    }

    public void removeItem(int postId) {
        final Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> zemogaDatabase.postDao().deleteItem(postId));
    }
}
