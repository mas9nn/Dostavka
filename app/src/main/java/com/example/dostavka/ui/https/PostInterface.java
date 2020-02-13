package com.example.dostavka.ui.https;

import com.example.dostavka.ui.login.models.UserResponse;
import com.google.gson.JsonElement;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PostInterface {
    @POST("login")
    @FormUrlEncoded
    Call<UserResponse> login(@Field("email") String email, @Field("password") String password);
    @POST("createUser")
    @FormUrlEncoded
    Call<UserResponse> createUser(@Field("email") String email,@Field("name") String name, @Field("password") String password);
}
