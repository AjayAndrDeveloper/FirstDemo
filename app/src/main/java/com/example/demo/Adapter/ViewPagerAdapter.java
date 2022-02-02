package com.example.demo.Adapter;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.demo.Model.Category;
import com.example.demo.R;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {
    public ViewPagerAdapter(ArrayList<Category> quotesList, Context context) {
        this.quotesList = quotesList;
        this.context = context;
    }

    ArrayList<Category> quotesList;
    Context context;

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.viewpage_layout,null);
        TextView textView = view.findViewById(R.id.textview2);
        textView.setText(quotesList.get(position).getQuotes());
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return quotesList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

//        super.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
