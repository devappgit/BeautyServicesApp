package com.beautyservices.android.model;


import android.support.annotation.NonNull;

import com.squareup.moshi.Moshi;

import java.io.IOException;

import timber.log.Timber;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    private int roleID;
    private String role;

    private String createdAt;
    private String updatedAt;

    private String accountKey;
    private String customerKey;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getRoleID() {
        return roleID;
    }

    public String getRole() {
        return role;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public String getCustomerKey() {
        return customerKey;
    }

    static User fromJson(@NonNull String jsonString) {
        if (jsonString.isEmpty()) {
            return null;
        }
        Moshi moshi = new Moshi.Builder().build();
        try {
            return moshi.adapter(User.class).fromJson(jsonString);
        } catch (IOException e) {
            Timber.e(e);
            return null;
        }
    }

    static String toJson(@NonNull User user) {
        Moshi moshi = new Moshi.Builder().build();
        return moshi.adapter(User.class).toJson(user);
    }
}
