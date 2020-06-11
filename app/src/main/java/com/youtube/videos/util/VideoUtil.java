package com.youtube.videos.util;

import android.util.Log;

import com.youtube.videos.model.Video;
import com.youtube.videos.model.VideoResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VideoUtil {

    private static final String TAG = "VideoUtil_Log";

    public static VideoResponse fromResponse(String response) {
        Log.i(TAG, "fromResponse:response => " + response);
        VideoResponse videoResponse = new VideoResponse();
        List<Video> videos = new ArrayList<>();
        JSONArray itemsJA;
        try {
            JSONObject responseJO = new JSONObject(response);
            if (responseJO.has("nextPageToken"))
                videoResponse.setNextPageToken(responseJO.getString("nextPageToken"));
            if (responseJO.has("prevPageToken"))
                videoResponse.setPrevPageToken(responseJO.getString("prevPageToken"));
            itemsJA = responseJO.getJSONArray("items");
        } catch (JSONException e) {
            Log.e(TAG, "fromResponse: ", e);
            return videoResponse;
        }
        for (int i = 0; i < itemsJA.length(); i++) {
            try {
                JSONObject itemJO = itemsJA.getJSONObject(i);
                String videoId = itemJO.getJSONObject("id").getString("videoId");
                JSONObject snippetJO = itemJO.getJSONObject("snippet");
                String title = snippetJO.getString("title");
                String description = snippetJO.getString("description");
                JSONObject thumbnailJO = snippetJO.getJSONObject("thumbnails").getJSONObject("medium");
                String thumbnailURL = thumbnailJO.getString("url");
                videos.add(new Video(videoId, title, description, thumbnailURL).logInfo());

            } catch (JSONException e) {
                Log.e(TAG, "fromResponse: ", e);
            }
        }
        videoResponse.setVideos(videos);
        Log.i(TAG, "fromResponse: videosSize => " + videos.size());
        return videoResponse;
    }
}
