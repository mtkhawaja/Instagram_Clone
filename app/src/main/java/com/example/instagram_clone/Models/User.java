package com.example.instagram_clone.Models;

import com.parse.ParseClassName;
import com.parse.ParseUser;
@ParseClassName("User")
public class User extends ParseUser {

    private static final String KEY_PROFILE_IMAGE = "profilePicture"      ;
    public static String getProfileImageKey() {
        return KEY_PROFILE_IMAGE ;
    }

}// Class
