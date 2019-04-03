package com.android.andoop.inlineplay.player;
class Size {

    private int mWidth;
    private int mHeight;

    public Size(int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    @Override
    public String toString() {
        return "Size{" + mWidth + "*" + mHeight +'}';
    }
}
