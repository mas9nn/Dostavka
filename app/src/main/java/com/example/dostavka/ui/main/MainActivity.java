package com.example.dostavka.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.example.dostavka.R;
import com.example.dostavka.databinding.ActivityMainBinding;
import com.example.dostavka.databinding.NavHeaderMainBinding;
import com.example.dostavka.ui.Util;
import com.example.dostavka.ui.catalog.CatalogFragment;
import com.example.dostavka.ui.home.HomeFragment;
import com.example.dostavka.ui.profile.ProfileFragment;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    MainActivityViewModel model;
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawer_layout;
    ActivityMainBinding binding;
    Util util = new Util();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        model = new ViewModelProvider(this).get(MainActivityViewModel.class);
        setUp();
        setupNavMenu();

        binding.setViewmodel(model);

        model.username = util.getUserName(this);
    }
    private void setUp() {
        navigationView = binding.navView;
        toolbar = binding.toolbar;
        drawer_layout = binding.drawerLayout;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer_layout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }
    private void setupNavMenu() {
        NavHeaderMainBinding navHeaderMainBinding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.nav_header_main, binding.navView, false);
        binding.navView.addHeaderView(navHeaderMainBinding.getRoot());
        navHeaderMainBinding.setViewmodel(model);
        Picasso.get().load("https://www.stickpng.com/assets/images/580b57fbd9996e24bc43be54.png").into(navHeaderMainBinding.imageViewName);
        navHeaderMainBinding.headerOfdrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.toolbarTitle.setVisibility(View.GONE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .disallowAddToBackStack()
                        .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                        .replace(R.id.container, ProfileFragment.newInstance(), ProfileFragment.TAG)
                        .commit();
                drawer_layout.closeDrawer(GravityCompat.START);
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.orange)));

                ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawer_layout, toolbar,R.string.app_name, R.string.app_name);
                mDrawerToggle.getDrawerArrowDrawable().setColor(Color.WHITE);
                drawer_layout.addDrawerListener(mDrawerToggle);

                mDrawerToggle.syncState();
                onNavigationItemSelected(navigationView.getMenu().getItem(5));
                navigationView.setCheckedItem(R.id.menu_none);
            }
        });
        navigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(this);
        onNavigationItemSelected(navigationView.getMenu().getItem(1));
        navigationView.setCheckedItem(R.id.second);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        binding.toolbarTitle.setVisibility(View.VISIBLE);
        drawer_layout.closeDrawer(GravityCompat.START);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.back_for_feed)));
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawer_layout, toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.getDrawerArrowDrawable().setColor(Color.GRAY);
        drawer_layout.addDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();
        switch (item.getItemId()) {
            case R.id.first:
                getSupportFragmentManager()
                        .beginTransaction()
                        .disallowAddToBackStack()
                        .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                        .replace(R.id.container, HomeFragment.newInstance(), HomeFragment.TAG)
                        .commit();
                return true;
            case R.id.second:
                getSupportFragmentManager()
                        .beginTransaction()
                        .disallowAddToBackStack()
                        .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                        .replace(R.id.container, CatalogFragment.newInstance(), CatalogFragment.TAG)
                        .commit();
                return true;
            case R.id.third:

                return true;
            case R.id.fourth:

                return true;
            default:
                return false;
        }
    }
}
