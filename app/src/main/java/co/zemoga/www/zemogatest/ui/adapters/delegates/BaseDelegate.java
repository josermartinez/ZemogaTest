package co.zemoga.www.zemogatest.ui.adapters.delegates;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import co.zemoga.www.zemogatest.callbacks.RecyclerViewType;

public interface BaseDelegate<VH extends RecyclerView.ViewHolder, T extends RecyclerViewType> {

    VH onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(VH viewHolder, T item);

}