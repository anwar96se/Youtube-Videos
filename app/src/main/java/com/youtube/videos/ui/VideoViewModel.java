package com.youtube.videos.ui;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.youtube.videos.api.RetrofitClient;
import com.youtube.videos.model.Video;
import com.youtube.videos.model.VideoResponse;
import com.youtube.videos.util.VideoUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoViewModel extends ViewModel {

    private static final String TAG = "VideoModel_Log";
    LiveData<PagedList<Video>> itemPagedList;
    LiveData<PageKeyedDataSource<String, Video>> liveDataSource;

    //constructor
    public VideoViewModel() {
        //getting our data source factory
        VideoDataSourceFactory itemDataSourceFactory = new VideoDataSourceFactory();

        //getting the live data source from data source factory
        liveDataSource = itemDataSourceFactory.getVideoLiveDataSource();

        //Getting PagedList config
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(VideoDataSource.PAGE_SIZE).build();

        //Building the paged list
        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();
    }

    static class VideoDataSourceFactory extends DataSource.Factory {

        private MutableLiveData<PageKeyedDataSource<String, Video>> itemLiveDataSource = new MutableLiveData<>();

        @NonNull
        @Override
        public DataSource<String, Video> create() {
            VideoDataSource itemDataSource = new VideoDataSource();
            itemLiveDataSource.postValue(itemDataSource);
            return itemDataSource;
        }

        MutableLiveData<PageKeyedDataSource<String, Video>> getVideoLiveDataSource() {
            return itemLiveDataSource;
        }
    }

    static class VideoDataSource extends PageKeyedDataSource<String, Video> {

        static final int PAGE_SIZE = 50;
        private static final String PART = "snippet";
        private static final String CHANNEL_ID = "UC1NuE6xeMyyTCHq0sKjJ5IQ";
        private static final String MAX_RESULT = "50";
        private static final String KEY = "AIzaSyDVHbsDrMF_8kG82q8x9HVfpeZvTOl773A";

        //this will be called once to load the initial data
        @Override
        public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull final PageKeyedDataSource.LoadInitialCallback<String, Video> callback) {
            Log.d(TAG, "loadInitial: ");
            RetrofitClient.getInstance()
                    .getApi().getVideos(KEY, PART, CHANNEL_ID, MAX_RESULT)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    try {
                                        VideoResponse videoResponse = VideoUtil.fromResponse(response.body().string());
                                        callback.onResult(videoResponse.getVideos(), videoResponse.getPrevPageToken(), videoResponse.getNextPageToken());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                try {
                                    String string = response.errorBody().string();
                                    Log.e(TAG, "onResponse: " + string);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e(TAG, "onFailure: ", t);
                        }
                    });
        }

        //this will load the previous page
        @Override
        public void loadBefore(@NonNull final PageKeyedDataSource.LoadParams<String> params, @NonNull final PageKeyedDataSource.LoadCallback<String, Video> callback) {
            Log.d(TAG, "loadBefore: ");
            RetrofitClient.getInstance()
                    .getApi().getPageVideos(KEY, PART, CHANNEL_ID, MAX_RESULT, params.key)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    try {
                                        VideoResponse videoResponse = VideoUtil.fromResponse(response.body().string());
                                        callback.onResult(videoResponse.getVideos(), videoResponse.getPrevPageToken());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                try {
                                    String string = response.errorBody().string();
                                    Log.e(TAG, "onResponse: " + string);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e(TAG, "onFailure: ", t);
                        }
                    });
        }

        //this will load the next page
        @Override
        public void loadAfter(@NonNull final PageKeyedDataSource.LoadParams<String> params, @NonNull final PageKeyedDataSource.LoadCallback<String, Video> callback) {
            Log.d(TAG, "loadAfter: ");

            RetrofitClient.getInstance()
                    .getApi().getPageVideos(KEY, PART, CHANNEL_ID, MAX_RESULT, params.key)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    try {
                                        VideoResponse videoResponse = VideoUtil.fromResponse(response.body().string());
                                        callback.onResult(videoResponse.getVideos(), videoResponse.getNextPageToken());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                try {
                                    String string = response.errorBody().string();
                                    Log.e(TAG, "onResponse: " + string);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e(TAG, "onFailure: ", t);
                        }
                    });
        }
    }
}
