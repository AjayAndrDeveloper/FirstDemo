package com.example.demo.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo.Listners.PhotoListner;
import com.example.demo.Model.GalleryModel;
import com.example.demo.R;

import java.util.ArrayList;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
    ArrayList<GalleryModel> imageOfArray = new ArrayList<>();
    Context context;
    protected PhotoListner photoListner;
    boolean isFolder = true;

    public GalleryAdapter(ArrayList<GalleryModel> imageOfArray, Context context, PhotoListner photoListner) {
        this.imageOfArray = imageOfArray;
        this.context = context;
        this.photoListner = photoListner;
    }
  public void setData(ArrayList<GalleryModel> list,boolean isFolder){
        imageOfArray =  new ArrayList<>();
        imageOfArray.addAll(list);
        notifyDataSetChanged();
        this.isFolder = isFolder;
  }
    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.gallery_image_layout,parent,false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        String image = imageOfArray.get(position).getImagePath();
        String folder = imageOfArray.get(position).getFolderName();
        Log.d("realme", "onBindViewHolder: "   + image);
        if (isFolder){holder.textView.setText(folder);
            holder.textView.setVisibility(View.VISIBLE);
            holder.imageView.setVisibility(View.GONE);
        }
          else {
//              holder.textView.setVisibility(View.GONE);
          holder.imageView.setVisibility(View.VISIBLE);
             Glide.with(context).load(image).into(holder.imageView);}

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoListner.onPhotoClick(image);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageOfArray.size() ;
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.galleryImage);
            textView = itemView.findViewById(R.id.folderName);


        }
    }
}
