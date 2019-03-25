package com.example.instagram_clone;
import android.app.Application;
import com.parse.Parse;

public class ParseApplication extends Application {
    private static final String appId     = "mtkhawaja-instagram-codepath";
    private static final String clientKey = "myMasterKeyIsASecretThatNoOneWillEverKnow";
    private static final String serverURL = "http://mtkhawaja-instagram-codepath.herokuapp.com/parse";
    @Override
    public void onCreate() {
        super.onCreate();

        // set applicationId, and server server based on the values in the Heroku settings.
        // clientKey is not needed unless explicitly configured
        // any network interceptors must be added with the Configuration Builder given this syntax

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(appId) // should correspond to APP_ID env variable
                .clientKey(clientKey)  // set explicitly unless clientKey is explicitly configured on Parse server
                .server(serverURL).build());
    }
}
