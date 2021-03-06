package com.example.instagram_clone.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.instagram_clone.Models.Post;
import com.example.instagram_clone.Models.User;
import com.example.instagram_clone.R;
import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    /**Adapter Variables*/
    private Context context;
    private List <Post> posts;

    public PostsAdapter(Context context, List <Post> posts){
        this.context = context;
        this.posts   = posts  ;
    } // Adapter Constructor

    /**Implemented Methods*/
    @NonNull
    @Override
    /* Creates One Individual Row*/
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, viewGroup, false);
        return new ViewHolder(view);
    }
    /* Binds the date */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Post post = posts.get(position);
        viewHolder.bind(post);
    }
    /* How many items are in our data-set*/
    @Override
    public int getItemCount() {
        return posts.size();
    }

    /* Within the RecyclerView.Adapter class */

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }
    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        /**UI References*/
        private  TextView  tvName        ;
        private  TextView  tvDescription ;
        private  TextView  createdAt     ;
        private  ImageView tvPostImg     ;
        private  ImageView userProfilePic;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            getViews(itemView);

        } // ViewHolder Constructor
        private void getViews(View view){
            tvName          = view.findViewById(R.id.tvName            );
            tvDescription   = view.findViewById(R.id.tvDescription     );
            tvPostImg       = view.findViewById(R.id.tvPostImg         );
            createdAt       = view.findViewById(R.id.createdAt         );
            userProfilePic  = view.findViewById(R.id.userProfilePicture);
        } // Get Views

        private void bind (Post post) {

            tvName.setText(post.getUser().getUsername());
            tvDescription.setText(post.getDescription());
            createdAt.setText((post.getCreatedAt()).toString());

            String imgURL = post.getImage().getUrl();
            ParseFile profileImg = post.getUser().getParseFile(User.getProfileImageKey());
            String placeholder = "drawable/faimage";
            if (imgURL != null)
                Glide.with(context)
                        .load(imgURL)
                        .apply(new RequestOptions().override(0, 0))
                        .apply(new RequestOptions().fitCenter())
                        .into(tvPostImg);

            if (profileImg != null)
                Glide.with(context)
                        .load(profileImg.getUrl())
                        .apply(new RequestOptions().override(150, 150))
                        .into(userProfilePic);
            else {
                Glide.with(context)
                        .load(profileImg)
                        .apply(new RequestOptions().placeholder(R.color.black).centerCrop())
                        .into(userProfilePic);
            }
        } // Bind
    } // ViewHolder Class
} // Posts Adapter Class
