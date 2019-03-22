package mfec.example.danupol.githubcontact.ContactList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import mfec.example.danupol.githubcontact.R;
import mfec.example.danupol.githubcontact.User;

public class ContactListViewAdapter extends ArrayAdapter<User> {
    private LayoutInflater layoutInflater;
    private int resource;
    private List<User> users;

    public ContactListViewAdapter(@NonNull Context context, int resource, @NonNull List<User> users) {
        super(context, resource, users);
        this.layoutInflater = LayoutInflater.from(context);
        this.resource = resource;
        this.users = users;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = layoutInflater.inflate(resource, parent, false);
        }
        User user = users.get(position);

        TextView name = convertView.findViewById(R.id.user_name);
        ImageView imageView = convertView.findViewById(R.id.user_image);

        name.setText(user.getLogin());
        Glide.with(convertView)
                .load(user.getAvatarUrl())
                .apply(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
                .into(imageView);

        return convertView;
    }
}
