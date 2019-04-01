package com.example.instagram_clone.Models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post extends ParseObject {
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IMAGE       = "image"      ;
    public static final String KEY_USER        = "user"        ;
    public static final String KEY_CREATED_AT  = "createdAt"   ;


    /** Getters and Setters */

    public  String getDescription() {
        return getString(KEY_DESCRIPTION);
    }
    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile image) {
         put(KEY_IMAGE, image);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
         put(KEY_USER, user);
    }

    public static String getKeyCreatedAt() {
        return KEY_CREATED_AT;
    }

} // Class
