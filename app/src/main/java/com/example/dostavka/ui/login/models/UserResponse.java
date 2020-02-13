package com.example.dostavka.ui.login.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private User_param data = null;
    public UserResponse(boolean success, String message, User_param data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public UserResponse(boolean success, String message) {
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

    public User_param getData() {
        return data;
    }

    public void setData(User_param data) {
        this.data = data;
    }
}
