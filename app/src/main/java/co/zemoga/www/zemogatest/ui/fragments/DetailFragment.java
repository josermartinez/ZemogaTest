package co.zemoga.www.zemogatest.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import javax.inject.Inject;

import co.zemoga.www.zemogatest.R;
import co.zemoga.www.zemogatest.database.entities.Post;
import co.zemoga.www.zemogatest.di.ZemogaComponent;
import co.zemoga.www.zemogatest.di.ZemogaComponentProvider;
import co.zemoga.www.zemogatest.model.PostDetail;
import co.zemoga.www.zemogatest.repository.Resource;
import co.zemoga.www.zemogatest.repository.Status;
import co.zemoga.www.zemogatest.ui.adapters.PostDetailAdapter;
import co.zemoga.www.zemogatest.viewmodel.PostDetailViewModel;
import co.zemoga.www.zemogatest.viewmodel.PostDetailViewModelFactory;

public class DetailFragment extends Fragment {

    private final static String POST_ARG_KEY = "postArg";

    private RecyclerView recyclerView;

    private PostDetailAdapter adapter;
    private Post post;
    private PostDetailViewModel postDetailViewModel;

    @Inject
    public PostDetailViewModelFactory postDetailViewModelFactory;

    public static DetailFragment newInstance(Post post) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(POST_ARG_KEY, post);
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            post = (Post) arguments.get(POST_ARG_KEY);
        }

        ZemogaComponent zemogaComponent = ((ZemogaComponentProvider) getActivity().getApplication())
                .getZemogaComponent();
        zemogaComponent.inject(this);

        postDetailViewModel = ViewModelProviders.of(this, postDetailViewModelFactory).get(PostDetailViewModel.class);
        adapter = new PostDetailAdapter();

        postDetailViewModel.getPostDetailLiveData().observe(this, this::processPostDetailResourceResult);

        postDetailViewModel.setData(post);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CheckBox checkBox = view.findViewById(R.id.favorite_checkbox);
        checkBox.setChecked(post.isFavorite());

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) ->
                postDetailViewModel.favoriteItem(post.getId(), isChecked));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void processPostDetailResourceResult(Resource<PostDetail> postDetailResource) {
        if (Status.SUCCESS.equals(postDetailResource.status)) {
            adapter.setData(postDetailResource.data);
        } else if (Status.LOADING.equals(postDetailResource.status)) {
            adapter.showLoader();
        } else {
            adapter.setData(null);
        }
    }
}
