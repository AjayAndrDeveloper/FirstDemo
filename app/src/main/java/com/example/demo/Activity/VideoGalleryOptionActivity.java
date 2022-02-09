package com.example.demo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo.R;
import com.example.demo.Utilies.CommonIntent;

public class VideoGalleryOptionActivity extends AppCompatActivity {
        Button imageBTN,videoBTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_gallery_option);
        imageBTN = findViewById(R.id.button1);
        videoBTN = findViewById(R.id.button2);
        imageBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonIntent.intentActivity(VideoGalleryOptionActivity.this,GalleryActivity.class);

            }
        });
        videoBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonIntent.intentActivity(VideoGalleryOptionActivity.this,VideoFolderActivity.class);
            }
        });
    }
}