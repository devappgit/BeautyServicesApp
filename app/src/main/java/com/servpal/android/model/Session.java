package com.servpal.android.model;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class Session {

    private final static String prefs_file = "Servpal_Prefs";
    private final static String USER = "user";
    private static SharedPreferences prefs;

    private static User user;

    public static void initialize(@NonNull Context context) {
        prefs = context.getSharedPreferences(prefs_file, Context.MODE_PRIVATE);

        user = User.fromJson(prefs.getString(USER, ""));
    }

    public static User user() {
        return user;
    }

    public static void persist(User networkUser) {
        user = networkUser;

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER, User.toJson(user));
        editor.apply();
    }

    public static boolean clear() {
        user = null;
        return prefs.edit().clear().commit();
    }
}
