package com.youtube.videos.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.youtube.videos.R;
import com.youtube.videos.model.Video;

public class VideoPlayerActivity extends BaseYouTubeActivity {

    public static final String EXTRA_VIDEO = "com.youtube.videos.EXTRA_VIDEO";

    private Video video;

    public static void newIntent(Activity activity, Video video) {
        Intent intent = new Intent(activity, VideoPlayerActivity.class);
        intent.putExtra(EXTRA_VIDEO, video);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        video = getIntent().getParcelableExtra(EXTRA_VIDEO);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(video.getId());
        }
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

}
