package com.servpal.android.model;


import com.squareup.moshi.Json;

public class LoginResult {

    @Json(name = "user")
    private UserBody userBody;

    public UserBody getBody() {
        return this.userBody;
    }

    // LoginResult is a UserBody wrapped in "user".  UserBody has its "user" object
//    Body: {
//        "user": { // UserBody obj
//            "user": { //User obj
//
//            }
//        }
//    }
}
