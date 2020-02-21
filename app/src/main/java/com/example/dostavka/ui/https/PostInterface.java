package com.example.dostavka.ui.https;

import com.example.dostavka.ui.home.models.RestaurantCategoryModel;
import com.example.dostavka.ui.home.models.RestaurantResponse;
import com.example.dostavka.ui.login.models.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostInterface {
    @POST("login")
    @FormUrlEncoded
    Call<UserResponse> login(@Field("email") String email, @Field("password") String password);

    @POST("createUser")
    @FormUrlEncoded
    Call<UserResponse> createUser(@Field("email") String email, @Field("name") String name, @Field("password") String password);

    @POST("createUserOther")
    @FormUrlEncoded
    Call<UserResponse> createUserOther(@Field("email") String email, @Field("name") String name);

    @GET("getAllShops")
    Call<RestaurantResponse> getAllShops(@Query("token") String token, @Query("id") String id);
}
