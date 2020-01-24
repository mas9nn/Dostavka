package com.example.dostavka.ui.home;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dostavka.R;
import com.example.dostavka.databinding.HomeFragmentBinding;
import com.example.dostavka.ui.home.adapters.HomePagerAdapter;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    HomeFragmentBinding binding;
    public static final String TAG = HomeFragment.class.getSimpleName();

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    String[] images = {"https://c8.alamy.com/comp/MFR53E/ad-a-d-creative-modern-black-letters-logo-design-with-brush-swoosh-MFR53E.jpg", "https://c8.alamy.com/comp/MFR53E/ad-a-d-creative-modern-black-letters-logo-design-with-brush-swoosh-MFR53E.jpg"};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.setHomeViewModel(mViewModel);
        binding.setLifecycleOwner(this);
        initViewPager();
        return binding.getRoot();
    }

    private void initViewPager() {
        HomePagerAdapter adapter = new HomePagerAdapter(requireContext(), images);
        binding.viewPager.setAdapter(adapter);
        binding.indicator.setViewPager(binding.viewPager);
    }

}
