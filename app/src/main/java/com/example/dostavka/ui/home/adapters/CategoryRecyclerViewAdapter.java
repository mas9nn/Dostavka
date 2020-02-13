package com.example.dostavka.ui.home.adapters;

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
import com.example.dostavka.ui.home.models.HomeCategoryModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.MyViewHolder> {

    private ItemClickListener mClickListener;
    private List<CatalogModel> categoryModels ;
    private Context context;
    int index;
    public CategoryRecyclerViewAdapter(List<CatalogModel> categoryModels, Context context) {
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
      //  Picasso.get().load(categoryModels.get(position).getImage()).into(holder.image_of_category);
        Drawable mIcon = context.getResources().getDrawable(categoryModels.get(position).getImage());
        if (index == position) {
            holder.cardview_item.setCardBackgroundColor(ContextCompat.getColor(context, R.color.orange));
            holder.image_of_category.setImageDrawable(getTintedDrawable(context.getResources(),categoryModels.get(position).getImage(),R.color.white));
            Animation anim = AnimationUtils.loadAnimation(context, R.anim.scale_in);
            holder.itemView.startAnimation(anim);
            anim.setFillAfter(true);
        } else {
            holder.cardview_item.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white));
            holder.image_of_category.setImageDrawable(getTintedDrawable(context.getResources(),categoryModels.get(position).getImage(),R.color.orange));

            holder.image_of_category.setImageDrawable(mIcon);
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
        TextView name_of_category;
        ImageView image_of_category;
        CardView cardview_item;
        MyViewHolder(View itemView) {
            super(itemView);
            name_of_category = itemView.findViewById(R.id.name_of_category);
            image_of_category = itemView.findViewById(R.id.image_of_category);
            cardview_item = itemView.findViewById(R.id.cardview_item);
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
