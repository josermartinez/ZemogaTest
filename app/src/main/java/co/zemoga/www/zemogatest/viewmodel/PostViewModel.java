package co.zemoga.www.zemogatest.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import co.zemoga.www.zemogatest.database.entities.Post;
import co.zemoga.www.zemogatest.repository.PostsRepository;
import co.zemoga.www.zemogatest.repository.Resource;

public class PostViewModel extends ViewModel {

    private final PostsRepository postsRepository;

    private final MediatorLiveData<Resource<List<Post>>> postDataMediatorLiveData;
    private LiveData<Resource<List<Post>>> postRepositoryDataLiveData;
    private final LiveData<List<Post>> favoriteItemsLiveData;
    private final LiveData<List<Post>> allItemsLiveData;

    public PostViewModel(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
        postDataMediatorLiveData = new MediatorLiveData<>();
        favoriteItemsLiveData = postsRepository.getFavoritesItems();
        allItemsLiveData = postsRepository.getAllItems();
    }

    public void refreshData() {
        addDataSources();
    }

    private void addDataSources() {
        postDataMediatorLiveData.removeSource(postRepositoryDataLiveData);
        postRepositoryDataLiveData = postsRepository.getPostsData();
        postDataMediatorLiveData.addSource(postRepositoryDataLiveData, listResource ->
                postDataMediatorLiveData.setValue(listResource));

    }

    public LiveData<Resource<List<Post>>> getPostDataMediatorLiveData() {
        return postDataMediatorLiveData;
    }

    public LiveData<List<Post>> getFavoriteItemsLiveData() {
        return favoriteItemsLiveData;
    }

    public LiveData<List<Post>> getAllItemsLiveData() {
        return allItemsLiveData;
    }

    public void removeAllData() {
        postsRepository.removeAllData();
    }

    public void removeItem(int position) {
        postsRepository.removeItem(allItemsLiveData.getValue().get(position).getId());
    }

    public void removeFavoriteItem(int position) {
        postsRepository.removeItem(favoriteItemsLiveData.getValue().get(position).getId());
    }
}
