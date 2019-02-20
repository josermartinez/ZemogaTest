package co.zemoga.www.zemogatest.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import javax.inject.Inject;

import co.zemoga.www.zemogatest.R;
import co.zemoga.www.zemogatest.callbacks.OnPostClickListener;
import co.zemoga.www.zemogatest.database.entities.Post;
import co.zemoga.www.zemogatest.di.ZemogaComponent;
import co.zemoga.www.zemogatest.di.ZemogaComponentProvider;
import co.zemoga.www.zemogatest.ui.adapters.PostTabPagerAdapter;
import co.zemoga.www.zemogatest.viewmodel.PostViewModel;
import co.zemoga.www.zemogatest.viewmodel.PostViewModelFactory;

public class MainActivity extends AppCompatActivity implements
        OnPostClickListener {

    private ViewPager viewPager;
    private PostTabPagerAdapter postTabPagerAdapter;

    @Inject
    PostViewModelFactory postViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ZemogaComponent zemogaComponent = ((ZemogaComponentProvider) getApplication())
                .getZemogaComponent();
        zemogaComponent.inject(this);

        PostViewModel postViewModel = ViewModelProviders.of(this, postViewModelFactory).get(PostViewModel.class);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        ImageView refreshButton = findViewById(R.id.refresh);
        TabLayout tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        postTabPagerAdapter = new PostTabPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(postTabPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        floatingActionButton.setOnClickListener(v -> postViewModel.removeAllData());
        refreshButton.setOnClickListener(v -> postViewModel.refreshData());

    }

    @Override
    public void onPostClicked(Post item) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetailActivity.DETAIL_ARG_KEY, item);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
