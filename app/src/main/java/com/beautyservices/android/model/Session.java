package com.beautyservices.android.model;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.beautyservices.android.BuildConfig;

public class Session {

    private final static String prefs_file = "Beautyservices_Prefs";
    private final static String prefs_file_dev = "Beautyservices_Prefs_Dev";

    private final static String USER = "user";
    private static SharedPreferences prefs;

    private static User user;

    @SuppressWarnings("ConstantConditions")
    private static String getPrefsForConfig() {
        return BuildConfig.DEV ? prefs_file : prefs_file_dev;
    }

    public static void initialize(@NonNull Context context) {
        if (prefs == null) {
            prefs = context.getSharedPreferences(getPrefsForConfig(), Context.MODE_PRIVATE);
        }
        user = User.fromJson(prefs.getString(USER, ""));
    }

    public static User user() {
        return user;
    }

    public static void persist(User networkUser) {
        user = networkUser;
        prefs.edit().putString(USER, User.toJson(user)).apply();
    }

    public static boolean clear() {
        user = null;
        return prefs.edit().clear().commit();
    }
}
