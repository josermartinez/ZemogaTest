package co.zemoga.www.zemogatest.ui.adapters.delegates;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import co.zemoga.www.zemogatest.R;
import co.zemoga.www.zemogatest.model.SectionText;

public class SectionTextDelegate implements BaseDelegate<SectionTextDelegate.ViewHolder, SectionText> {

    @Override
    public SectionTextDelegate.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(SectionTextDelegate.ViewHolder viewHolder, SectionText item) {
        viewHolder.bind(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView sectionTextView;

        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.delegate_text, parent, false));

            sectionTextView = (TextView) itemView;
        }

        public void bind(SectionText item) {
            sectionTextView.setText(item.getText());
        }
    }
}
