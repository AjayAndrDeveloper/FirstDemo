package com.example.demo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.Adapter.GalleryAdapter;
import com.example.demo.Listners.FolderListener;
import com.example.demo.Model.ImgFolderModel;
import com.example.demo.R;
import com.example.demo.Utilies.ImageGallery;

import java.util.ArrayList;

public   class GalleryActivity extends AppCompatActivity implements FolderListener {
        RecyclerView recyclerView;
        GalleryAdapter galleryAdapter;
        ArrayList<ImgFolderModel> imageArray;
        TextView galleryNumber;
        private  static final int MY_READ_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        recyclerView = findViewById(R.id.galleryRecyclerView);
        galleryNumber  = findViewById(R.id.galleryNumber);

          if(ContextCompat.checkSelfPermission(GalleryActivity.this,
                  Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
              ActivityCompat.requestPermissions(GalleryActivity.this,
                      new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_READ_PERMISSION_CODE);

          }else {
              LoadImage();
          }
        recyclerView.setAdapter(galleryAdapter);
          if (imageArray.size()>0){
          galleryNumber.setText("Photos +("+imageArray.size()+")");}

    }

    private void LoadImage() {
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        imageArray = ImageGallery.listOfImages(this);
        galleryAdapter = new GalleryAdapter(imageArray,this,this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==MY_READ_PERMISSION_CODE){
            if (grantResults[0]== PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "PERMISSION GRANTED    ", Toast.LENGTH_SHORT).show();
                LoadImage();
            }
            else {
                Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onPhotoClick(String path,String folderName,int folderSize) {
//        galleryAdapter.setData(imageArray,false);
        Toast.makeText(this, "  " + folderName, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,ImageDisplayActivity.class);
        intent.putExtra("path",path);
        intent.putExtra("folderName",folderName);
        intent.putExtra("folderSize",folderSize);
        startActivity(intent);
            }

}