package com.example.dostavka.ui.catalog;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dostavka.R;
import com.example.dostavka.databinding.FragmentCatalogBinding;
import com.example.dostavka.ui.catalog.adapters.CatalogRecyclerViewAdapter;
import com.example.dostavka.ui.catalog.model.CatalogModel;

import java.util.ArrayList;
import java.util.List;

public class CatalogFragment extends Fragment {

    private CatalogViewModel mViewModel;
    FragmentCatalogBinding binding;
    List<CatalogModel> catalogModels = new ArrayList<>();


    public static final String TAG = CatalogFragment.class.getSimpleName();

    public static CatalogFragment newInstance() {
        return new CatalogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_catalog, container, false);
        mViewModel = new ViewModelProvider(this).get(CatalogViewModel.class);
        binding.setViewmodel(mViewModel);
        initCatalogs();
        return binding.getRoot();
    }

    private void initCatalogs() {
        catalogModels.add(new CatalogModel("Еда",R.drawable.bel_eda));
        catalogModels.add(new CatalogModel("Грузы",R.drawable.bel_gruz));
        catalogModels.add(new CatalogModel("Лекарства",R.drawable.bel_lek));
        catalogModels.add(new CatalogModel("Клининг",R.drawable.bel_klin));
        catalogModels.add(new CatalogModel("Покупки",R.drawable.bel_pok));
        catalogModels.add(new CatalogModel("Посылка",R.drawable.bel_pos));
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(requireContext(), 2);
        binding.catalogRecycler.setLayoutManager(mLayoutManager);
        binding.catalogRecycler.setHasFixedSize(true);
        binding.catalogRecycler.setNestedScrollingEnabled(false);
        final CatalogRecyclerViewAdapter adapter = new CatalogRecyclerViewAdapter(catalogModels,requireContext());
        binding.catalogRecycler.setAdapter(adapter);
        adapter.setClickListener(new CatalogRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                adapter.changeColor(position);
            }
        });

    }
}
