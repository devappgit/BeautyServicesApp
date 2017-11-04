package com.servpal.android.api;

import android.os.Build;
import android.support.annotation.NonNull;

import com.servpal.android.BuildConfig;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ServpalMobileApiInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        // Note: Some endpoints require mobile=true parameter for RESTful mobile Servpal API
        // If you want to, you can intercept POST calls (check with request.method()) and append mobile=true urlencoded field
        // https://stackoverflow.com/questions/34791244/retrofit2-modifying-request-body-in-okhttp-interceptor

        // Constant X-Requested-With required for RESTful Servpal API
        builder.addHeader("X-Requested-With", "XMLHttpRequest");

        // https://developers.google.com/app-conversion-tracking/api/request-response-specs
        // Servpal/3.4.4 (Android 7.1.2; en_US; Nexus5X; Build/N2G48C)
        String analyticsUserAgent = "Servpal/" + BuildConfig.VERSION_NAME +
                " (" +
                "Android " + Build.VERSION.RELEASE + "; " +
                Locale.getDefault() + "; " +
                Build.MODEL + "; Build/" + Build.ID +
                ")";
        builder.addHeader("User-Agent", analyticsUserAgent);

        // want Content-Type or Accept? Seems like it's not needed

        return chain.proceed(builder.build());
    }
}
