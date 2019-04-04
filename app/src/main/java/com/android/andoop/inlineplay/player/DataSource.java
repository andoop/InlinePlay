package com.android.andoop.inlineplay.player;

import android.os.Parcel;
import android.os.Parcelable;

public class DataSource implements Parcelable {
    private String title;
    private String url;

    public DataSource(String title, String url) {
        this.title = title;
        this.url = url;
    }

    protected DataSource(Parcel in) {
        title = in.readString();
        url = in.readString();
    }

    public static final Creator<DataSource> CREATOR = new Creator<DataSource>() {
        @Override
        public DataSource createFromParcel(Parcel in) {
            return new DataSource(in);
        }

        @Override
        public DataSource[] newArray(int size) {
            return new DataSource[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(url);
    }
}
