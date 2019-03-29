package com.example.instagram_clone.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instagram_clone.Adapters.PostsAdapter;
import com.example.instagram_clone.Models.Post;
import com.example.instagram_clone.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {

    /**UI References*/
    protected RecyclerView    rvPosts;
    /** Adapter */
    protected PostsAdapter    adapter;
    /** Posts */
    protected List <Post>     mPosts;
    /**Debug/Log Variable */
    private String      TAG        = "POSTS_FRAGMENT";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvPosts = view.findViewById(R.id.rvPosts);
        /* Create the data source */
        mPosts  = new ArrayList<>();

        /*  Create the Adapter */
        adapter = new PostsAdapter(getContext(), mPosts);

        /* Set the adapter on the layout manager.*/
        rvPosts.setAdapter(adapter);

        /* set the layout manager on the recycler view.*/
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();
    }

    private void queryPosts(){
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.KEY_USER);
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
    }// queryPosts
} // Class


