package com.example.news_search;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static ImageView myimage;
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    public RecyclerView r1;
    ArrayList<NewsData> items;
    public MyOwnAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Locate the ListView in listview_main.xml


        r1 = (RecyclerView) findViewById(R.id.recyclerView);
        mQueue = Volley.newRequestQueue(this);
        jsonParse();
        CardView block = (CardView) findViewById(R.id.oneBlock);


        // Locate the EditText in listview_main.xml
        SearchView editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
    }

    private void jsonParse() {
        String url = " https://newsapi.org/v2/top-headlines?country=us&apiKey=27e64574c3c64573afbea511eb0e65d2";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            ArrayList<NewsData> items = new ArrayList<>();
                            JSONArray jsonArray = response.getJSONArray("articles");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject cases = jsonArray.getJSONObject(i);
                                String title = cases.getString("title");
                                String url = cases.getString("urlToImage");


                                items.add(new NewsData(title, url));

                            }
                            adapter = new MyOwnAdapter(getApplicationContext(), items);
                            r1.setAdapter(adapter);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            r1.setLayoutManager(layoutManager);


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
        mQueue.add(request);
    }


@Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

@Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}
