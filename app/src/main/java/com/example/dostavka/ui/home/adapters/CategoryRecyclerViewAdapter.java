package com.example.dostavka.ui.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dostavka.R;
import com.example.dostavka.ui.home.models.HomeCategoryModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.MyViewHolder> {

    private ItemClickListener mClickListener;
    private List<HomeCategoryModel> categoryModels ;
    private Context context;

    public CategoryRecyclerViewAdapter(List<HomeCategoryModel> categoryModels, Context context) {
        this.categoryModels = categoryModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_of_category.setText(categoryModels.get(position).getName());
        Picasso.get().load(categoryModels.get(position).getImage()).into(holder.image_of_category);
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name_of_category;
        ImageView image_of_category;
        MyViewHolder(View itemView) {
            super(itemView);
            name_of_category = itemView.findViewById(R.id.name_of_category);
            image_of_category = itemView.findViewById(R.id.image_of_category);
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
