package com.example.dostavka.ui.profile;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.dostavka.R;
import com.example.dostavka.ui.home.HomeFragment;
import com.example.dostavka.ui.home.adapters.CategoryRecyclerViewAdapter;
import com.example.dostavka.ui.home.models.HomeCategoryModel;
import com.example.dostavka.ui.home.models.RestaurantCategoryModel;
import com.example.dostavka.ui.profile.adapters.ProfileRecyclerViewAdapter;
import com.example.dostavka.ui.settings.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    public static final String TAG = ProfileFragment.class.getSimpleName();
    List<HomeCategoryModel> categoryModels = new ArrayList<>();

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    RecyclerView rec;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);

        CardView cardView = v.findViewById(R.id.card_view_profile);
        cardView.setBackgroundResource(R.drawable.shape_card_topcorners);

        rec = v.findViewById(R.id.recycler_of_profile);
        initRecycler();
        return v;
    }

    private void initRecycler() {
        categoryModels.add(new HomeCategoryModel("https://live.staticflickr.com/1739/42363610931_cc333ba7de_b.jpg", "salat"));
        categoryModels.add(new HomeCategoryModel("https://live.staticflickr.com/1739/42363610931_cc333ba7de_b.jpg", "salat"));
        categoryModels.add(new HomeCategoryModel("https://live.staticflickr.com/1739/42363610931_cc333ba7de_b.jpg", "salat"));
        categoryModels.add(new HomeCategoryModel("https://live.staticflickr.com/1739/42363610931_cc333ba7de_b.jpg", "salat"));
        categoryModels.add(new HomeCategoryModel("https://live.staticflickr.com/1739/42363610931_cc333ba7de_b.jpg", "salat"));
        categoryModels.add(new HomeCategoryModel("https://live.staticflickr.com/1739/42363610931_cc333ba7de_b.jpg", "salat"));

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        rec.setLayoutManager(layoutManager);
        ProfileRecyclerViewAdapter adapter = new ProfileRecyclerViewAdapter(categoryModels, requireContext());
        rec.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.options:
                startActivity(new Intent(requireContext(), SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
