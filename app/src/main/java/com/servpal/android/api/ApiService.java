package com.servpal.android.api;

import com.servpal.android.model.LoginResult;
import com.servpal.android.model.SearchResult;
import com.servpal.android.model.UserBody;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("api/login")
    Call<LoginResult> login(@Field("email") String email, @Field("password") String password, @Field("mobile") boolean mobile);

    @FormUrlEncoded
    @POST("api/user")
    Call<UserBody> createAccount(@Field("role") String role, @Field("email") String email, @Field("password") String password,
                                @Field("firstName") String firstName, @Field("lastName") String lastName, @Field("mobile") boolean mobile); // sends empty body?

    @GET("api/user/{user_id}")
    Call<UserBody> getUser(@Path("user_id") int userId);

    @GET("professionals/find/{page}")
    Call<SearchResult> findProfessionals(@Path("page") int page, @Query("search") String query);
}
