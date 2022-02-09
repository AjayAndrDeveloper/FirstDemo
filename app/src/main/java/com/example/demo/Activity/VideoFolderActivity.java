package com.example.demo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.demo.Adapter.VFolderListAdapter;
import com.example.demo.Model.VideoFolderModel;
import com.example.demo.R;
import com.example.demo.Utilies.VideoGallery;

import java.util.ArrayList;

public class VideoFolderActivity extends AppCompatActivity {
        RecyclerView recyclerView;
    private  static final int MY_READ_PERMISSION_CODE = 101;
    ArrayList<VideoFolderModel> videoList = new ArrayList<>();
    VFolderListAdapter vFolderListAdapter;
String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_folder);
        VideoListByFolder.abc = false;
        recyclerView = findViewById(R.id.videoFolderRecyclerView);
        if(ContextCompat.checkSelfPermission(VideoFolderActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(VideoFolderActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_READ_PERMISSION_CODE);

        }else {
            LoadFolders();
        }
        recyclerView.setAdapter(vFolderListAdapter);
    }

    private void LoadFolders() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        videoList = VideoGallery.getVideos(path,this);
        vFolderListAdapter = new VFolderListAdapter(videoList,this);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==MY_READ_PERMISSION_CODE){
            if (grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "PERMISSION GRANTED    ", Toast.LENGTH_SHORT).show();
                LoadFolders();
            }
            else {
                Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }

        }
}}