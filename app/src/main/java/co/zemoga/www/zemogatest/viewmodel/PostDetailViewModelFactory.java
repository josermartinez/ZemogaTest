package co.zemoga.www.zemogatest.viewmodel;

import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import co.zemoga.www.zemogatest.repository.PostDetailRepository;

public class PostDetailViewModelFactory implements ViewModelProvider.Factory {

    private final PostDetailRepository postDetailRepository;

    public PostDetailViewModelFactory(PostDetailRepository postDetailRepository) {
        this.postDetailRepository = postDetailRepository;
    }

    @NonNull
    @Override
    public PostDetailViewModel create(@NonNull Class modelClass) {
        return new PostDetailViewModel(postDetailRepository);
    }
}
