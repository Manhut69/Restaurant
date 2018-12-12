package com.example.clintnieuwendijk.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    private String categories[] = {"appetizers", "entrees"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        CategoriesRequest x = new CategoriesRequest(this);
        x.getCategories(this);
        Toast.makeText(this, "I have been started", Toast.LENGTH_SHORT).show();
    }

    private class OnItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", categories[position]);
            startActivity(intent);
            return true;
        }
    }
    @Override
    public void gotCategories(ArrayList<String> categories) {
        Toast.makeText(this, categories.get(0), Toast.LENGTH_LONG).show();
        MenuEntryAdapter menuEntry = new MenuEntryAdapter(this, R.layout.activity_menu_entry, categories);
        ListView lv = findViewById(R.id.categoryListLayout);
        lv.setAdapter(menuEntry);
        lv.setOnItemLongClickListener(new OnItemLongClickListener());
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
