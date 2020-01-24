package com.example.dostavka.ui.home.models;

public class HomeCategoryModel {
    String image,name;

    public HomeCategoryModel() {
    }

    public HomeCategoryModel(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
