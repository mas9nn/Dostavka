package com.example.dostavka.ui.home;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dostavka.R;
import com.example.dostavka.databinding.HomeFragmentBinding;
import com.example.dostavka.ui.home.adapters.CategoryRecyclerViewAdapter;
import com.example.dostavka.ui.home.adapters.HomePagerAdapter;
import com.example.dostavka.ui.home.adapters.RestaurantRecyclerViewAdapter;
import com.example.dostavka.ui.home.models.HomeCategoryModel;
import com.example.dostavka.ui.home.models.RestaurantCategoryModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    HomeFragmentBinding binding;
    public static final String TAG = HomeFragment.class.getSimpleName();

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    String[] images = {"https://c8.alamy.com/comp/MFR53E/ad-a-d-creative-modern-black-letters-logo-design-with-brush-swoosh-MFR53E.jpg", "https://c8.alamy.com/comp/MFR53E/ad-a-d-creative-modern-black-letters-logo-design-with-brush-swoosh-MFR53E.jpg"};
    List<HomeCategoryModel> categoryModels = new ArrayList<>();
    List<RestaurantCategoryModel> restaurantCategoryModels = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.setHomeViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        initViewPager();
        initCategoryRecyclerView();
        initRestourauntRecyclerView();
        return binding.getRoot();
    }

    private void initRestourauntRecyclerView() {
        restaurantCategoryModels.add(new RestaurantCategoryModel());
        restaurantCategoryModels.add(new RestaurantCategoryModel());
        restaurantCategoryModels.add(new RestaurantCategoryModel());
        restaurantCategoryModels.add(new RestaurantCategoryModel());
        restaurantCategoryModels.add(new RestaurantCategoryModel());
        restaurantCategoryModels.add(new RestaurantCategoryModel());
        RestaurantRecyclerViewAdapter categoryAdapter = new RestaurantRecyclerViewAdapter(restaurantCategoryModels,requireContext());
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext());
        binding.restaurantRecycler.setLayoutManager(layoutManager);
        binding.restaurantRecycler.setHasFixedSize(true);
        binding.restaurantRecycler.setNestedScrollingEnabled(false);
        binding.restaurantRecycler.setAdapter(categoryAdapter);
    }

    private void initCategoryRecyclerView() {
        categoryModels.add(new HomeCategoryModel("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png","Googel"));
        categoryModels.add(new HomeCategoryModel("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png","Googel"));
        categoryModels.add(new HomeCategoryModel("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png","Googel"));
        categoryModels.add(new HomeCategoryModel("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png","Googel"));
        categoryModels.add(new HomeCategoryModel("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png","Googel"));
        categoryModels.add(new HomeCategoryModel("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png","Googel"));
        CategoryRecyclerViewAdapter categoryAdapter = new CategoryRecyclerViewAdapter(categoryModels,requireContext());
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.categoryRecycler.setLayoutManager(layoutManager);
        binding.categoryRecycler.setHasFixedSize(true);
        binding.categoryRecycler.setNestedScrollingEnabled(false);
        binding.categoryRecycler.setAdapter(categoryAdapter);
    }

    private void initViewPager() {
        HomePagerAdapter adapter = new HomePagerAdapter(requireContext(), images);
        binding.viewPager.setAdapter(adapter);
        binding.indicator.setViewPager(binding.viewPager);
    }

}
