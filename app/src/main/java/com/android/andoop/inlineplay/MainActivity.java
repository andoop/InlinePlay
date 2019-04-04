package com.android.andoop.inlineplay;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.andoop.inlineplay.player.DataSource;
import com.android.andoop.inlineplay.player.InlinePlayHelper;
import com.android.andoop.inlineplay.player.ScalableType;
import com.android.andoop.inlineplay.player.XVideoView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<DataSource> data = new ArrayList<>();
    private InlinePlayHelper inlinePlayHelper = new InlinePlayHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter<VideoItemHolder>() {
            @NonNull
            @Override
            public VideoItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new VideoItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull VideoItemHolder holder, int position) {
                holder.onBind(data.get(position));
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        });
        inlinePlayHelper.setRecyclerView(recyclerView);
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                inlinePlayHelper.update();
            }
        }, 100);

    }

    private void initData() {
        int[] raws = new int[]{
                R.raw.video1,
                R.raw.video2,
                R.raw.video3,
                R.raw.video4,
                R.raw.video5,
                R.raw.video6,
                R.raw.video7,
                R.raw.video8,
                R.raw.video9,
                R.raw.video10
        };
        data.clear();
        for (int i = 0; i < raws.length; i++) {
            data.add(new DataSource("视频" + i, Uri.parse("android.resource://" + getPackageName() + "/" + raws[i]).toString()));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        inlinePlayHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        inlinePlayHelper.onPause();
    }

    @Override
    protected void onDestroy() {
        inlinePlayHelper.onDestroy();
        super.onDestroy();
    }

    private class VideoItemHolder extends RecyclerView.ViewHolder {
        XVideoView videoView;
        TextView title;
        DataSource dataSource;

        VideoItemHolder(View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video_view);
            videoView.setAspectRatio(16/9f);
            videoView.setScalebleType(ScalableType.FIT_CENTER);
            title = itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FullScreenActivity.openInFullScreen(view.getContext(),dataSource,true);
                }
            });
        }

        void onBind(DataSource dataSource) {
            this.dataSource = dataSource;
            videoView.setDataSource(dataSource);
        }
    }
}
