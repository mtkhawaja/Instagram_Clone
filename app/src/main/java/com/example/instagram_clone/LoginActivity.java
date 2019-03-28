package com.example.instagram_clone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /** Debug Tag*/
    private static final String TAG = "LoginActivity";

    /** UI References */
    private EditText             username;
    private EditText             password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if(persistenceCheck())
            goMainActivity();
        setContentView(R.layout.activity_login);
        bind();

        /** Tool Bar Code */
        // Find the toolbar view inside the activity layout
        Toolbar toolbar =  findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

    }// OnCreate

    private void bind(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Button register = findViewById(R.id.register);
        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();
                login(usernameTxt, passwordTxt);
            }
        });
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(v.getContext(), SignupActivity.class);
                startActivity(i);
                finish();
            }
        });


    } //Bind

    private  void login(String username, String password){
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                //TO-DO: Useful Exception Handling
                if (e != null){
                    Log.e(TAG, "Issue with Login");
                    e.printStackTrace();
                    return;
                }
                goMainActivity();
            } //done
        });
    } // login

    private void goMainActivity(){
        Log.d(TAG, "Navigating to main Activity");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private boolean persistenceCheck(){
        ParseUser currentUser = ParseUser.getCurrentUser();
        return (currentUser != null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Tapped on Compose Icon

        /*
        if(item.getItemId() == R.id.instagram_logo_action){
            // Navigate to new activities.
            return true;
        }*/


        if(item.getItemId() == R.id.logout){
            finishAndRemoveTask();
        }
        return super.onOptionsItemSelected(item);
    }
} //Class

