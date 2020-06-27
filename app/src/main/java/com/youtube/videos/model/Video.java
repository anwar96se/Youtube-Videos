package com.youtube.videos.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Video implements Parcelable {

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
    private String id, name, desc, thumbnail;

    public Video() {
    }

    public Video(String id, String name, String desc, String thumbnail) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.thumbnail = thumbnail;
    }

    protected Video(Parcel in) {
        id = in.readString();
        name = in.readString();
        desc = in.readString();
        thumbnail = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeString(thumbnail);
    }
}
