package com.example.instagram_clone;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {
    /** Debug Tag*/
    private static final String TAG = "SignupActivity";

    /** UI References*/
    private EditText    signupUsername  ;
    private EditText    composedPassword;
    private EditText    signupEmail     ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        bind();
        createUser();
    } // onCreate

    private void bind(){
        signupUsername      = findViewById(R.id.signupUsername   );
        composedPassword    = findViewById(R.id.composedPassword );
        signupEmail         = findViewById(R.id.signupEmail      );
        Button signupBtn    = findViewById(R.id.signupBtn        );
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

    }

    private void goMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    void createUser(){

        String  signupUsernameTxt   = signupUsername.getText().toString()   ;
        String  composedPasswordTxt = composedPassword.getText().toString() ;
        String  signupEmailTxt      = signupEmail.getText().toString()      ;

        ParseUser user = new ParseUser();
        user.setUsername(signupUsernameTxt);
        user.setPassword(composedPasswordTxt);
        user.setEmail(signupEmailTxt);

        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    goMainActivity();
                }
                else {
                    Log.e(TAG, "Issue with SignUp");
                    e.printStackTrace();
                    return;
                }
            }
        });
    }
    /** Menu Methods*/
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
            ParseUser.logOut();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

} //Class
