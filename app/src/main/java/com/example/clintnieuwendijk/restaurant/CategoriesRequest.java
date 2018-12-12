package com.example.clintnieuwendijk.restaurant;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    Context context;
    Callback activity;

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray categoriesJSONResponse = new JSONArray();
        ArrayList<String> categories = new ArrayList<>();
        try {
            categoriesJSONResponse = response.getJSONArray("categories");
            for (int i = 0; i < categoriesJSONResponse.length(); i++) {
                categories.add(categoriesJSONResponse.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("An exception happened", "Riv");
        }

        activity.gotCategories(categories);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotCategoriesError(error.getMessage());
        Log.e("Error requesting JSON", error.getMessage());
    }


    public void getCategories(Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonRequest);
    }
}
