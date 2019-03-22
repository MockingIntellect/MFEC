package mfec.example.danupol.githubcontact.ContactDetail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;

import mfec.example.danupol.githubcontact.R;
import mfec.example.danupol.githubcontact.User;

public class ContactDetailFragment extends Fragment {

    private static final String KEY_USERNAME = "USERID";
    private ContactDetailViewModel mViewModel;
    private String username;

    private ImageView profImageView;
    private TextView usernameTextview;
    private TextView displaynameTextview;
    private TextView followingsTextview;
    private TextView followersTextView;
    private TextView bioTextView;
    private TextView locationTextView;
    private TextView urlTextView;

    public static ContactDetailFragment newInstance(String username) {
        ContactDetailFragment fragment = new ContactDetailFragment();
        Bundle args = new Bundle();
        args.putString(KEY_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            username = getArguments().getString(KEY_USERNAME);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_detail_fragment, container, false);
        profImageView = view.findViewById(R.id.prof_image);
        usernameTextview = view.findViewById(R.id.username_textview);
        displaynameTextview = view.findViewById(R.id.displayname_textview);
        followingsTextview = view.findViewById(R.id.followings_textview);
        followersTextView = view.findViewById(R.id.followers_textview);
        bioTextView = view.findViewById(R.id.userbio_textview);
        locationTextView = view.findViewById(R.id.userlocation_textview);
        urlTextView = view.findViewById(R.id.userurl_textview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(ContactDetailViewModel.class);
        mViewModel.getContact(username);
        observeData();
    }

    public void observeData(){
        mViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if(user != null) {
                    String displayname = String.format("@%s", user.getLogin());
                    String followings = String.format("Following: %s", user.getFollowing());
                    String followers = String.format("Followers: %s", user.getFollowers());
                    String bio = String.format("Bio: %s",user.getBio());
                    String location = String.format("Location: %s", user.getLocation());
                    String url = String.format("URL: %s", user.getHtml_url());

                    Glide.with(ContactDetailFragment.this)
                            .load(user.getAvatarUrl())
                            .apply(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
                            .into(profImageView);

                    usernameTextview.setText(user.getName());
                    displaynameTextview.setText(displayname);
                    followingsTextview.setText(followings);
                    followersTextView.setText(followers);
                    bioTextView.setText(bio);
                    locationTextView.setText(location);
                    urlTextView.setText(url);
                }
            }
        });
    }
}
