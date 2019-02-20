package co.zemoga.www.zemogatest.ui.adapters.delegates;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import co.zemoga.www.zemogatest.R;
import co.zemoga.www.zemogatest.model.CommentSectionTitle;

public class CommentTitleDelegate implements BaseDelegate<CommentTitleDelegate.ViewHolder, CommentSectionTitle> {

    @Override
    public CommentTitleDelegate.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(CommentTitleDelegate.ViewHolder viewHolder, CommentSectionTitle item) {
        viewHolder.bind(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;

        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.delegate_comment_section_title, parent, false));

            titleTextView = (TextView) itemView;
        }

        public void bind(CommentSectionTitle item) {
            titleTextView.setText(item.getTitle());
        }
    }
}
