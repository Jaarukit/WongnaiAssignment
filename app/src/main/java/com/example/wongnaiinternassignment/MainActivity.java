package com.example.wongnaiinternassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.wongnaiinternassignment.Models.Coin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Coin> mCoin;
    private static String JSON_URL = "https://api.coinranking.com/v1/public/coins";
    Adapter mAdapter;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        mCoin = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON2();

    }

    private void parseJSON() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONArray jsonArray = response.getJSONArray(1);
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        Coin coin = new Coin();
                        coin.setmName(jsonObject.getString("name").toString());
                        coin.setmDescription(jsonObject.getString("description".toString()));
                        coin.setmImageURL(jsonObject.getString("iconUrl"));

                        mCoin.add(coin);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                recyclerView.setLayoutManager((new LinearLayoutManager(getApplicationContext())));
                mAdapter = new Adapter(getApplicationContext(), mCoin);
                recyclerView.setAdapter(mAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);
    }

    private void parseJSON2() {
        String url = "https://api.coinranking.com/v1/public/coins";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONObject("data").getJSONArray("coins");


                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String name = hit.getString("name");
                                String description = hit.getString("description");
                                String iconUrl = hit.getString("iconUrl");

                                mCoin.add(new Coin(name, description, iconUrl));
                            }
                            recyclerView.setLayoutManager((new LinearLayoutManager(getApplicationContext())));
                            mAdapter = new Adapter(getApplicationContext(), mCoin);
                            recyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
}




