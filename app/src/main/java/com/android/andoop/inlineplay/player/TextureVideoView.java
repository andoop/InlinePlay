package com.android.andoop.inlineplay.player;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;

import com.android.andoop.inlineplay.player.mediaplayer.MediaPlayerCore;

class TextureVideoView extends TextureView implements IVideoView {
    private XPlayer player = new MediaPlayerCore(getContext());
    private ScalableType scalableType = ScalableType.FIT_XY;
    private Size videoSize;
    private Size viewSize;
    private SurfaceTexture cachedSurfaceTexture;
    private boolean isReleased = true;

    public TextureVideoView(Context context) {
        super(context);
        init();
    }

    public TextureVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        player.setOnVideoSizeChangedListener(new XPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(XPlayer mp, int w, int h) {
                videoSize = new Size(w, h);
                updateScale();
            }
        });
        setSurfaceTextureListener(new SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
                if (isReleased) {
                    cachedSurfaceTexture = surfaceTexture;
                } else if (cachedSurfaceTexture != null) {
                    TextureVideoView.this.setSurfaceTexture(cachedSurfaceTexture);
                    updateScale();
                }
                isReleased = false;
                player.setSurface(new Surface(cachedSurfaceTexture));
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
                viewSize = new Size(i, i1);
                updateScale();
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

    private void updateScale() {
        if (videoSize != null) {
            viewSize = new Size(getWidth(), getHeight());
            setTransform(new ScaleManager(viewSize, videoSize).getScaleMatrix(scalableType));
            requestLayout();
            invalidate();
        }
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
        isReleased = true;
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

    @Override
    public void setScalebleType(ScalableType scalebleType) {
        this.scalableType = scalebleType;
        updateScale();
    }
}
