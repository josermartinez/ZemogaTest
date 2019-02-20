package co.zemoga.www.zemogatest.ui.adapters.delegates;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import co.zemoga.www.zemogatest.R;
import co.zemoga.www.zemogatest.model.SectionTitle;

public class TitleDelegate implements BaseDelegate<TitleDelegate.ViewHolder, SectionTitle> {

    @Override
    public TitleDelegate.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(TitleDelegate.ViewHolder viewHolder, SectionTitle item) {
        viewHolder.bind(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;

        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.delegate_section_title, parent, false));

            titleTextView = (TextView) itemView;
        }

        public void bind(SectionTitle item) {
            titleTextView.setText(item.getTitle());
        }
    }
}
