package co.zemoga.www.zemogatest.ui.adapters.delegates;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import co.zemoga.www.zemogatest.R;
import co.zemoga.www.zemogatest.callbacks.RecyclerViewType;

public class LoaderDelegate implements BaseDelegate<LoaderDelegate.ViewHolder, RecyclerViewType> {

    @Override
    public LoaderDelegate.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(LoaderDelegate.ViewHolder viewHolder, RecyclerViewType item) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.delegate_loader, parent, false));
        }

    }
}

