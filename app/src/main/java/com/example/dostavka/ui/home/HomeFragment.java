package com.example.dostavka.ui.home;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dostavka.R;
import com.example.dostavka.databinding.FragmentHomeBinding;
import com.example.dostavka.ui.Util;
import com.example.dostavka.ui.catalog.model.CatalogModel;
import com.example.dostavka.ui.home.adapters.CargoRecyclerViewAdapter;
import com.example.dostavka.ui.home.adapters.CategoryRecyclerViewAdapter;
import com.example.dostavka.ui.home.adapters.HomePagerAdapter;
import com.example.dostavka.ui.home.adapters.RestaurantRecyclerViewAdapter;
import com.example.dostavka.ui.home.models.CargoModel;
import com.example.dostavka.ui.home.models.RestaurantCategoryModel;
import com.example.dostavka.ui.home.models.RestaurantResponse;
import com.example.dostavka.ui.login.signIn.SignInListener;
import com.example.dostavka.ui.restaurant.RestaurantActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements SignInListener {

    private HomeViewModel mViewModel;
    FragmentHomeBinding binding;
    public static final String TAG = HomeFragment.class.getSimpleName();

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    String[] images = {"https://c8.alamy.com/comp/MFR53E/ad-a-d-creative-modern-black-letters-logo-design-with-brush-swoosh-MFR53E.jpg", "https://c8.alamy.com/comp/MFR53E/ad-a-d-creative-modern-black-letters-logo-design-with-brush-swoosh-MFR53E.jpg"};
    List<CatalogModel> categoryModels = new ArrayList<>();
    List<RestaurantCategoryModel> restaurantCategoryModels = new ArrayList<>();
    List<CargoModel> cargoModels = new ArrayList<>();

    Util util = new Util();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final SignInListener listener = this;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        listener.Start();
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.setHomeViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        initViewPager();
        initCategoryRecyclerView();
        initRestourauntRecyclerView();

        mViewModel.listener = listener;
        mViewModel.getAllShops(requireContext());
        mViewModel.shops.observe(requireActivity(), new Observer<RestaurantResponse>() {
            @Override
            public void onChanged(RestaurantResponse restaurantResponse) {
                restaurantCategoryModels = restaurantResponse.getData();
                initRestourauntRecyclerView();
                listener.Success();
            }
        });

        return binding.getRoot();
    }

    private void initRestourauntRecyclerView() {
        RestaurantRecyclerViewAdapter categoryAdapter = new RestaurantRecyclerViewAdapter(restaurantCategoryModels, requireContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.restaurantRecycler.setLayoutManager(layoutManager);
        binding.restaurantRecycler.setNestedScrollingEnabled(false);
        binding.restaurantRecycler.setAdapter(categoryAdapter);
        categoryAdapter.setClickListener(new RestaurantRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(requireContext(), RestaurantActivity.class));
            }
        });
    }

    private void initCargoRecyclerView() {
        cargoModels.clear();
        cargoModels.add(new CargoModel(R.drawable.universal,"Универсал" ));
        cargoModels.add(new CargoModel(R.drawable.malotonnaj,"Малотоннаж" ));
        cargoModels.add(new CargoModel(R.drawable.gruzovik,"Большой грузовик" ));
        final CargoRecyclerViewAdapter cargoAdapter = new CargoRecyclerViewAdapter(cargoModels, requireContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.restaurantRecycler.setLayoutManager(layoutManager);
        binding.restaurantRecycler.setNestedScrollingEnabled(false);
        binding.restaurantRecycler.setAdapter(cargoAdapter);
        cargoAdapter.setClickListener(new CargoRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                cargoAdapter.changeColor(position);
            }
        });
    }

    private void initCategoryRecyclerView() {
        categoryModels.add(new CatalogModel("Еда", R.drawable.bel_eda));
        categoryModels.add(new CatalogModel("Грузы", R.drawable.bel_gruz));
        categoryModels.add(new CatalogModel("Лекарства", R.drawable.bel_lek));
        categoryModels.add(new CatalogModel("Клининг", R.drawable.bel_klin));
        categoryModels.add(new CatalogModel("Покупки", R.drawable.bel_pok));
        categoryModels.add(new CatalogModel("Посылка", R.drawable.bel_pos));


        final CategoryRecyclerViewAdapter categoryAdapter = new CategoryRecyclerViewAdapter(categoryModels, requireContext());
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.categoryRecycler.setLayoutManager(layoutManager);
        binding.categoryRecycler.setHasFixedSize(true);
        binding.categoryRecycler.setNestedScrollingEnabled(false);
        binding.categoryRecycler.setAdapter(categoryAdapter);
        categoryAdapter.setClickListener(new CategoryRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                categoryAdapter.changeColor(position);
                switch (position){
                    case 0:
                        initRestourauntRecyclerView();
                        break;
                    case 1:
                        initCargoRecyclerView();
                        break;
                }
            }
        });
    }

    private void initViewPager() {
        HomePagerAdapter adapter = new HomePagerAdapter(requireContext(), images);
        binding.viewPager.setAdapter(adapter);
        binding.indicator.setViewPager(binding.viewPager);
    }

    @Override
    public void Success() {
        util.goneProgress(binding.progressBarHome);
    }

    @Override
    public void Failed(String message) {
        util.showToast(requireContext(),message);
        util.goneProgress(binding.progressBarHome);
    }

    @Override
    public void Finish() {
        util.goneProgress(binding.progressBarHome);
    }

    @Override
    public void Start() {
        util.showProgress(binding.progressBarHome);
    }
}
