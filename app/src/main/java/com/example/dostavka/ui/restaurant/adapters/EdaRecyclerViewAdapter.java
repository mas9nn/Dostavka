package com.example.dostavka.ui.restaurant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dostavka.R;
import com.example.dostavka.ui.home.models.RestaurantCategoryModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EdaRecyclerViewAdapter extends RecyclerView.Adapter<EdaRecyclerViewAdapter.MyViewHolder> {

    private ItemClickListener mClickListener;
    private List<RestaurantCategoryModel> categoryModels;
    private Context context;

    public EdaRecyclerViewAdapter(List<RestaurantCategoryModel> categoryModels, Context context) {
        this.categoryModels = categoryModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_restaurant_eat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, rating, duration, rating_all, name_glav,price;
        ImageView image;

        MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.rating);
            duration = itemView.findViewById(R.id.duration);
            rating_all = itemView.findViewById(R.id.rating_all);
            name_glav = itemView.findViewById(R.id.name_of_restaurant);
            price = itemView.findViewById(R.id.price_of_restaurant);
            image = itemView.findViewById(R.id.image_restaurant);
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
