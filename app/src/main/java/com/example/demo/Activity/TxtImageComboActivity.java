package com.example.demo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.example.demo.Adapter.FragmentAdapter;
import com.example.demo.R;
import com.google.android.material.tabs.TabLayout;

public class TxtImageComboActivity extends AppCompatActivity {
        ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txt_image_combo);
        viewPager = findViewById(R.id.comboViewPage);
     tabLayout = findViewById(R.id.tabMode);
        tabLayout.addTab(tabLayout.newTab().setText("Text Quotes"));
        tabLayout.addTab(tabLayout.newTab().setText("Image Quotes"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());

//        FragmentAdapter fragmentAdapter =  new FragmentAdapter(getSupportFragmentManager());
        viewPager.setPageMargin(20);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
      tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
          @Override
          public void onTabSelected(TabLayout.Tab tab) {
              viewPager.setCurrentItem(tab.getPosition());
          }

          @Override
          public void onTabUnselected(TabLayout.Tab tab) {

          }

          @Override
          public void onTabReselected(TabLayout.Tab tab) {

          }
      });






    }
}