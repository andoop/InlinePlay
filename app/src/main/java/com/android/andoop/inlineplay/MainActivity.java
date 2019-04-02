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

import com.android.andoop.inlineplay.data.DataUtils;
import com.android.andoop.inlineplay.player.DataSource;
import com.android.andoop.inlineplay.player.InlinePlayHelper;
import com.android.andoop.inlineplay.player.XVideoView;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<DataSource> data;
    private InlinePlayHelper inlinePlayHelper = new InlinePlayHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        data = Arrays.asList(DataUtils.URL_LIST);
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

    @Override
    protected void onPause() {
        super.onPause();
        inlinePlayHelper.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        inlinePlayHelper.onResume();
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
///mnt/sdcard/DCIM/Camera/VID_20190313_175759.mp4
        VideoItemHolder(View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video_view);
            title = itemView.findViewById(R.id.title);
        }

        void onBind(DataSource dataSource) {
            this.dataSource = dataSource;
            String ss = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video11).toString();
            dataSource.setUrl(new File(ss).getAbsolutePath());
            videoView.setDataSource(dataSource);
        }
    }
}
