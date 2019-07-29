/*
    An app to review the menu in a restaurant using adapters and requests
    By Clint Nieuwendijk
 */

package com.example.clintnieuwendijk.restaurant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    // request menu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MenuRequest x = new MenuRequest(this, getIntent().getStringExtra("category"));
        x.getItems(this);
    }

    // if request is successful, create menu
    @Override
    public void gotItems(ArrayList<MenuItem> categories) {
        ItemAdapter itemEntry = new ItemAdapter(this, R.layout.activity_item_adapter, categories);
        ListView lv = findViewById(R.id.menuListView);
        lv.setAdapter(itemEntry);
    }

    // if request failed, display error message
    @Override
    public void gotItemsError(String message) {
        Log.d("Oh no!", "Something went wrong fetching the menu");
        ArrayList<MenuItem> categories = new ArrayList<>(Collections.singletonList(new MenuItem("Error", "Something went wrong", "https://i.imgur.com/KA5GaEv.jpg", "Error", 0.0, 0)));
        gotItems(categories);
    }
}
