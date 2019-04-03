package com.android.andoop.inlineplay.player;

public interface IVideoView {
    void setDataSource(DataSource dataSource);

    void playVideo();

    void stopVideo();

    void pauseVideo();

    boolean isPlaying();

    void setScalebleType(ScalableType scalebleType);
}
