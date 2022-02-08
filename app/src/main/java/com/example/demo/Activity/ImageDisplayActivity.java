package com.example.demo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.Adapter.ImageByFolderAdapter;
import com.example.demo.Listners.PhotosClickListener;
import com.example.demo.Model.ImageByFolderModel;
import com.example.demo.R;
import com.example.demo.Utilies.GetALLImagesByFolder;

import java.util.ArrayList;

public class ImageDisplayActivity extends AppCompatActivity implements PhotosClickListener {
        RecyclerView recyclerView;
        TextView folderHead;
        ArrayList<ImageByFolderModel> imageByFolderModelArrayList;
        GetALLImagesByFolder getALLImagesByFolder;
    String path;
    String folderName;
    int foldersize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        Intent intent = getIntent();
          path= intent.getStringExtra("path");
        folderName=  intent.getStringExtra("folderName");
        foldersize = intent.getIntExtra("folderSize",0);
      recyclerView= findViewById(R.id.photoRecyclerView);
      folderHead= findViewById(R.id.folderHead);
      folderHead.setText(folderName +  "  " +" (" + foldersize +")");
      imageByFolderModelArrayList = new ArrayList<>();
      recyclerView.setLayoutManager(new GridLayoutManager(this,3));
      imageByFolderModelArrayList =  GetALLImagesByFolder.imageBy(path,this);
      recyclerView.setAdapter(new ImageByFolderAdapter(imageByFolderModelArrayList,this,this));


    }

    @Override
    public void PhotoClickListener(ImageByFolderAdapter.FolderViewHolder holder, int position, ArrayList<ImageByFolderModel> pics) {
//        pictureBrowserFragment
        Intent intent = new Intent(this,BigPhotoActivity.class);
        intent.putExtra("list",pics);
        intent.putExtra("position",position);
        startActivity(intent);
//        Toast.makeText(this, "   "+ path +" "+ folderName, Toast.LENGTH_SHORT).show();
    }

}