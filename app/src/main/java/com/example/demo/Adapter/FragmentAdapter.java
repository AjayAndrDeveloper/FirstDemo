package com.example.demo.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.demo.Fragments.ImageFragment;
import com.example.demo.Fragments.TextFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTab;

    public FragmentAdapter(@NonNull FragmentManager fm, Context context, int totalTab) {
        super(fm);
        this.context = context;
        this.totalTab = totalTab;
    }

    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

       switch (position){
           case 0:
               return new TextFragment();
           case 1:
               return new ImageFragment();
       }
       return null;
    }

    @Override
    public int getCount() {
        return totalTab;
    }
}
