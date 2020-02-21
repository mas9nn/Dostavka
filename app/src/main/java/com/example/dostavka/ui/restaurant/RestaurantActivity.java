package com.example.dostavka.ui.restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dostavka.R;
import com.example.dostavka.databinding.ActivityRestaurantBinding;
import com.example.dostavka.ui.home.adapters.CategoryRecyclerViewAdapter;
import com.example.dostavka.ui.home.adapters.RestaurantRecyclerViewAdapter;
import com.example.dostavka.ui.home.models.HomeCategoryModel;
import com.example.dostavka.ui.home.models.RestaurantCategoryModel;
import com.example.dostavka.ui.restaurant.adapters.EdaRecyclerViewAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RestaurantActivity extends AppCompatActivity {

    ActivityRestaurantBinding binding;
    RestaurantViewModel model;
    List<HomeCategoryModel> categoryModels = new ArrayList<>();
    List<RestaurantCategoryModel> restaurantCategoryModels = new ArrayList<>();
    private BottomSheetBehavior sheetBehavior;
    private LinearLayout bottom_sheet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity__restaurant);
        model = new ViewModelProvider(this).get(RestaurantViewModel.class);
        binding.cardViewRestaurant.setBackgroundResource(R.drawable.shape_card_topcorners);
        binding.setViewmodel(model);

        Picasso.get().load("https://astana.restolife.kz/upload/information_system_30/1/7/7/item_17738/information_items_property_20439.jpg").into(binding.headerImg);

        initRecyclerPopular();
        initSalatsRecycler();

        View v = binding.getRoot();

        final Dialog dialog = new BottomSheetDialog(this);
        View sheetView = this.getLayoutInflater().inflate(R.layout.bottom_sheet_restaurant, null);
        dialog.setContentView(sheetView);
        CardView cardView = dialog.findViewById(R.id.card_of_calendar);
        Objects.requireNonNull(cardView).setBackgroundResource(R.drawable.shape_card_topcorners);
        binding.categoryBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.oplata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }

    private void initSalatsRecycler() {
//        restaurantCategoryModels.add(new RestaurantCategoryModel("https://astana.restolife.kz/upload/information_system_30/1/7/7/item_17738/information_items_property_20439.jpg", "Restaurant", "10-20 min", "4.8", "3000"));
//        restaurantCategoryModels.add(new RestaurantCategoryModel("https://astana.restolife.kz/upload/information_system_30/1/7/7/item_17738/information_items_property_20439.jpg", "Restaurant", "10-20 min", "4.8", "3000"));
//        restaurantCategoryModels.add(new RestaurantCategoryModel("https://astana.restolife.kz/upload/information_system_30/1/7/7/item_17738/information_items_property_20439.jpg", "Restaurant", "10-20 min", "4.8", "3000"));
//        restaurantCategoryModels.add(new RestaurantCategoryModel("https://astana.restolife.kz/upload/information_system_30/1/7/7/item_17738/information_items_property_20439.jpg", "Restaurant", "10-20 min", "4.8", "3000"));
//        restaurantCategoryModels.add(new RestaurantCategoryModel("https://astana.restolife.kz/upload/information_system_30/1/7/7/item_17738/information_items_property_20439.jpg", "Restaurant", "10-20 min", "4.8", "3000"));
//        restaurantCategoryModels.add(new RestaurantCategoryModel("https://astana.restolife.kz/upload/information_system_30/1/7/7/item_17738/information_items_property_20439.jpg", "Restaurant", "10-20 min", "4.8", "3000"));
//        EdaRecyclerViewAdapter categoryAdapter = new EdaRecyclerViewAdapter(restaurantCategoryModels, this);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        binding.salatsRecycler.setLayoutManager(layoutManager);
//        binding.salatsRecycler.setNestedScrollingEnabled(false);
//        binding.salatsRecycler.setAdapter(categoryAdapter);
    }

    private void initRecyclerPopular() {
        categoryModels.add(new HomeCategoryModel("https://live.staticflickr.com/1739/42363610931_cc333ba7de_b.jpg", "salat"));
        categoryModels.add(new HomeCategoryModel("https://live.staticflickr.com/1739/42363610931_cc333ba7de_b.jpg", "salat"));
        categoryModels.add(new HomeCategoryModel("https://live.staticflickr.com/1739/42363610931_cc333ba7de_b.jpg", "salat"));
        categoryModels.add(new HomeCategoryModel("https://live.staticflickr.com/1739/42363610931_cc333ba7de_b.jpg", "salat"));
        categoryModels.add(new HomeCategoryModel("https://live.staticflickr.com/1739/42363610931_cc333ba7de_b.jpg", "salat"));
        categoryModels.add(new HomeCategoryModel("https://live.staticflickr.com/1739/42363610931_cc333ba7de_b.jpg", "salat"));


       // CategoryRecyclerViewAdapter categoryAdapter = new CategoryRecyclerViewAdapter(categoryModels, this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.popularRecycler.setLayoutManager(layoutManager);
        binding.popularRecycler.setHasFixedSize(true);
        binding.popularRecycler.setNestedScrollingEnabled(false);
       // binding.popularRecycler.setAdapter(categoryAdapter);
    }
}
