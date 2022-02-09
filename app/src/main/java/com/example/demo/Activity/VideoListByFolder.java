package com.example.demo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.demo.Adapter.VideoListAdapter;
import com.example.demo.Model.VideoFolderModel;
import com.example.demo.R;
import com.example.demo.Utilies.VideoGallery;

import java.util.ArrayList;

public class VideoListByFolder extends AppCompatActivity {
     RecyclerView recyclerView ;
     ArrayList<VideoFolderModel> arrayList = new ArrayList<>();
      public static Boolean abc = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list_by_folder);
        recyclerView = findViewById(R.id.videoListRecyclerView);
        Intent intent = getIntent();
        String path= intent.getStringExtra("videoList");
        arrayList = VideoGallery.getVideos(path,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new VideoListAdapter(arrayList,this));

    }
}