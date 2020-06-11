package com.youtube.videos.model;

import android.util.Log;

public class Video {

    private String id, name, desc, thumbnail;

    public Video() {
    }

    public Video(String id, String name, String desc, String thumbnail) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Video logInfo() {
        Log.i(getClass().getSimpleName(), "logInfo: " + toString());
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return id.equals(video.id) &&
                name.equals(video.name) &&
                desc.equals(video.desc) &&
                thumbnail.equals(video.thumbnail);
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
