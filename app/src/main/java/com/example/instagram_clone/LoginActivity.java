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
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

} //Class

