package com.android.andoop.inlineplay.player;

public enum ScalableType {
    NONE,
    //Scale to fit
    FIT_XY,
    FIT_START,
    FIT_CENTER,
    FIT_END,
    //No Scale
    LEFT_TOP,
    LEFT_CENTER,
    LEFT_BOTTOM,
    CENTER_TOP,
    CENTER,
    CENTER_BOTTOM,
    RIGHT_TOP,
    RIGHT_CENTER,
    RIGHT_BOTTOM,
    //Crop
    LEFT_TOP_CROP,
    LEFT_CENTER_CROP,
    LEFT_BOTTOM_CROP,
    CENTER_TOP_CROP,
    CENTER_CROP,
    CENTER_BOTTOM_CROP,
    RIGHT_TOP_CROP,
    RIGHT_CENTER_CROP,
    RIGHT_BOTTOM_CROP,
    //Scale Inside
    START_INSIDE,
    CENTER_INSIDE,
    END_INSIDE
}
