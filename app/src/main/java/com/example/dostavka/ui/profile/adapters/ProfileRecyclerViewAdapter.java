package com.example.dostavka.ui.profile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dostavka.R;
import com.example.dostavka.ui.home.models.HomeCategoryModel;
import com.example.dostavka.ui.home.models.RestaurantCategoryModel;

import java.util.List;

public class ProfileRecyclerViewAdapter extends RecyclerView.Adapter<ProfileRecyclerViewAdapter.MyViewHolder> {

    private ItemClickListener mClickListener;
    private List<HomeCategoryModel> categoryModels;
    private Context context;

    public ProfileRecyclerViewAdapter(List<HomeCategoryModel> categoryModels, Context context) {
        this.categoryModels = categoryModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_profile_restaurant, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
