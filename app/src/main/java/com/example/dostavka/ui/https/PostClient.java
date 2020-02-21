package com.example.dostavka.ui.https;

import com.example.dostavka.ui.home.models.RestaurantCategoryModel;
import com.example.dostavka.ui.home.models.RestaurantResponse;
import com.example.dostavka.ui.login.models.UserResponse;
import com.google.gson.JsonElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostClient {
    private static final String Base_URL = "https://c60c8091.ngrok.io/api/";
    private PostInterface postInterface;
    private static PostClient INSTANCE;

    public PostClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postInterface = retrofit.create(PostInterface.class);
    }

    public static PostClient getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new PostClient();
        }
        return INSTANCE;
    }

    public Call<UserResponse> login(String email, String password) {
        return postInterface.login(email, password);
    }

    public Call<UserResponse> createUser(String name, String email, String password) {
        return postInterface.createUser(email, name, password);
    }
    public Call<UserResponse> createUserOther(String name, String email) {
        return postInterface.createUserOther(email, name);
    }

    public Call<RestaurantResponse> getAllShops(String token, String id) {
        return postInterface.getAllShops(token, id);
    }
}
