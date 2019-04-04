package com.android.andoop.inlineplay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.andoop.inlineplay.player.DataSource;
import com.android.andoop.inlineplay.player.ScalableType;
import com.android.andoop.inlineplay.player.XVideoView;

public class FullScreenActivity extends AppCompatActivity {
    private static String KEY_DATASOURCE = "data_source";
    private static String KEY_CONTINTUEPLAY = "continue_play";
    private DataSource dataSource;
    private boolean continuePlay;
    private XVideoView videoView;
    private boolean isPaused;

    public static void openInFullScreen(Context context, DataSource dataSource, boolean continuePlay) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DATASOURCE, dataSource);
        bundle.putBoolean(KEY_CONTINTUEPLAY, continuePlay);
        Intent intent = new Intent(context, FullScreenActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        videoView = findViewById(R.id.video_view);
        Bundle extras = getIntent().getExtras();
        dataSource = extras.getParcelable(KEY_DATASOURCE);
        continuePlay = extras.getBoolean(KEY_CONTINTUEPLAY, false);
        videoView.setScalebleType(ScalableType.FIT_CENTER);
        videoView.setDataSource(dataSource);
        if (continuePlay) {
            videoView.foucus();
        } else {
            videoView.playVideo();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isPaused) {
            isPaused = false;
            videoView.playVideo();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (videoView.isPlaying()) {
            isPaused = true;
            videoView.pauseVideo();
        }
    }
}
