package com.example.dostavka.ui.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dostavka.R;
import com.example.dostavka.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {


    SettingsViewModel model;
    ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);

        model = new ViewModelProvider(this).get(SettingsViewModel.class);

        binding.setHomeViewModel(model);


        binding.moreSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, MoreSettingsActivity.class));
            }
        });
        binding.sposobyOplaty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, MoreSettingsActivity.class));
            }
        });
        binding.myAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, AddressActivity.class));
            }
        });
        binding.courier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, CourierActivity.class));
            }
        });

    }
}
