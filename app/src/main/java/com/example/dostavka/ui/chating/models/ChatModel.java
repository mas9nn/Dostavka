package com.example.dostavka.ui.chating.models;

import com.example.dostavka.ui.login.models.User_param;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatModel {
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<data> data = null;

    public ChatModel(boolean success, String message, List<com.example.dostavka.ui.chating.models.data> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
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

    public List<com.example.dostavka.ui.chating.models.data> getData() {
        return data;
    }

    public void setData(List<com.example.dostavka.ui.chating.models.data> data) {
        this.data = data;
    }
}
