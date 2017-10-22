package com.servpal.android;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.servpal.android.model.Session;

import timber.log.Timber;

public class ServpalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        AndroidThreeTen.init(this);

        Session.initialize(this);
    }
}
