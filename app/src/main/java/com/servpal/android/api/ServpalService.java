package com.servpal.android.api;

import com.servpal.android.model.LoginResponse;
import com.servpal.android.model.Message;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServpalService {

    @FormUrlEncoded
    @POST("api/user")
    Call<Message> createAccount(@Field("role") String role, @Field("email") String email, @Field("password") String password,
                                @Field("firstName") String firstName, @Field("lastName") String lastName, @Field("mobile") boolean mobile); // sends empty body?

    @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password, @Field("mobile") boolean mobile);
}
