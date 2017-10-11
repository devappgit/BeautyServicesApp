package com.servpal.android.api;

import com.servpal.android.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServpalService {

    @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password, @Field("mobile") boolean mobile);

    @GET("api/user")
    Call<Void> createAccount();
}
