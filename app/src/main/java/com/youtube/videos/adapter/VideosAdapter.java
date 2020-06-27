package com.youtube.videos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youtube.videos.R;
import com.youtube.videos.model.Video;

public class VideosAdapter extends PagedListAdapter<Video, VideosAdapter.VideosHolder> {

    private static final DiffUtil.ItemCallback<Video> DIFF_CALLBACK = new DiffUtil.ItemCallback<Video>() {
        @Override
        public boolean areItemsTheSame(@NonNull Video oldItem, @NonNull Video newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Video oldItem, @NonNull Video newItem) {
            return oldItem.equals(newItem);
        }
    };

    private OnClickListener listener;

    public VideosAdapter(OnClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    @NonNull
    @Override
    public VideosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return VideosHolder.from(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosHolder holder, int position) {
        Video repoItem = getItem(position);
        if (repoItem != null) {
            holder.bindView(repoItem, listener);
        }

    }

    public interface OnClickListener {
        void onClick(Video video);
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

        void bindView(final Video video, final OnClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onClick(video);
                }
            });
            text.setText(video.getName());
            Glide.with(itemView).load(video.getThumbnail()).into(thumbnail);
        }
    }
}
