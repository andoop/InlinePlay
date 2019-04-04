package com.android.andoop.inlineplay.player;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class XVideoView extends AspectRatioFrameLayout implements IVideoView, VideoViewManager.VideoViewHolder {
    private VideoViewManager videoViewManager;
    private boolean isVideoViewAttached;
    private DataSource dataSource;
    private ScalableType scalebleType;

    public XVideoView(Context context) {
        super(context);
        init();
    }

    public XVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setTag("video_view");
        videoViewManager = VideoViewManager.getInstance(getContext());
    }

    @Override
    public FrameLayout getHolder() {
        return this;
    }

    @Override
    public void onVideoViewAttached() {
        isVideoViewAttached = true;
    }

    @Override
    public void onVideoViewDetached() {
        isVideoViewAttached = false;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void foucus() {
        videoViewManager.setVideoViewHolder(this);
    }

    @Override
    public void playVideo() {
        videoViewManager.setVideoViewHolder(this);
        videoViewManager.setDataSource(dataSource);
        if (scalebleType != null) {
            videoViewManager.setScalebleType(scalebleType);
        }
        videoViewManager.playVideo();
    }

    @Override
    public void stopVideo() {
        if (isVideoViewAttached) {
            videoViewManager.stopVideo();
        }
    }

    @Override
    public void pauseVideo() {
        if (isVideoViewAttached) {
            videoViewManager.pauseVideo();
        }
    }

    @Override
    public boolean isPlaying() {
        if (isVideoViewAttached) {
            return videoViewManager.isPlaying();
        }
        return false;
    }

    @Override
    public void setScalebleType(ScalableType scalebleType) {
        this.scalebleType = scalebleType;
        if (isVideoViewAttached) {
            videoViewManager.setScalebleType(scalebleType);
        }
    }

}
