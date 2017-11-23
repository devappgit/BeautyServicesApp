package com.servpal.android;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.servpal.android.model.Session;
import com.servpal.android.utils.ProductionTree;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class ServpalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.Builder builder = new Fabric.Builder(this)
                .kits(
                        new Crashlytics.Builder().core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()).build()
                );
        Fabric.with(builder.build());

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ProductionTree());
        }

        AndroidThreeTen.init(this);

        Session.initialize(this);
    }
}
