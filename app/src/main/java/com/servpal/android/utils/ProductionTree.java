package com.servpal.android.utils;


import timber.log.Timber;

public class ProductionTree extends Timber.Tree {
    @Override
    protected void log(int priority, String tag, String message, Throwable t) {

        // by default ProductionTree logs nothing to logcat

        // Add non-fatal crash logging here e.g. Crashlytics
//        if (priority > Log.WARN) {
//            // log your non-fatal crash
//            Crashlytics.log(Log.ERROR, tag, message);
//            if (t != null) {
//                Crashlytics.logException(t);
//            } else {
//                Crashlytics.logException(new Throwable(message));
//            }
//        }
    }
}
