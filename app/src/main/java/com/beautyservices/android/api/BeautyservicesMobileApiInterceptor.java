package com.beautyservices.android.api;

import android.os.Build;
import android.support.annotation.NonNull;

import com.beautyservices.android.BuildConfig;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This is an {@link okhttp3.Interceptor} for Beautyservices API
 * A rundown of how an interceptor works: https://github.com/square/okhttp/wiki/Interceptors
 * This interceptor will inject a header required for REST api to work and a useful User-Agent header
 */
public class BeautyservicesMobileApiInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        // Note: Some endpoints require mobile=true parameter for RESTful mobile Beautyservices API
        // If you want to, you can intercept POST calls (check with request.method()) and append mobile=true urlencoded field
        // https://stackoverflow.com/questions/34791244/retrofit2-modifying-request-body-in-okhttp-interceptor

        // Constant X-Requested-With required for RESTful Beautyservices API
        builder.addHeader("X-Requested-With", "XMLHttpRequest");

        // https://developers.google.com/app-conversion-tracking/api/request-response-specs
        // Beautyservices/0.1.0 (Android 7.1.2; en_US; Nexus5X; Build/N2G48C)
        String analyticsUserAgent = "Beautyservices/" + BuildConfig.VERSION_NAME +
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
