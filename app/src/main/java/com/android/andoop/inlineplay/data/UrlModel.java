package com.android.andoop.inlineplay.data;

public class UrlModel {
    public final String mName;
    public final String mUrl;
    public boolean mSelected;

    public UrlModel(final String name, final String url) {
        mName = name;
        mUrl = url;
    }

    public void setSelected(boolean selected) {
        this.mSelected = selected;
    }
}
