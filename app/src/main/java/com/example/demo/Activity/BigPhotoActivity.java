package com.example.demo.Activity;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.demo.Adapter.BigScreenAdapter;
import com.example.demo.Adapter.ImageListAdapter;
import com.example.demo.Adapter.ViewPagerAdapter;
import com.example.demo.Listners.CurrentItemListener;
import com.example.demo.Listners.ImageListener;
import com.example.demo.Model.ImageByFolderModel;
import com.example.demo.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class BigPhotoActivity extends AppCompatActivity implements CurrentItemListener, ImageListener {
    ViewPager viewPager;
    ArrayList<ImageByFolderModel> pics;
    RecyclerView recyclerView;
    int flag = 0;

    int position;

    int lastPos = 0;
    public static int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_photo);
        viewPager = findViewById(R.id.bigScreen);
        recyclerView = findViewById(R.id.imageListView);

        Intent intent = getIntent();
        pics = (ArrayList<ImageByFolderModel>) intent.getSerializableExtra("list");
        position = intent.getIntExtra("position", 0);
        currentPos = position;
        BigScreenAdapter bigScreenAdapter = new BigScreenAdapter(pics, this, this);
        viewPager.setAdapter(bigScreenAdapter);
        viewPager.setCurrentItem(position);
        viewPager.setPageMargin(40);


        ImageListAdapter imageListAdapter = new ImageListAdapter(pics, this, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);


        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.smoothScrollToPosition(position);
//        recyclerView.setScrollY(centeredItemPosition);
//        final LinearSnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);
//        recyclerView.setOnFlingListener(snapHelper);

        lastPos = position;
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(imageListAdapter);
        imageListAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(position);
//        recyclerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return false;
//            }
//       });

//        int firstPosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
//        int lastPosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
//        int middle=(firstPosition+lastPosition)/2;
//
//        Log.d("postion", "onCreate: "+middle+"firstPosition= "+firstPosition+" lastPosition="+lastPosition);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                imageListAdapter.notifyDataSetChanged(position);
//                recyclerView.getAdapter().notifyDataSetChanged();
//                recyclerView.scrollToPosition( position);


            }

            @Override
            public void onPageSelected(int position) {
                currentPos = position;
                imageListAdapter.notifyDataSetChanged();
//                    recyclerView.setScrollX(position);

                if (position > lastPos && position < pics.size() - 2) {

                    recyclerView.smoothScrollToPosition(position + 2);

                } else if (position < lastPos && position >= 2) {
                    recyclerView.smoothScrollToPosition(position - 2);
                } else {
                    recyclerView.smoothScrollToPosition(position);

                }

                lastPos = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void ClickOnCurrentItem(int position) {
        viewPager.setCurrentItem(position);

    }

    @Override
    public void setClickImage() {

        if (flag == 0) {
            recyclerView.setVisibility(View.GONE);
            flag++;
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            flag = 0;

        }
    }
}