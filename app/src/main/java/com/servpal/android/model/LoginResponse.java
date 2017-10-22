package com.servpal.android.model;


import com.squareup.moshi.Json;

public class LoginResponse {

    @Json(name = "user")
    private UserBody userBody;

    public UserBody getBody() {
        return this.userBody;
    }

    // LoginResponse is a UserBody wrapped in "user".  UserBody has its "user" object
//    Body: {
//        "user": { // UserBody obj
//            "user": { //User obj
//
//            }
//        }
//    }
}
