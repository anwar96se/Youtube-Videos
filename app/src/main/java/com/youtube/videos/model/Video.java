package com.youtube.videos.model;

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
}
