package com.example.dostavka.ui.home.models;

import com.example.dostavka.ui.login.models.User_param;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestaurantResponse {
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<RestaurantCategoryModel> data = null;
    public RestaurantResponse(boolean success, String message, List<RestaurantCategoryModel> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public RestaurantResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }



    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RestaurantCategoryModel> getData() {
        return data;
    }

    public void setData(List<RestaurantCategoryModel> data) {
        this.data = data;
    }
}
