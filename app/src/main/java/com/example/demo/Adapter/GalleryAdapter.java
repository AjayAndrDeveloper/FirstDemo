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
import com.example.demo.Listners.FolderListener;
import com.example.demo.Model.ImgFolderModel;
import com.example.demo.R;

import java.util.ArrayList;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
    ArrayList<ImgFolderModel> imageOfArray = new ArrayList<>();
    Context context;
    protected FolderListener folderListener;
    boolean isGrid ;

    public GalleryAdapter(ArrayList<ImgFolderModel> imageOfArray, Context context, FolderListener folderListener,Boolean isGrid) {
        this.imageOfArray = imageOfArray;
        this.context = context;
        this.folderListener = folderListener;
        this.isGrid = isGrid;
    }
//  public void setData(ArrayList<GalleryModel> list,boolean isFolder){
//        imageOfArray =  new ArrayList<>();
//
//            imageOfArray.addAll(list);
//
//        notifyDataSetChanged();
//        this.isFolder = isFolder;
//  }
    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.gallery_image_layout,parent,false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        ImgFolderModel folders =  imageOfArray.get(position);
        String imagePath = imageOfArray.get(position).getImagePath();
        String folder = imageOfArray.get(position).getFolderName();
        int folderSize = imageOfArray.get(position).getNumberOfPics();
        String newline = System.lineSeparator();


         Log.d("realme", "onBindViewHolder: "   + imagePath);
//        if (isFolder){
            holder.textView.setText(folder);
//            holder.textView.setVisibility(View.VISIBLE);
//            holder.imageView.setVisibility(View.GONE);
//        }
//          else {
//              holder.textView.setVisibility(View.GONE);
             holder.imageView.setVisibility(View.VISIBLE);
             holder.textView.setText(folder +newline+"("+ folderSize+")");
             Glide.with(context).load(folders.getFirstImage()).into(holder.imageView);

//    }
//
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                folderListener.onPhotoClick(imagePath,folder,folderSize);
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
