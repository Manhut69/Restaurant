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
import android.widget.TextView;


import java.util.ArrayList;
import com.squareup.picasso.Picasso;

public class MenuEntryAdapter extends ArrayAdapter<String> {

    ArrayList<String> content;

    // initialize the menu list
    MenuEntryAdapter(Context context, int resource, ArrayList<String> content) {
        super(context, resource, content);
        this.content = content;
    }

    // input menu items
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            Log.e("content", "Is null");
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_menu_entry,
                                                                    parent, false);
        }

        TextView txt = convertView.findViewById(R.id.menuContent);
        txt.setText(content.get(position));
        return convertView;
    }
}
