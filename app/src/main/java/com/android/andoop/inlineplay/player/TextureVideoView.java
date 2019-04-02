package com.android.andoop.inlineplay.player;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;

import com.android.andoop.inlineplay.player.mediaplayer.MediaPlayerCore;

class TextureVideoView extends TextureView implements IVideoView {
    private XPlayer player = new MediaPlayerCore(getContext());

    public TextureVideoView(Context context) {
        super(context);
        init();
    }

    public TextureVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setSurfaceTextureListener(new SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
                player.setSurface(new Surface(surfaceTexture));
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

            }
        });
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        player.setDataSource(dataSource);
    }

    @Override
    public void playVideo() {
        player.setOnPreparedListener(new XPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(XPlayer mp) {
                player.start();
            }
        });
        player.prepare();
    }

    @Override
    public void stopVideo() {
        player.stop();
    }

    @Override
    public void pauseVideo() {
        player.pause();
    }

    @Override
    public boolean isPlaying() {
        return player.isPlaying();
    }
}
