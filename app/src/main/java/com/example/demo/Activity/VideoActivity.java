package com.example.demo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.VideoView;

import com.example.demo.R;

public class VideoActivity extends AppCompatActivity {
        VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoView = findViewById(R.id.videoView2);
        Intent intent = getIntent();
       String videoPath= intent.getStringExtra("video");
        videoView.setVideoPath(videoPath);
        videoView.start();

    }
}