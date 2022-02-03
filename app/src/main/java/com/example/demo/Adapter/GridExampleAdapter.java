package com.example.demo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo.R;

import java.util.ArrayList;


public class GridExampleAdapter extends RecyclerView.Adapter<GridExampleAdapter.ExampleViewHolder> {
    ArrayList<String> gridImages = new ArrayList<>();
    Context context;

    public GridExampleAdapter(ArrayList<String> gridImages, Context context) {
        this.gridImages = gridImages;
        this.context = context;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.staggered_layout,parent,false);
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        Glide.with(context).load(gridImages.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return gridImages.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.staggeredImage);

        }
    }
}
