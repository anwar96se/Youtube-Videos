package com.youtube.videos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youtube.videos.R;
import com.youtube.videos.model.Video;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideosHolder> {

    private List<Video> items;

    public VideosAdapter(List<Video> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public VideosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return VideosHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosHolder holder, int position) {
        holder.bindView(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    static class VideosHolder extends RecyclerView.ViewHolder {

        private ImageView thumbnail;
        private TextView text;

        private VideosHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            text = itemView.findViewById(R.id.text);
        }

        static VideosHolder from(ViewGroup view) {
            return new VideosHolder(LayoutInflater.from(view.getContext()).inflate(R.layout.item_video, null, false));
        }

        void bindView(Video video) {
            text.setText(video.getName());
            Glide.with(itemView).load(video.getThumbnail()).into(thumbnail);
        }
    }
}
