package com.youtube.videos.util;

import android.util.Log;

import com.youtube.videos.model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VideoUtil {

    private static final String TAG = "VideoUtil_Log";

    public static List<Video> fromResponse(String response) {
        List<Video> videos = new ArrayList<>();
        JSONArray itemsJA;
        try {
            JSONObject responseJO = new JSONObject(response);
            itemsJA = responseJO.getJSONArray("items");
        } catch (JSONException e) {
            Log.e(TAG, "fromResponse: ", e);
            return videos;
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
                videos.add(new Video(videoId, title, description, thumbnailURL));
            } catch (JSONException e) {
                Log.e(TAG, "fromResponse: ", e);
            }
        }
        Log.i(TAG, "fromResponse: videosSize => " + videos.size());
        return videos;
    }
}
