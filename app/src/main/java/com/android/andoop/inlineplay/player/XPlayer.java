package com.android.andoop.inlineplay.player;

import android.view.Surface;

public abstract class XPlayer {
    public abstract void setDataSource(DataSource dataSource);

    public abstract void setSurface(Surface surface);

    public abstract void prepare();

    public abstract void setVolume(float volume);

    public abstract void start();

    public abstract void pause();

    public abstract void stop();

    public abstract void release();

    public abstract void seek(int msec);

    public abstract boolean isPlaying();

    public interface OnPreparedListener {
        void onPrepared(XPlayer mp);
    }


    public void setOnPreparedListener(OnPreparedListener listener) {
        mOnPreparedListener = listener;
    }

    protected OnPreparedListener mOnPreparedListener;


    public interface OnCompletionListener {
        void onCompletion(XPlayer mp);
    }

    public void setOnCompletionListener(OnCompletionListener listener) {
        mOnCompletionListener = listener;
    }

    protected OnCompletionListener mOnCompletionListener;

    public interface OnBufferingUpdateListener {
        void onBufferingUpdate(XPlayer mp, int percent);
    }

    public void setOnBufferingUpdateListener(OnBufferingUpdateListener listener) {
        mOnBufferingUpdateListener = listener;
    }

    protected OnBufferingUpdateListener mOnBufferingUpdateListener;

    public interface OnSeekCompleteListener {
        void onSeekComplete(XPlayer mp);
    }

    public void setOnSeekCompleteListener(OnSeekCompleteListener listener) {
        mOnSeekCompleteListener = listener;
    }

    protected OnSeekCompleteListener mOnSeekCompleteListener;

    public interface OnErrorListener {
        boolean onError(XPlayer mp, int what, int extra);
    }

    public void setOnErrorListener(OnErrorListener listener) {
        mOnErrorListener = listener;
    }

    protected OnErrorListener mOnErrorListener;

    public interface OnInfoListener {
        void onInfo(XPlayer mp, int what, int arg1, int arg2);
    }

    public void setOnInfoListener(OnInfoListener listener) {
        this.mOnInfoListener = listener;
    }

    protected OnInfoListener mOnInfoListener;

    public interface OnTickListener {
        void onTick(XPlayer mp);
    }

    protected OnTickListener mOnTickListener;

    public void setOnTickListener(OnTickListener onTickListener) {
        this.mOnTickListener = onTickListener;
    }

    protected OnVideoSizeChangedListener mOnVideoSizeChangedListener;

    public interface OnVideoSizeChangedListener {
        void onVideoSizeChanged(XPlayer mp, int w, int h);
    }

    public void setOnVideoSizeChangedListener(OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.mOnVideoSizeChangedListener = onVideoSizeChangedListener;
    }
}
