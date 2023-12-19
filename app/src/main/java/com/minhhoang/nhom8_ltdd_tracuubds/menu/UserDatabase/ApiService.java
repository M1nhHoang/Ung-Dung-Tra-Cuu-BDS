package com.minhhoang.nhom8_ltdd_tracuubds.menu.UserDatabase;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("users/")
    Call<Void> registerUser(@Body User user);

    @GET("user/auth/")
    Call<User> auth(@Query("username") String username, @Query("password") String password);
}
