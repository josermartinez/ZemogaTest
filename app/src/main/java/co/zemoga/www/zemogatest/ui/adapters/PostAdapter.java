package co.zemoga.www.zemogatest.ui.adapters;

import android.support.v4.app.Fragment;

import java.util.List;

import co.zemoga.www.zemogatest.R;
import co.zemoga.www.zemogatest.database.entities.Post;
import co.zemoga.www.zemogatest.model.Empty;
import co.zemoga.www.zemogatest.ui.adapters.delegates.EmptyDelegate;
import co.zemoga.www.zemogatest.ui.adapters.delegates.PostDelegate;
import co.zemoga.www.zemogatest.ui.adapters.delegates.ViewType;

public class PostAdapter extends BaseAdapter {

    public PostAdapter(Fragment fragment) {
        delegateArray.put(ViewType.POST_VIEW.ordinal(), new PostDelegate((PostDelegate.OnInteractionListener) fragment));
        delegateArray.put(ViewType.NO_DATA.ordinal(), new EmptyDelegate());
    }

    public void setData(List<Post> postList) {
        items.clear();
        if (postList != null && postList.size() > 0) {
            items.addAll(postList);
        } else {
            items.add(new Empty(R.string.no_data_found_text));
        }
        notifyDataSetChanged();
    }

}
