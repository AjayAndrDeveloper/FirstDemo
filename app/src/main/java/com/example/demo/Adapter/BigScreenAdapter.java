package com.example.demo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.demo.Model.ImageByFolderModel;
import com.example.demo.R;

import java.util.ArrayList;

public class BigScreenAdapter extends PagerAdapter {
    ArrayList<ImageByFolderModel> photoList;
    Context context;

    public BigScreenAdapter(ArrayList<ImageByFolderModel> photoList, Context context) {
        this.photoList = photoList;
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.big_screen_layout,null);
        ImageView imageView = view.findViewById(R.id.imagesBig);
        Glide.with(view).load(photoList.get(position).getPicturePath()).into(imageView);
        container.addView(view);
        return view;

    }

    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
    }
}
