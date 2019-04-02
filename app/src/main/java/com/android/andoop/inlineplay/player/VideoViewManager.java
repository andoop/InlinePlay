package com.android.andoop.inlineplay.player;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class VideoViewManager implements IVideoView {
    private static VideoViewManager INSTANCE;
    private TextureVideoView videoView;
    private VideoViewHolder videoViewHolder;

    private VideoViewManager(Context context) {
        videoView = new TextureVideoView(context);
    }

    public static VideoViewManager getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (VideoViewManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new VideoViewManager(context);
                }
            }
        }
        return INSTANCE;
    }

    public void setVideoViewHolder(VideoViewHolder videoViewHolder) {
        if (this.videoViewHolder != null && this.videoViewHolder != videoViewHolder) {
            this.videoViewHolder.getHolder().removeView(videoView);
            this.videoViewHolder.onVideoViewDetached();
        }
        if (videoView.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) videoView.getParent();
            viewGroup.removeView(videoView);
        }
        this.videoViewHolder = videoViewHolder;
        this.videoViewHolder.getHolder().addView(videoView, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        this.videoViewHolder.onVideoViewAttached();
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        videoView.setDataSource(dataSource);
    }

    @Override
    public void playVideo() {
        videoView.playVideo();
    }

    @Override
    public void stopVideo() {
        videoView.stopVideo();
    }

    @Override
    public void pauseVideo() {
        videoView.pauseVideo();
    }

    @Override
    public boolean isPlaying() {
        return videoView.isPlaying();
    }

    public interface VideoViewHolder {
        FrameLayout getHolder();

        void onVideoViewAttached();

        void onVideoViewDetached();
    }

}
