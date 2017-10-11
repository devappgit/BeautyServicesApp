package com.servpal.android.model;


import com.squareup.moshi.Json;

public class LoginResponse {

    @Json(name = "user")
    private Wrapper wrapper;

    public User getUser() {
        return this.wrapper.user;
    }

    public static class Wrapper {
        User user;
        // Profile
        // Business
        // Avail
        // Pictures
        // account?
        // Reviews
    }

//    Wrapper exists because JSON is nested as
//    {
//        "user": {
//            "user": {
//                // attributes
//            }
//        }
//    }
}
