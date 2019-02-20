package co.zemoga.www.zemogatest.model;

import co.zemoga.www.zemogatest.callbacks.RecyclerViewType;
import co.zemoga.www.zemogatest.ui.adapters.delegates.ViewType;

public class Loader implements RecyclerViewType {

    @Override
    public int getViewType() {
        return ViewType.LOADER.ordinal();
    }
}
