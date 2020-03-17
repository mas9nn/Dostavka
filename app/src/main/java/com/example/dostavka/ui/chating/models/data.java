package com.example.dostavka.ui.chating.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class data {
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("created_at")
    @Expose
    private String created_at;

    public data(String from, String to, String message, String created_at) {
        this.from = from;
        this.to = to;
        this.message = message;
        this.created_at = created_at;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
