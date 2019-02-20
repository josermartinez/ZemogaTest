package co.zemoga.www.zemogatest.ui.adapters.delegates;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import co.zemoga.www.zemogatest.R;
import co.zemoga.www.zemogatest.model.UserInfo;

public class UserInformationDelegate implements BaseDelegate<UserInformationDelegate.ViewHolder, UserInfo> {

    @Override
    public UserInformationDelegate.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(UserInformationDelegate.ViewHolder viewHolder, UserInfo item) {
        viewHolder.bind(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView emailTextView;
        private TextView phoneTextView;
        private TextView websiteTextView;

        public ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.delegate_user_information, parent, false));

            nameTextView = itemView.findViewById(R.id.name_textView);
            emailTextView = itemView.findViewById(R.id.email_textView);
            phoneTextView = itemView.findViewById(R.id.phone_textView);
            websiteTextView = itemView.findViewById(R.id.website_textView);
        }

        public void bind(UserInfo item) {
            nameTextView.setText(item.getName());
            emailTextView.setText(item.getEmail());
            phoneTextView.setText(item.getPhone());
            websiteTextView.setText(item.getWebsite());
        }
    }
}
