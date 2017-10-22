package com.servpal.android.api;

import com.servpal.android.model.LoginResponse;
import com.servpal.android.model.Message;
import com.servpal.android.model.UserBody;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServpalService {

    @GET("api/user/{user_id}")
    Call<UserBody> getUser(@Path("user_id") int userId);

    @FormUrlEncoded
    @POST("api/user")
    Call<Message> createAccount(@Field("role") String role, @Field("email") String email, @Field("password") String password,
                                @Field("firstName") String firstName, @Field("lastName") String lastName, @Field("mobile") boolean mobile); // sends empty body?

    @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password, @Field("mobile") boolean mobile);
}
