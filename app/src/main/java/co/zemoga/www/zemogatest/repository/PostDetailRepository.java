package co.zemoga.www.zemogatest.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import co.zemoga.www.zemogatest.database.ZemogaDatabase;
import co.zemoga.www.zemogatest.database.entities.Post;
import co.zemoga.www.zemogatest.model.PostComment;
import co.zemoga.www.zemogatest.model.PostDetail;
import co.zemoga.www.zemogatest.model.UserInfo;
import co.zemoga.www.zemogatest.remote.ZemogaClient;
import retrofit2.Response;

public class PostDetailRepository {

    private final ZemogaClient zemogaClient;
    private final ZemogaDatabase zemogaDatabase;

    public PostDetailRepository(ZemogaClient zemogaClient, ZemogaDatabase zemogaDatabase) {
        this.zemogaClient = zemogaClient;
        this.zemogaDatabase = zemogaDatabase;
    }

    public LiveData<Resource<PostDetail>> getPostDetail(Post post) {
        MutableLiveData<Resource<PostDetail>> resourceMutableLiveData = new MutableLiveData<>();
        resourceMutableLiveData.setValue(Resource.loading(null));
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                PostDetail postDetail = new PostDetail();
                postDetail.setDescription(post.getBody());
                Response<UserInfo> userInfoResponse = zemogaClient.getUserInfo(post.getUserId());
                if (!userInfoResponse.isSuccessful()) {
                    resourceMutableLiveData.postValue(Resource.error(userInfoResponse.message(), null));
                    return;
                }
                postDetail.setUserInfo(userInfoResponse.body());

                Response<List<PostComment>> commentsResponse = zemogaClient.getComments(post.getId());
                if (!commentsResponse.isSuccessful()) {
                    resourceMutableLiveData.postValue(Resource.error(commentsResponse.message(), null));
                    return;
                }
                postDetail.setPostCommentList(commentsResponse.body());
                resourceMutableLiveData.postValue(Resource.success(postDetail));
            } catch (IOException e) {
                resourceMutableLiveData.postValue(Resource.error(e.getMessage(), null));
            }
        });
        return resourceMutableLiveData;
    }

    public void favoriteItem(int postId, boolean checked) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> zemogaDatabase.postDao().updateFavoriteState(checked, postId));
    }

    public void updateReadState(int id) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            zemogaDatabase.postDao().updateReadState(id);
        });
    }
}
