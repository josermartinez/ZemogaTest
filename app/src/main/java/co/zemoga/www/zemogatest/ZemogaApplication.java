package co.zemoga.www.zemogatest;

import android.app.Application;

import co.zemoga.www.zemogatest.di.DaggerZemogaComponent;
import co.zemoga.www.zemogatest.di.ZemogaComponent;
import co.zemoga.www.zemogatest.di.ZemogaComponentProvider;
import co.zemoga.www.zemogatest.di.ZemogaModule;

public class ZemogaApplication extends Application implements ZemogaComponentProvider {

    private ZemogaComponent zemogaComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        zemogaComponent = DaggerZemogaComponent.builder()
                .zemogaModule(new ZemogaModule(this))
                .build();
    }

    @Override
    public ZemogaComponent getZemogaComponent() {
        return zemogaComponent;
    }
}
