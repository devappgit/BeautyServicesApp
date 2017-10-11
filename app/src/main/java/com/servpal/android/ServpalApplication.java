package com.servpal.android;

import android.app.Application;

import timber.log.Timber;

public class ServpalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
