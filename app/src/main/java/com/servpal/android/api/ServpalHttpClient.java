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

public class ServpalHttpClient {

    private static LoggingInterceptor prettyLoggger() {
        return new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .tag("Network")
                .build();
    }

    private static HttpLoggingInterceptor JakeWharton() {
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
                    .baseUrl("https://dev.servpal.com/")    //TODO: Before release change to Production URL
                    .addConverterFactory(MoshiConverterFactory.create())
                    .client(getClient().newBuilder()
                            .addInterceptor(new HeaderInterceptor())    // add servpal X-Requested-With header
                            .addInterceptor(prettyLoggger())
                            .build())
                    .build();
        }
        return retrofit;
    }

    public static ServpalService getService() {
        return getRetrofitClient().create(ServpalService.class);
    }
}
