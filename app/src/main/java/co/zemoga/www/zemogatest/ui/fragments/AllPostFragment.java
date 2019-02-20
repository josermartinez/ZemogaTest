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

public class AllPostFragment extends Fragment implements
        PostDelegate.OnInteractionListener {

    private RecyclerView recyclerView;

    private PostAdapter adapter;
    private OnPostClickListener onPostClickListener;
    private PostViewModel postViewModel;

    @Inject
    PostViewModelFactory postViewModelFactory;

    public static AllPostFragment newInstance() {
        return new AllPostFragment();
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

        postViewModel = ViewModelProviders.of(this, postViewModelFactory).get(PostViewModel.class);
        postViewModel.getAllItemsLiveData().observe(this, posts -> adapter.setData(posts));
        /*postViewModel.getPostDataMediatorLiveData().observe(this, listResource -> {
            if (Status.SUCCESS.equals(listResource.status)) {
                //adapter.setData(listResource.data);
            }
        });*/
        postViewModel.refreshData();

        adapter = new PostAdapter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_post, container, false);
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
                postViewModel.removeItem(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onItemClicked(Post item) {
        onPostClickListener.onPostClicked(item);
    }
}
