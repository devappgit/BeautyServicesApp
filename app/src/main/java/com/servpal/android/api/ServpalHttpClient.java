package com.servpal.android.api;


import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.servpal.android.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * A class that holds a singleton {@link OkHttpClient} and singleton {@link Retrofit} services
 *
 * Note the addition of a {@link ServpalMobileApiInterceptor},
 * which adds X-Requested-With and User-Agent headers on all requests going through the ApiService
 *
 * Try not to use the OkHttpClient directly, instead create
 * Service interfaces like {@link ApiService}
 */
public class ServpalHttpClient {

    public static String baseUrl() {
        return BuildConfig.DEBUG ? "https://dev.servpal.com/" : "https://servpal.com/";
    }

    private static LoggingInterceptor prettyLoggger() {
        return new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .tag("Network")
                .build();
    }

    private static HttpLoggingInterceptor basicLogger() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BASIC : HttpLoggingInterceptor.Level.NONE);
        return logger;
    }

    private static OkHttpClient client;
    private static synchronized OkHttpClient getClient() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build();
        }
        return client;
    }

    private static Retrofit retrofit;
    private static synchronized Retrofit getRetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .client(getClient().newBuilder()
                            .addInterceptor(new ServpalMobileApiInterceptor())    // add servpal X-Requested-With & User-Agent headers
                            .addInterceptor(prettyLoggger())
                            .build())
                    .build();
        }
        return retrofit;
    }

    public static ApiService getService() {
        return getRetrofitClient().create(ApiService.class);
    }
}
