package com.example.dostavka.ui.chating.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dostavka.R;
import com.example.dostavka.ui.chating.models.data;

import java.util.List;

public class LVadapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<data> objects;

    public LVadapter(Context context, List<data> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.list_msg, parent, false);
        }

        TextView textView = view.findViewById(R.id.message_user);
        TextView message_time = view.findViewById(R.id.message_time);
        TextView message_text = view.findViewById(R.id.message_text);

        textView.setText(objects.get(position).getFrom());
        message_time.setText(objects.get(position).getCreated_at());
        message_text.setText(objects.get(position).getMessage());

        return view;
    }
}