package com.android.andoop.inlineplay.player.mediaplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.Surface;

import com.android.andoop.inlineplay.player.DataSource;
import com.android.andoop.inlineplay.player.Error;
import com.android.andoop.inlineplay.player.XPlayer;

import java.io.IOException;

public class MediaPlayerCore extends XPlayer implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Context context;

    public MediaPlayerCore(Context context) {
        this.context = context;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        release();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnErrorListener(this);
        try {
            mediaPlayer.setDataSource(context, Uri.parse(dataSource.getUrl()));
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
    public void prepare() {
        mediaPlayer.prepareAsync();
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
        mediaPlayer.reset();
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

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        Log.i("aaa", i + ":" + i1);
        return false;
    }
}
