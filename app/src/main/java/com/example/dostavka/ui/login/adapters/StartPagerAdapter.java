package com.example.dostavka.ui.login.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.dostavka.R;
import com.example.dostavka.ui.login.models.StartPagerModel;

import java.util.List;

public class StartPagerAdapter extends PagerAdapter {
    private Context context;
    private List<StartPagerModel> pagerModels;
    private LayoutInflater layoutInflater;


    public StartPagerAdapter(Context context, List<StartPagerModel> pagerModels) {
        this.context = context;
        this.pagerModels = pagerModels;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return pagerModels.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.item_startpager, container, false);

        ImageView imageView = itemView.findViewById(R.id.main_image);
        TextView header_text = itemView.findViewById(R.id.header_text);
        TextView description_text = itemView.findViewById(R.id.description_text);

        imageView.setImageResource(pagerModels.get(position).getImage());
        header_text.setText(pagerModels.get(position).getHeader());
        description_text.setText(pagerModels.get(position).getDescription());
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}