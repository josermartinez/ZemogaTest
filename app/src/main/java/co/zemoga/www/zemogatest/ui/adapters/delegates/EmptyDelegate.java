package co.zemoga.www.zemogatest.ui.adapters.delegates;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import co.zemoga.www.zemogatest.R;
import co.zemoga.www.zemogatest.model.Empty;

public class EmptyDelegate implements BaseDelegate<EmptyDelegate.ViewHolder, Empty> {

    @Override
    public EmptyDelegate.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(EmptyDelegate.ViewHolder viewHolder, Empty item) {
        viewHolder.bind(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView messageTextView;

        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.delegate_empty, parent, false));

            messageTextView = (TextView) itemView;

        }

        public void bind(Empty item) {
            messageTextView.setText(itemView.getResources().getString(item.getResourceMessage()));
        }
    }
}

