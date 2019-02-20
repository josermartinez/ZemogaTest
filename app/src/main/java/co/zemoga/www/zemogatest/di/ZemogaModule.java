package co.zemoga.www.zemogatest.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import co.zemoga.www.zemogatest.database.ZemogaDatabase;
import co.zemoga.www.zemogatest.remote.ZemogaClient;
import co.zemoga.www.zemogatest.repository.PostDetailRepository;
import co.zemoga.www.zemogatest.repository.PostsRepository;
import co.zemoga.www.zemogatest.viewmodel.PostDetailViewModelFactory;
import co.zemoga.www.zemogatest.viewmodel.PostViewModelFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class ZemogaModule {

    private final Application application;

    public ZemogaModule(Application application) {
        this.application = application;
    }

    @ZemogaScope
    @Provides
    Context provideContext() {
        return application.getApplicationContext();
    }

    @ZemogaScope
    @Provides
    PostsRepository providePostsRepository(ZemogaClient zemogaClient, ZemogaDatabase zemogaDatabase) {
        return new PostsRepository(zemogaClient, zemogaDatabase);
    }

    @ZemogaScope
    @Provides
    ZemogaClient provideZemogaClient() {
        return new ZemogaClient();
    }

    @Provides
    PostViewModelFactory providePostViewModelFactory(PostsRepository postsRepository) {
        return new PostViewModelFactory(postsRepository);
    }

    @ZemogaScope
    @Provides
    ZemogaDatabase provideZemogaDatabase(Context context) {
        return Room.databaseBuilder(context, ZemogaDatabase.class, ZemogaDatabase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @ZemogaScope
    @Provides
    PostDetailRepository providePostDetailRepository(ZemogaClient zemogaClient, ZemogaDatabase zemogaDatabase) {
        return new PostDetailRepository(zemogaClient, zemogaDatabase);
    }

    @Provides
    PostDetailViewModelFactory providePostDetailViewModelFactory(PostDetailRepository postDetailRepository) {
        return new PostDetailViewModelFactory(postDetailRepository);
    }
}
