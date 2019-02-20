package co.zemoga.www.zemogatest.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import co.zemoga.www.zemogatest.R;
import co.zemoga.www.zemogatest.callbacks.OnPostClickListener;
import co.zemoga.www.zemogatest.database.entities.Post;
import co.zemoga.www.zemogatest.di.ZemogaComponent;
import co.zemoga.www.zemogatest.di.ZemogaComponentProvider;
import co.zemoga.www.zemogatest.ui.adapters.PostAdapter;
import co.zemoga.www.zemogatest.ui.adapters.delegates.PostDelegate;
import co.zemoga.www.zemogatest.viewmodel.PostViewModel;
import co.zemoga.www.zemogatest.viewmodel.PostViewModelFactory;

public class FavoriteFragment extends Fragment implements
        PostDelegate.OnInteractionListener {

    private PostAdapter adapter;
    private PostViewModel postViewModel;
    private OnPostClickListener onPostClickListener;

    private RecyclerView recyclerView;

    @Inject
    PostViewModelFactory postViewModelFactory;

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPostClickListener) {
            onPostClickListener = (OnPostClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement OnPostClickListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ZemogaComponent zemogaComponent = ((ZemogaComponentProvider) getActivity().getApplication())
                .getZemogaComponent();
        zemogaComponent.inject(this);

        adapter = new PostAdapter(this);

        postViewModel = ViewModelProviders.of(this, postViewModelFactory).get(PostViewModel.class);
        postViewModel.getFavoriteItemsLiveData().observe(this, posts -> adapter.setData(posts));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                postViewModel.removeFavoriteItem(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onItemClicked(Post item) {
        onPostClickListener.onPostClicked(item);
    }
}
