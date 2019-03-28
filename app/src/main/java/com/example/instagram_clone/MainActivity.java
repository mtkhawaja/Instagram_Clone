package com.example.instagram_clone;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.instagram_clone.Models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**Debug/Log Variable */
    private String      TAG        = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bind();
        setBottomNavigationView();
        //  queryPosts();
    } // On Create

    /** Bottom Nav */

    private void setBottomNavigationView(){
        /**Bottom Navigation Variables*/
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        // do something here
                        break;

                    case R.id.action_compose:
                        // do something here
                        break;
                    case R.id.action_profile:
                        // do something here
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    } // Set Bottom Navigation View.



    /** Menu Methods*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        } // onCreateOptionsMenu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
            // Tapped on Compose Icon
            /*
            if(item.getItemId() == R.id.instagram_logo_action){
                // Navigate to new activities.
                return true;
            }*/

            if(item.getItemId() == R.id.logout){
                ParseUser.logOut();
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
            }
            return super.onOptionsItemSelected(item);
        } //onOptionsItemSelected

}// Class
