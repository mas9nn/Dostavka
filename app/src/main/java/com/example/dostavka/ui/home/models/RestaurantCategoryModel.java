package com.example.dostavka.ui.home.models;

public class RestaurantCategoryModel {
    String image,name,min,rating,price;

    public RestaurantCategoryModel() {
    }

    public RestaurantCategoryModel(String image, String name, String min, String rating, String price) {
        this.image = image;
        this.name = name;
        this.min = min;
        this.rating = rating;
        this.price = price;
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

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
