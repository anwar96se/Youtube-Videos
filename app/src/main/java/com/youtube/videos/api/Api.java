package com.youtube.videos.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String TAG = "Api_Log";

    @GET("search")
    Call<ResponseBody> getVideos(
            @Query("key") String key
            , @Query("part") String part
            , @Query("channelId") String channelId
            , @Query("maxResults") String maxResults
    );

    @GET("search")
    Call<ResponseBody> getPageVideos(
            @Query("key") String key
            , @Query("part") String part
            , @Query("channelId") String channelId
            , @Query("maxResults") String maxResults
            , @Query("pageToken") String pageToken
    );
}
