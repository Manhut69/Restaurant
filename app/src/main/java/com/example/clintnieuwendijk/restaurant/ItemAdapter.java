package com.example.clintnieuwendijk.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<MenuItem> {

    ArrayList<MenuItem> items;

    public ItemAdapter(Context context, int resource, ArrayList<MenuItem> items) {
        super(context, resource, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            Log.d("It's", "Null");
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item_adapter, parent, false);
        }

        MenuItem menuItem = items.get(position);
        TextView categoryView = convertView.findViewById(R.id.categoryView);
        categoryView.setText(menuItem.getCategory());

        TextView description = convertView.findViewById(R.id.descriptionView);
        description.setText(menuItem.getDescription());

        TextView name = convertView.findViewById(R.id.nameView);
        name.setText(menuItem.getName());

        TextView price = convertView.findViewById(R.id.priceView);
        price.setText(Double.toString(menuItem.getPrice()));

        ImageView iv = convertView.findViewById(R.id.itemImageView);
        Picasso.with(getContext()).load(menuItem.getImageUrl()).into(iv);

        return convertView;
    }
}
