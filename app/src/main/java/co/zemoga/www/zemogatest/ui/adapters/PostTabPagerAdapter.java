package co.zemoga.www.zemogatest.ui.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.zemoga.www.zemogatest.R;
import co.zemoga.www.zemogatest.ui.fragments.AllPostFragment;
import co.zemoga.www.zemogatest.ui.fragments.FavoriteFragment;

public class PostTabPagerAdapter extends FragmentStatePagerAdapter {

    private final static int PAGE_COUNT = 2;
    private final Context context;

    public PostTabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return AllPostFragment.newInstance();
        } else {
            return FavoriteFragment.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.all_post_tab_title);
        } else {
            return context.getString(R.string.favorite_post_tab_title);
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
