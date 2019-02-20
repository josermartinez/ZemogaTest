package co.zemoga.www.zemogatest.viewmodel;

import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import co.zemoga.www.zemogatest.repository.PostsRepository;

public class PostViewModelFactory implements ViewModelProvider.Factory {

    private final PostsRepository postsRepository;

    public PostViewModelFactory(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @NonNull
    @Override
    public PostViewModel create(@NonNull Class modelClass) {
        return new PostViewModel(postsRepository);
    }
}
