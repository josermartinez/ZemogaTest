package co.zemoga.www.zemogatest.di;

import co.zemoga.www.zemogatest.ui.activities.MainActivity;
import co.zemoga.www.zemogatest.ui.fragments.AllPostFragment;
import co.zemoga.www.zemogatest.ui.fragments.DetailFragment;
import co.zemoga.www.zemogatest.ui.fragments.FavoriteFragment;
import dagger.Component;

@ZemogaScope
@Component(modules = ZemogaModule.class)
public interface ZemogaComponent {

    void inject(MainActivity mainActivity);

    void inject(AllPostFragment allPostFragment);

    void inject(DetailFragment detailFragment);

    void inject(FavoriteFragment favoriteFragment);
}
