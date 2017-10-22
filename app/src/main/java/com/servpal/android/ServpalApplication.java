package com.servpal.android;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.servpal.android.model.Session;
import com.servpal.android.utils.ProductionTree;

import timber.log.Timber;

public class ServpalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ProductionTree());
        }

        AndroidThreeTen.init(this);

        Session.initialize(this);
    }
}
