package com.servpal.android.model;


import com.squareup.moshi.Json;

public class LoginResponse {

    @Json(name = "user")
    private UserBody userBody;

    public UserBody getBody() {
        return this.userBody;
    }
}
