package com.android.andoop.inlineplay.player;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class InlinePlayHelper extends RecyclerView.OnScrollListener {
    private static final String TAG = "InlinePlayHelper";
    private XVideoView playingView;
    private RecyclerView mRecyclerView;

    public void setRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        recyclerView.addOnScrollListener(this);
    }

    public void update() {
        if (playingView != null && playingView.isPlaying()) {
            playingView.stopVideo();
            playingView = null;
        }
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                findVideoViewAndPlay();
            }
        });

    }

    private void findVideoViewAndPlay() {
        XVideoView videoView = findVideoView(mRecyclerView);
        if (videoView != null && !videoView.isPlaying()) {
            videoView.playVideo();
            playingView = videoView;
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            Log.i(TAG, "onScrollStateChanged: idle");
            if (playingView != null && playingView.isPlaying()) {
                return;
            }
            findVideoViewAndPlay();
        }
    }

    private XVideoView findVideoView(RecyclerView recyclerView) {
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            if (recyclerView.getChildAt(i).findViewWithTag("video_view") instanceof XVideoView) {
                XVideoView view = recyclerView.getChildAt(i).findViewWithTag("video_view");
                if (moreThanHalfVisibility(recyclerView, view)) {
                    return view;
                }
            }
        }
        return null;
    }

    private boolean moreThanHalfVisibility(View parentView, View view) {
        Rect rectParent = new Rect();
        parentView.getGlobalVisibleRect(rectParent);
        Rect rectChild = new Rect();
        boolean visibleRect = view.getGlobalVisibleRect(rectChild);
        if (!visibleRect) {
            return false;
        }
        if (rectChild.bottom < rectParent.bottom) {
            return rectChild.bottom - rectParent.top > (view.getHeight()) / 2;
        } else {
            return rectParent.bottom - rectChild.top > (view.getHeight()) / 2;
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (playingView == null || !playingView.isPlaying()) {
            return;
        }
        if (!moreThanHalfVisibility(recyclerView, playingView)) {
            playingView.stopVideo();
        }
    }

    public void onPause() {
        if (playingView != null && playingView.isPlaying()) {
            playingView.stopVideo();
        }
    }

    public void onResume() {
        if(playingView!=null){
            playingView.foucus();
        }
        if (playingView != null && !playingView.isPlaying()) {
            playingView.playVideo();
        }
    }

    public void onDestroy() {
        onPause();
        playingView = null;
        mRecyclerView.removeOnScrollListener(this);
        mRecyclerView = null;
    }
}
