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

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    Context context;
    Callback activity;
    String category;

    public interface Callback {
        void gotItems(ArrayList<MenuItem> items);
        void gotItemsError(String message);
    }

    public MenuRequest(Context context, String category) {
        this.context = context;
        this.category = category;
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray itemsJSONResponse = new JSONArray();
        ArrayList<MenuItem> items = new ArrayList<>();
        try {
            itemsJSONResponse = response.getJSONArray("items");
            for (int i = 0; i < itemsJSONResponse.length(); i++) {
                JSONObject item = itemsJSONResponse.getJSONObject(i);
                if (item.getString("category").equals(category)) {
                    items.add(new MenuItem(item.getString("category"), item.getString("description"), item.getString("image_url"), item.getString("name"), item.getDouble("price"), item.getInt("id")));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("An exception happened", "Riv");
        }

        activity.gotItems(items);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotItemsError(error.getMessage());
    }


    public void getItems(Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonRequest = new JsonObjectRequest("https://resto.mprog.nl/menu", null, this, this);
        queue.add(jsonRequest);
    }
}
