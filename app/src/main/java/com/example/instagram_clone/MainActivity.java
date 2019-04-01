package com.example.instagram_clone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.instagram_clone.Fragments.ComposeFragment;
import com.example.instagram_clone.Fragments.PostsFragment;
import com.example.instagram_clone.Fragments.ProfileFragment;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    /**Debug/Log Variable */
    private String      TAG        = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bind();
        setBottomNavigationView();

        /** Tool Bar Code */
        // Find the toolbar view inside the activity layout
        Toolbar toolbar =  findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
    } // On Create

    /** Bottom Nav */

    private void setBottomNavigationView(){
        /**Bottom Navigation Variables*/
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            final FragmentManager fragmentManager = getSupportFragmentManager();
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                final Fragment composeFragment = new ComposeFragment();
                final Fragment postsFragment   = new PostsFragment  ();
                final Fragment profileFragment = new ProfileFragment();
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        fragmentManager.beginTransaction().replace(R.id.flContainer,postsFragment).commit();
                        break;
                    case R.id.action_compose:
                        fragmentManager.beginTransaction().replace(R.id.flContainer,composeFragment).commit();
                        break;
                    case R.id.action_profile:
                        fragmentManager.beginTransaction().replace(R.id.flContainer,profileFragment).commit();
                        break;
                    default:
                        return true;
                }
                return true;
            } // onNavigationItemSelected
        }); //setOnNavigationItemSelectedListener
        // Default view
        bottomNavigationView.setSelectedItemId(R.id.action_home);
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

            if(item.getItemId() == R.id.logout){
                ParseUser.logOut();
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
            }
            return super.onOptionsItemSelected(item);
        } //onOptionsItemSelected

}// Class
