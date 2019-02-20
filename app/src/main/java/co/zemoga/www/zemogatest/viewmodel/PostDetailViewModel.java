package co.zemoga.www.zemogatest.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import co.zemoga.www.zemogatest.database.entities.Post;
import co.zemoga.www.zemogatest.model.PostDetail;
import co.zemoga.www.zemogatest.repository.PostDetailRepository;
import co.zemoga.www.zemogatest.repository.Resource;

public class PostDetailViewModel extends ViewModel {

    private final PostDetailRepository postDetailRepository;
    private final MediatorLiveData<Resource<PostDetail>> resourcePostDetailLiveData;
    private LiveData<Resource<PostDetail>> postDetailLiveData;

    public PostDetailViewModel(PostDetailRepository postDetailRepository) {
        this.postDetailRepository = postDetailRepository;

        this.resourcePostDetailLiveData = new MediatorLiveData<>();
    }

    public void setData(Post post) {
        postDetailRepository.updateReadState(post.getId());
        resourcePostDetailLiveData.removeSource(postDetailLiveData);
        postDetailLiveData = postDetailRepository.getPostDetail(post);
        resourcePostDetailLiveData.addSource(postDetailLiveData, resourcePostDetailLiveData::setValue);
    }

    public LiveData<Resource<PostDetail>> getPostDetailLiveData() {
        return resourcePostDetailLiveData;
    }

    public void favoriteItem(int postId, boolean checked){
        postDetailRepository.favoriteItem(postId, checked);
    }
}
