package co.zemoga.www.zemogatest.model;

import co.zemoga.www.zemogatest.callbacks.RecyclerViewType;
import co.zemoga.www.zemogatest.ui.adapters.delegates.ViewType;

public class Empty implements RecyclerViewType {

    private int resourceMessage;

    public Empty(int resourceMessage) {
        this.resourceMessage = resourceMessage;
    }

    public int getResourceMessage() {
        return resourceMessage;
    }

    public void setResourceMessage(int resourceMessage) {
        this.resourceMessage = resourceMessage;
    }

    @Override
    public int getViewType() {
        return ViewType.NO_DATA.ordinal();
    }
}
