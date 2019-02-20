package co.zemoga.www.zemogatest.ui.adapters.delegates;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import co.zemoga.www.zemogatest.R;
import co.zemoga.www.zemogatest.database.entities.Post;

public class PostDelegate implements BaseDelegate<PostDelegate.ViewHolder, Post> {

    private OnInteractionListener listener;

    public PostDelegate(OnInteractionListener listener) {
        this.listener = listener;
    }

    @Override
    public PostDelegate.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(PostDelegate.ViewHolder viewHolder, Post item) {
        viewHolder.bind(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView unreadIcon;
        private TextView titleTextView;
        private CheckBox checkBox;

        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.delegate_post_view, parent, false));

            unreadIcon = itemView.findViewById(R.id.unread_icon);
            titleTextView = itemView.findViewById(R.id.title_textView);
            checkBox = itemView.findViewById(R.id.favorite_checkbox);
        }

        public void bind(Post item) {
            unreadIcon.setVisibility(item.isRead() ? View.INVISIBLE : View.VISIBLE);
            titleTextView.setText(item.getTitle());
            checkBox.setChecked(item.isFavorite());
            checkBox.setVisibility(item.isFavorite() ? View.VISIBLE : View.INVISIBLE);
            checkBox.setEnabled(false);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClicked(item);
                }
            });
        }
    }

    public interface OnInteractionListener {
        void onItemClicked(Post item);
    }
}
