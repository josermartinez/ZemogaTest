package co.zemoga.www.zemogatest.ui.adapters;

import co.zemoga.www.zemogatest.R;
import co.zemoga.www.zemogatest.model.CommentSectionTitle;
import co.zemoga.www.zemogatest.model.Loader;
import co.zemoga.www.zemogatest.model.PostDetail;
import co.zemoga.www.zemogatest.model.SectionText;
import co.zemoga.www.zemogatest.model.SectionTitle;
import co.zemoga.www.zemogatest.ui.adapters.delegates.CommentDelegate;
import co.zemoga.www.zemogatest.ui.adapters.delegates.CommentTitleDelegate;
import co.zemoga.www.zemogatest.ui.adapters.delegates.LoaderDelegate;
import co.zemoga.www.zemogatest.ui.adapters.delegates.SectionTextDelegate;
import co.zemoga.www.zemogatest.ui.adapters.delegates.TitleDelegate;
import co.zemoga.www.zemogatest.ui.adapters.delegates.UserInformationDelegate;
import co.zemoga.www.zemogatest.ui.adapters.delegates.ViewType;

public class PostDetailAdapter extends BaseAdapter {

    public PostDetailAdapter() {
        delegateArray.put(ViewType.SECTION_TITLE.ordinal(), new TitleDelegate());
        delegateArray.put(ViewType.SECTION_TEXT.ordinal(), new SectionTextDelegate());
        delegateArray.put(ViewType.USER_INFORMATION.ordinal(), new UserInformationDelegate());
        delegateArray.put(ViewType.COMMENT_SECTION_TITLE.ordinal(), new CommentTitleDelegate());
        delegateArray.put(ViewType.COMMENT.ordinal(), new CommentDelegate());
        delegateArray.put(ViewType.LOADER.ordinal(), new LoaderDelegate());
    }

    public void setData(PostDetail postDetail) {
        items.clear();
        if (postDetail != null) {
            items.add(new SectionTitle(R.string.description_post_detail_title));
            items.add(new SectionText(postDetail.getDescription()));
            items.add(new SectionTitle(R.string.user_post_detail_title));
            items.add(postDetail.getUserInfo());
            items.add(new CommentSectionTitle(R.string.comments_post_detail_title));
            items.addAll(postDetail.getPostCommentList());
        }
        notifyDataSetChanged();
    }

    public void showLoader() {
        items.clear();
        items.add(new Loader());
        notifyDataSetChanged();
    }
}
