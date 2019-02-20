package co.zemoga.www.zemogatest.ui.adapters.delegates;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import co.zemoga.www.zemogatest.R;
import co.zemoga.www.zemogatest.model.PostComment;

public class CommentDelegate implements BaseDelegate<CommentDelegate.ViewHolder, PostComment> {

    @Override
    public CommentDelegate.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(CommentDelegate.ViewHolder viewHolder, PostComment item) {
        viewHolder.bind(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView commentTextView;

        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.delegate_comment, parent, false));

            commentTextView = itemView.findViewById(R.id.comment_textView);
        }

        public void bind(PostComment item) {
            commentTextView.setText(item.getBody());
        }
    }
}
