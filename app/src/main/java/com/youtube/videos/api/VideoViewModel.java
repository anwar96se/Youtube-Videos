package com.youtube.videos.api;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

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
    public LiveData<PagedList<Video>> itemPagedList;
    private LiveData<PageKeyedDataSource<String, Video>> liveDataSource;

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

    public static class VideoDataSource extends PageKeyedDataSource<String, Video> {

        static final int PAGE_SIZE = 50;
        private static final String PART = "snippet";
        private static final String CHANNEL_ID = "UC1NuE6xeMyyTCHq0sKjJ5IQ";
        private static final String MAX_RESULT = "50";
//        private static final String DEVELOPER_KEY = "AIzaSyDVHbsDrMF_8kG82q8x9HVfpeZvTOl773A";
        public static final String DEVELOPER_KEY = "sdsdSDSD";
        public static String TEST_RESPONSE() {
            return "" +
                    "{\n" +
                    "    \"kind\": \"youtube#searchListResponse\",\n" +
                    "    \"etag\": \"8cW1rMT6aPMflvUb_UR9_5GmnsQ\",\n" +
                    "    \"nextPageToken\": \"CDIQAA\",\n" +
                    "    \"regionCode\": \"PS\",\n" +
                    "    \"pageInfo\": {\n" +
                    "        \"totalResults\": 98,\n" +
                    "        \"resultsPerPage\": 50\n" +
                    "    },\n" +
                    "    \"items\": [\n" +
                    "        {\n" +
                    "            \"kind\": \"youtube#searchResult\",\n" +
                    "            \"etag\": \"Y3Jmg6a4d6nuSJhCm22mPB-4blk\",\n" +
                    "            \"id\": {\n" +
                    "                \"kind\": \"youtube#video\",\n" +
                    "                \"videoId\": \"cb4wttm03XI\"\n" +
                    "            },\n" +
                    "            \"snippet\": {\n" +
                    "                \"publishedAt\": \"2019-12-02T11:21:28Z\",\n" +
                    "                \"channelId\": \"UC1NuE6xeMyyTCHq0sKjJ5IQ\",\n" +
                    "                \"title\": \"روائع نبيل العوضي | قصة إسلام الفاروق عمر رضى الله عنه\",\n" +
                    "                \"description\": \"اشترك في قناة دروس الشيخ نبيل العوضي لتصلك جميع الدروس والمحاضرات http://bit.ly/DoroosAlAwadi روائع القصص للشيخ نبيل...\",\n" +
                    "                \"thumbnails\": {\n" +
                    "                    \"default\": {\n" +
                    "                        \"url\": \"https://i.ytimg.com/vi/cb4wttm03XI/default.jpg\",\n" +
                    "                        \"width\": 120,\n" +
                    "                        \"height\": 90\n" +
                    "                    },\n" +
                    "                    \"medium\": {\n" +
                    "                        \"url\": \"https://i.ytimg.com/vi/cb4wttm03XI/mqdefault.jpg\",\n" +
                    "                        \"width\": 320,\n" +
                    "                        \"height\": 180\n" +
                    "                    },\n" +
                    "                    \"high\": {\n" +
                    "                        \"url\": \"https://i.ytimg.com/vi/cb4wttm03XI/hqdefault.jpg\",\n" +
                    "                        \"width\": 480,\n" +
                    "                        \"height\": 360\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                \"channelTitle\": \"الشيخ نبيل العوضي\",\n" +
                    "                \"liveBroadcastContent\": \"none\",\n" +
                    "                \"publishTime\": \"2019-12-02T11:21:28Z\"\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"kind\": \"youtube#searchResult\",\n" +
                    "            \"etag\": \"Au1_3g-bYiQYvlNvPiQhi2H9cRQ\",\n" +
                    "            \"id\": {\n" +
                    "                \"kind\": \"youtube#video\",\n" +
                    "                \"videoId\": \"PzN4Sm5nYsc\"\n" +
                    "            },\n" +
                    "            \"snippet\": {\n" +
                    "                \"publishedAt\": \"2019-02-13T14:00:02Z\",\n" +
                    "                \"channelId\": \"UC1NuE6xeMyyTCHq0sKjJ5IQ\",\n" +
                    "                \"title\": \"نبيل العوضي - سلسلة قصص القرآن الكريم | قصة البقرة وهاروت وماروت\",\n" +
                    "                \"description\": \"قصة البقرة وهاروت وماروت من سلسلة دروس قصص القرآن الكريم للشيخ نبيل العوضي لمشاهدة المزيد من سلسلة قصص القر...\",\n" +
                    "                \"thumbnails\": {\n" +
                    "                    \"default\": {\n" +
                    "                        \"url\": \"https://i.ytimg.com/vi/PzN4Sm5nYsc/default.jpg\",\n" +
                    "                        \"width\": 120,\n" +
                    "                        \"height\": 90\n" +
                    "                    },\n" +
                    "                    \"medium\": {\n" +
                    "                        \"url\": \"https://i.ytimg.com/vi/PzN4Sm5nYsc/mqdefault.jpg\",\n" +
                    "                        \"width\": 320,\n" +
                    "                        \"height\": 180\n" +
                    "                    },\n" +
                    "                    \"high\": {\n" +
                    "                        \"url\": \"https://i.ytimg.com/vi/PzN4Sm5nYsc/hqdefault.jpg\",\n" +
                    "                        \"width\": 480,\n" +
                    "                        \"height\": 360\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                \"channelTitle\": \"الشيخ نبيل العوضي\",\n" +
                    "                \"liveBroadcastContent\": \"none\",\n" +
                    "                \"publishTime\": \"2019-02-13T14:00:02Z\"\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"kind\": \"youtube#searchResult\",\n" +
                    "            \"etag\": \"p-HURAgF4ImI9GTXz7u-xbQTE4o\",\n" +
                    "            \"id\": {\n" +
                    "                \"kind\": \"youtube#video\",\n" +
                    "                \"videoId\": \"ukYWnDkukgc\"\n" +
                    "            },\n" +
                    "            \"snippet\": {\n" +
                    "                \"publishedAt\": \"2019-02-15T14:00:11Z\",\n" +
                    "                \"channelId\": \"UC1NuE6xeMyyTCHq0sKjJ5IQ\",\n" +
                    "                \"title\": \"نبيل العوضي - سلسلة قصص القرآن الكريم | قصة قابيل وهابيل وأصحاب السبت\",\n" +
                    "                \"description\": \"قصة قابيل وهابيل وأصحاب السبت من سلسلة دروس قصص القرآن الكريم للشيخ نبيل العوضي لمشاهدة المزيد من سلسلة...\",\n" +
                    "                \"thumbnails\": {\n" +
                    "                    \"default\": {\n" +
                    "                        \"url\": \"https://i.ytimg.com/vi/ukYWnDkukgc/default.jpg\",\n" +
                    "                        \"width\": 120,\n" +
                    "                        \"height\": 90\n" +
                    "                    },\n" +
                    "                    \"medium\": {\n" +
                    "                        \"url\": \"https://i.ytimg.com/vi/ukYWnDkukgc/mqdefault.jpg\",\n" +
                    "                        \"width\": 320,\n" +
                    "                        \"height\": 180\n" +
                    "                    },\n" +
                    "                    \"high\": {\n" +
                    "                        \"url\": \"https://i.ytimg.com/vi/ukYWnDkukgc/hqdefault.jpg\",\n" +
                    "                        \"width\": 480,\n" +
                    "                        \"height\": 360\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                \"channelTitle\": \"الشيخ نبيل العوضي\",\n" +
                    "                \"liveBroadcastContent\": \"none\",\n" +
                    "                \"publishTime\": \"2019-02-15T14:00:11Z\"\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"kind\": \"youtube#searchResult\",\n" +
                    "            \"etag\": \"H_tdLPfwSoIvxhJh7ogkL6fN8Sk\",\n" +
                    "            \"id\": {\n" +
                    "                \"kind\": \"youtube#video\",\n" +
                    "                \"videoId\": \"HIRh0lzP3qM\"\n" +
                    "            },\n" +
                    "            \"snippet\": {\n" +
                    "                \"publishedAt\": \"2019-01-04T14:00:02Z\",\n" +
                    "                \"channelId\": \"UC1NuE6xeMyyTCHq0sKjJ5IQ\",\n" +
                    "                \"title\": \"نبيل العوضي - سلسلة روائع القصص | قصة إبن الملك في دمشق\",\n" +
                    "                \"description\": \"قصة إبن الملك في دمشق من سلسلة دروس روائع القصص للشيخ نبيل العوضي لمشاهدة المزيد من سلسلة روائع القصص للشيخ...\",\n" +
                    "                \"thumbnails\": {\n" +
                    "                    \"default\": {\n" +
                    "                        \"url\": \"https://i.ytimg.com/vi/HIRh0lzP3qM/default.jpg\",\n" +
                    "                        \"width\": 120,\n" +
                    "                        \"height\": 90\n" +
                    "                    },\n" +
                    "                    \"medium\": {\n" +
                    "                        \"url\": \"https://i.ytimg.com/vi/HIRh0lzP3qM/mqdefault.jpg\",\n" +
                    "                        \"width\": 320,\n" +
                    "                        \"height\": 180\n" +
                    "                    },\n" +
                    "                    \"high\": {\n" +
                    "                        \"url\": \"https://i.ytimg.com/vi/HIRh0lzP3qM/hqdefault.jpg\",\n" +
                    "                        \"width\": 480,\n" +
                    "                        \"height\": 360\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                \"channelTitle\": \"الشيخ نبيل العوضي\",\n" +
                    "                \"liveBroadcastContent\": \"none\",\n" +
                    "                \"publishTime\": \"2019-01-04T14:00:02Z\"\n" +
                    "            }\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"kind\": \"youtube#searchResult\",\n" +
                    "            \"etag\": \"RkcI97Yz8QsRIhi5UtLLGPrr30Q\",\n" +
                    "            \"id\": {\n" +
                    "                \"kind\": \"youtube#video\",\n" +
                    "                \"videoId\": \"XPw3HSvQIvY\"\n" +
                    "            },\n" +
                    "            \"snippet\": {\n" +
                    "                \"publishedAt\": \"2020-02-03T11:00:07Z\",\n" +
                    "                \"channelId\": \"UC1NuE6xeMyyTCHq0sKjJ5IQ\",\n" +
                    "                \"title\": \"روائع نبيل العوضي |  قصه زواج فتاه امريكيه من مسلم\",\n" +
                    "                \"description\": \"اشترك في قناة دروس الشيخ نبيل العوضي لتصلك جميع الدروس والمحاضرات http://bit.ly/DoroosAlAwadi روائع القصص للشيخ نبيل...\",\n" +
                    "                \"thumbnails\": {\n" +
                    "                    \"default\": {\n" +
                    "                        \"url\": \"https://i.ytimg.com/vi/XPw3HSvQIvY/default.jpg\",\n" +
                    "                        \"width\": 120,\n" +
                    "                        \"height\": 90\n" +
                    "                    },\n" +
                    "                    \"medium\": {\n" +
                    "                        \"url\": \"https://i.ytimg.com/vi/XPw3HSvQIvY/mqdefault.jpg\",\n" +
                    "                        \"width\": 320,\n" +
                    "                        \"height\": 180\n" +
                    "                    },\n" +
                    "                    \"high\": {\n" +
                    "                        \"url\": \"https://i.ytimg.com/vi/XPw3HSvQIvY/hqdefault.jpg\",\n" +
                    "                        \"width\": 480,\n" +
                    "                        \"height\": 360\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                \"channelTitle\": \"الشيخ نبيل العوضي\",\n" +
                    "                \"liveBroadcastContent\": \"none\",\n" +
                    "                \"publishTime\": \"2020-02-03T11:00:07Z\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}" +
                    "";
        }

        //this will be called once to load the initial data
        @Override
        public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull final PageKeyedDataSource.LoadInitialCallback<String, Video> callback) {
            Log.d(TAG, "loadInitial: ");
            RetrofitClient.getInstance()
                    .getApi().getVideos(DEVELOPER_KEY, PART, CHANNEL_ID, MAX_RESULT)
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
                                VideoResponse videoResponse = VideoUtil.fromResponse(TEST_RESPONSE());
                                callback.onResult(videoResponse.getVideos(), videoResponse.getPrevPageToken(), videoResponse.getNextPageToken());
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e(TAG, "onFailure: ", t);
                            VideoResponse videoResponse = VideoUtil.fromResponse(TEST_RESPONSE());
                            callback.onResult(videoResponse.getVideos(), videoResponse.getPrevPageToken(), videoResponse.getNextPageToken());
                        }
                    });
        }

        //this will load the previous page
        @Override
        public void loadBefore(@NonNull final PageKeyedDataSource.LoadParams<String> params, @NonNull final PageKeyedDataSource.LoadCallback<String, Video> callback) {
            Log.d(TAG, "loadBefore: ");
            RetrofitClient.getInstance()
                    .getApi().getPageVideos(DEVELOPER_KEY, PART, CHANNEL_ID, MAX_RESULT, params.key)
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
                            VideoResponse videoResponse = VideoUtil.fromResponse(TEST_RESPONSE());
                            callback.onResult(videoResponse.getVideos(), videoResponse.getPrevPageToken());

                        }
                    });
        }

        //this will load the next page
        @Override
        public void loadAfter(@NonNull final PageKeyedDataSource.LoadParams<String> params, @NonNull final PageKeyedDataSource.LoadCallback<String, Video> callback) {
            Log.d(TAG, "loadAfter: ");

            RetrofitClient.getInstance()
                    .getApi().getPageVideos(DEVELOPER_KEY, PART, CHANNEL_ID, MAX_RESULT, params.key)
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
