package com.example.instagram_clone.Fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.example.instagram_clone.Models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {
    /**Debug/Log Variable */
    private String      TAG        = "PROFILE_FRAGMENT";

    public ProfileFragment() {
        super();
    }

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
        super.adapter.clear();
        queryPosts();
        super.swipeContainer.setRefreshing(false);
    }
}//  Class
