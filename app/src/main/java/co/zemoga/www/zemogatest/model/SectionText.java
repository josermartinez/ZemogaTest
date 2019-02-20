package co.zemoga.www.zemogatest.model;

import co.zemoga.www.zemogatest.callbacks.RecyclerViewType;
import co.zemoga.www.zemogatest.ui.adapters.delegates.ViewType;

public class SectionText implements RecyclerViewType {

    private String text;

    public SectionText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int getViewType() {
        return ViewType.SECTION_TEXT.ordinal();
    }
}
