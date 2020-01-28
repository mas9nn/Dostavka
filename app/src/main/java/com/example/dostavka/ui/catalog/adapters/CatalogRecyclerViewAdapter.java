package com.example.dostavka.ui.catalog.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dostavka.R;
import com.example.dostavka.ui.catalog.model.CatalogModel;
import com.example.dostavka.ui.home.models.RestaurantCategoryModel;

import java.util.List;

public class CatalogRecyclerViewAdapter extends RecyclerView.Adapter<CatalogRecyclerViewAdapter.MyViewHolder> {

    private ItemClickListener mClickListener;
    private List<CatalogModel> categoryModels;
    private Context context;
    int index;

    public CatalogRecyclerViewAdapter(List<CatalogModel> categoryModels, Context context) {
        this.categoryModels = categoryModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_catalog, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(categoryModels.get(position).getName());
        Drawable mIcon = context.getResources().getDrawable(categoryModels.get(position).getImage());
        if (index == position) {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.orange));
            holder.image.setImageDrawable(getTintedDrawable(context.getResources(),categoryModels.get(position).getImage(),R.color.white));
            Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_in);
            holder.itemView.startAnimation(anim);
            holder.name.setTextColor(ContextCompat.getColor(context, R.color.white));
            anim.setFillAfter(true);
        } else {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white));
            holder.image.setImageDrawable(getTintedDrawable(context.getResources(),categoryModels.get(position).getImage(),R.color.orange));
            holder.name.setTextColor(ContextCompat.getColor(context, R.color.black));

            holder.image.setImageDrawable(mIcon);
        }
    }

    public Drawable getTintedDrawable(Resources res,
                                      @DrawableRes int drawableResId, @ColorRes int colorResId) {
        Drawable drawable = res.getDrawable(drawableResId);
        int color = res.getColor(colorResId);
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

    public void changeColor(int position) {
        index = position;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        ImageView image;
        CardView cardView;
        MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_of_catalog);
            image = itemView.findViewById(R.id.catalog_image);
            cardView = itemView.findViewById(R.id.card_view);
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
