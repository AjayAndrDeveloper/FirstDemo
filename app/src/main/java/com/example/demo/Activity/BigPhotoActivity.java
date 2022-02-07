package com.example.demo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.demo.Adapter.BigScreenAdapter;
import com.example.demo.Adapter.ViewPagerAdapter;
import com.example.demo.Model.ImageByFolderModel;
import com.example.demo.R;

import java.util.ArrayList;

public class BigPhotoActivity extends AppCompatActivity {
        ViewPager viewPager;
        ArrayList<ImageByFolderModel> pics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_photo);
        viewPager = findViewById(R.id.bigScreen);
        Intent intent = getIntent();
        pics = (ArrayList<ImageByFolderModel>) intent.getSerializableExtra("list");
        int position = intent.getIntExtra("position",0);
        BigScreenAdapter bigScreenAdapter = new BigScreenAdapter(pics,this);
        viewPager.setAdapter(bigScreenAdapter);
        viewPager.setCurrentItem(position);

    }
}