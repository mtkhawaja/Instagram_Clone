package com.example.instagram_clone.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.instagram_clone.Adapters.PostsAdapter;
import com.example.instagram_clone.Models.Post;
import com.example.instagram_clone.Models.User;
import com.example.instagram_clone.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends PostsFragment {
    /**Debug/Log Variable */
    private String      TAG        = "PROFILE_FRAGMENT";
    /**UI References*/
    protected TextView  profileUsername;
    protected TextView  profileUserBio ;
    protected ImageView profileImage   ;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        userProfileBind(view);
        recyclerViewConfig(view);
        queryPosts();
        swipeConfig(view);
    } // onViewCreated

    @Override
    protected void queryPosts() {
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.KEY_USER);
        postQuery.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        int POST_LIMIT = 20;
        postQuery.setLimit(POST_LIMIT);
        postQuery.addDescendingOrder(Post.KEY_CREATED_AT);
        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null)
                    Log.e(TAG, "Error with Query");
                Post temp;
                mPosts.addAll(posts);
                adapter.notifyDataSetChanged();
                for(int i = 0; i < posts.size();i++){
                    temp = posts.get(i);
                    Log.d(TAG, "POST = " + temp.getDescription()
                            + " Username = " + temp.getUser());
                }
            }
        });
    }

    @Override
    protected void refreshHomeTimeLine() {
        adapter.clear();
        queryPosts();
        swipeContainer.setRefreshing(false);
    }

    @Override
    protected void swipeConfig(View view) {
        swipeContainer =   view.findViewById(R.id.profileSwipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshHomeTimeLine();
            }
        });
    }

    @Override
    protected void recyclerViewConfig(View view) {
        super.rvPosts = view.findViewById(R.id.profilePosts);
        /* Create the data source */
        super.mPosts  = new ArrayList<>();

        /*  Create the Adapter */
        super.adapter = new PostsAdapter(getContext(), super.mPosts);

        /* Set the adapter on the layout manager.*/
        super.rvPosts.setAdapter(super.adapter);

        /* set the layout manager on the recycler view.*/
        super.rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    protected void userProfileBind(View view){
        profileUsername =view.findViewById(R.id.profileUsername);
        profileUserBio = view.findViewById(R.id.profileUserBio);
        profileImage = view.findViewById(R.id.profileImage);
        profileUsername.setText(ParseUser.getCurrentUser().getUsername());
        profileUserBio.setText("Just a test for now, but one day users will be able to add their biographies here");
        profileImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "One day the user will be able to add their profile picture",Toast.LENGTH_SHORT).show();
            }
        });

        ParseFile profileImgURL = ParseUser.getCurrentUser().getParseFile(User.getProfileImageKey());

        if (profileImgURL != null )
            Glide.with(view.getContext())
                    .load(profileImgURL.getUrl())
                    .apply(new RequestOptions().override(300, 500))
                    .into(profileImage);
        else
        Glide.with(view.getContext())
                .load("@drawable/faimage")
                .apply(new RequestOptions().placeholder(R.color.black).centerCrop())
                .into(profileImage);

    }

}//  Class
