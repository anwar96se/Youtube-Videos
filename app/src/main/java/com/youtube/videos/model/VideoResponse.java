package com.youtube.videos.model;

import java.util.List;

public class VideoResponse {

    private String nextPageToken;
    private String prevPageToken;
    private List<Video> videos;

    public String getNextPageToken() {
        return nextPageToken;
    }

    public String getPrevPageToken() {
        return prevPageToken;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public void setPrevPageToken(String prevPageToken) {
        this.prevPageToken = prevPageToken;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
