package co.zemoga.www.zemogatest.model;

import co.zemoga.www.zemogatest.callbacks.RecyclerViewType;
import co.zemoga.www.zemogatest.ui.adapters.delegates.ViewType;

public class SectionTitle implements RecyclerViewType {

    private int title;

    public SectionTitle(int title) {
        this.title = title;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    @Override
    public int getViewType() {
        return ViewType.SECTION_TITLE.ordinal();
    }
}
