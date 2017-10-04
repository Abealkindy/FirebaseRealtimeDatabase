package com.rosinante24.firebaserealtimedatabase;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Rosinante24 on 04/10/17.
 */
@IgnoreExtraProperties

public class User {
    public String username;
    public String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
