package co.zemoga.www.zemogatest.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import co.zemoga.www.zemogatest.R;
import co.zemoga.www.zemogatest.database.entities.Post;
import co.zemoga.www.zemogatest.ui.fragments.DetailFragment;

public class DetailActivity extends BaseActivity {

    public final static String DETAIL_ARG_KEY = "detailArg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        addFragment(DetailFragment.newInstance((Post) getIntent().getExtras().get(DETAIL_ARG_KEY)),
                R.id.detail_container);
    }
}
