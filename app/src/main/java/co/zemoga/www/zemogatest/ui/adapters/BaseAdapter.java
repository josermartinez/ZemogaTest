package co.zemoga.www.zemogatest.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import co.zemoga.www.zemogatest.callbacks.RecyclerViewType;
import co.zemoga.www.zemogatest.ui.adapters.delegates.BaseDelegate;

public class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected SparseArrayCompat<BaseDelegate> delegateArray;
    protected List<RecyclerViewType> items;

    public BaseAdapter() {
        delegateArray = new SparseArrayCompat<>();
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return delegateArray.get(viewType).onCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        delegateArray.get(getItemViewType(position)).onBindViewHolder(holder, items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }
}