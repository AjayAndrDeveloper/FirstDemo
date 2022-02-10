package com.example.demo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.demo.R;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {
    VideoView videoView;
    int position;
    ImageView backImage , lockImage;
    boolean flag = true,listener = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoView = findViewById(R.id.videoView2);
        backImage = findViewById(R.id.backBtn);
        lockImage = findViewById(R.id.lock);
         Intent intent = getIntent();
        String videoPath = intent.getStringExtra("video");
      position = intent.getIntExtra("position",0);
            ArrayList<String> PathList = intent.getStringArrayListExtra("PathList");
        videoView.setVideoPath(videoPath);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
//        if (PathList.size()>1) {
            mediaController.setPrevNextListeners(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Handle next click here
                    ++position;
                    videoView.setVideoPath(PathList.get(position));
                    videoView.start();
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Handle previous click here
                    --position;
                    videoView.setVideoPath(PathList.get(position));
                    videoView.start();
                }
            });
//        }
        videoView.setMediaController(mediaController);

        videoView.start();


        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
             ++position;
             videoView.setVideoPath(PathList.get(position));
                videoView.start();
            }
        });
//        videoView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//               if (listener) {
//                   mediaController.setVisibility(View.GONE);
//                   backImage.setVisibility(View.GONE);
//                   listener=false;
//               }else {
//                   mediaController.setVisibility(View.VISIBLE);
//                   backImage.setVisibility(View.VISIBLE);
//                   listener=true;
//               }
//                return true;
//            }
//        });
//        videoView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener) {
////                   mediaController.setVisibility(View.GONE);
//                   backImage.setVisibility(View.GONE);
//                   listener=false;
//               }else {
////                   mediaController.setVisibility(View.VISIBLE);
//                   backImage.setVisibility(View.VISIBLE);
//                   listener=true;
//               }
//            }
//        });
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        lockImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    mediaController.setVisibility(View.GONE);
                    backImage.setVisibility(View.GONE);
                    lockImage.setImageResource(R.drawable.ic_baseline_lock_24);
                    flag =false ;
                }else {
                    mediaController.setVisibility(View.VISIBLE);
                    backImage.setVisibility(View.VISIBLE);
                    lockImage.setImageResource(R.drawable.ic_baseline_lock_open_24);
                    flag =true ;
                }
            }
        });
    }

//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//
//        return super.dispatchKeyEvent(event);
//
//    }


    @Override
    public void onBackPressed() {
        if (flag){
        super.onBackPressed();
    }else {
            Toast.makeText(VideoActivity.this, "Your Screen is lock", Toast.LENGTH_SHORT).show();
        }
}}