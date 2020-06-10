package com.youtube.videos.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.youtube.videos.R;
import com.youtube.videos.adapter.VideosAdapter;
import com.youtube.videos.model.Video;
import com.youtube.videos.util.VideoUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Log";
    private static final String URL = "https://www.googleapis.com/youtube/v3/search?" +
            "part=snippet" +
            "&channelId=UC1NuE6xeMyyTCHq0sKjJ5IQ" +
            "&maxResults=50" +
            "&key=AIzaSyDVHbsDrMF_8kG82q8x9HVfpeZvTOl773A";
    private List<Video> items = new ArrayList<>();
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rec_videos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new VideosAdapter(items));
        loadVideos();
    }

    private void loadVideos() {
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        items.addAll(VideoUtil.fromResponse(response));
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "That didn't work!", error);
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
