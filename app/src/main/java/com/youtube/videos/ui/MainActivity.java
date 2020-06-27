package com.youtube.videos.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youtube.videos.R;
import com.youtube.videos.adapter.VideosAdapter;
import com.youtube.videos.api.VideoViewModel;
import com.youtube.videos.model.Video;

public class MainActivity extends AppCompatActivity implements VideosAdapter.OnClickListener {

    private View emptyList;
    private RecyclerView recyclerView;
    private VideosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emptyList = findViewById(R.id.emptyList);
        recyclerView = findViewById(R.id.rec_videos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        VideoViewModel itemViewModel = ViewModelProviders.of(this).get(VideoViewModel.class);
        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<Video>>() {
            @Override
            public void onChanged(@Nullable PagedList<Video> items) {
//                showEmptyList(items == null || items.size() == 0);
                adapter.submitList(items);
            }
        });
        recyclerView.setAdapter(adapter = new VideosAdapter(this));
    }

    private void showEmptyList(boolean show) {
        recyclerView.setVisibility(!show ? View.VISIBLE : View.GONE);
        emptyList.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(Video video) {
        VideoPlayerActivity.newIntent(this, video);
    }
}
