package com.android.andoop.inlineplay.player.mediaplayer;

import android.media.MediaPlayer;
import android.view.Surface;

import com.android.andoop.inlineplay.player.DataSource;
import com.android.andoop.inlineplay.player.Error;
import com.android.andoop.inlineplay.player.XPlayer;

import java.io.IOException;

public class MediaPlayerCore extends XPlayer implements MediaPlayer.OnPreparedListener {
    private MediaPlayer mediaPlayer = new MediaPlayer();

    public MediaPlayerCore() {
        mediaPlayer.setOnPreparedListener(this);
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        try {
            mediaPlayer.setDataSource(dataSource.getUrl());
        } catch (IOException e) {
            e.printStackTrace();
            if (mOnErrorListener != null) {
                mOnErrorListener.onError(this, Error.ERROR_IO, 0);
            }
        }
    }

    @Override
    public void setSurface(Surface surface) {
        mediaPlayer.setSurface(surface);
    }

    @Override
    public void setVolume(float volume) {
        mediaPlayer.setVolume(volume, volume);
    }

    @Override
    public void start() {
        mediaPlayer.start();
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
    }

    @Override
    public void release() {
        mediaPlayer.release();
    }

    @Override
    public void seek(int msec) {
        mediaPlayer.seekTo(msec);
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        if (mOnPreparedListener != null) {
            mOnPreparedListener.onPrepared(this);
        }
    }
}
