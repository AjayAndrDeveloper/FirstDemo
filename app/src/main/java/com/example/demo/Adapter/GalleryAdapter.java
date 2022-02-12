package com.example.demo.Adapter;

import static java.lang.Boolean.TRUE;

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


public class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM_TYPE_GRID = 0;
    public static final int ITEM_TYPE_LINEAR = 1;
    private int VIEW_TYPE = 0;
    ArrayList<ImgFolderModel> imageOfArray = new ArrayList<>();
    Context context;
    protected FolderListener folderListener;
    boolean isGrid;
    int viewType = 0;

    public GalleryAdapter(ArrayList<ImgFolderModel> imageOfArray, Context context, FolderListener folderListener, Boolean isGrid) {
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        Log.d("isgrid", "onCreateViewHolder: " + isGrid);
//
//
        if (isGrid) {
            view = layoutInflater.inflate(R.layout.gallery_image_grid_layout, parent, false);
            return new GalleryGridViewHolder(view);
        }

        else {
            view = layoutInflater.inflate(R.layout.gallery_image_linear_layout, parent, false);
            return new GalleryLinearViewHolder(view);
        }
}

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImgFolderModel folders = imageOfArray.get(position);
        String imagePath = imageOfArray.get(position).getImagePath();
        String folder = imageOfArray.get(position).getFolderName();
        int folderSize = imageOfArray.get(position).getNumberOfPics();
        String newline = System.lineSeparator();

        if (isGrid) {
                 GalleryGridViewHolder viewHolder = (GalleryGridViewHolder) holder;
                viewHolder.textView.setText(folder);
                viewHolder.textView.setText(folder + newline + "(" + folderSize + ")");
                Glide.with(context).load(folders.getFirstImage()).into(viewHolder.imageView);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        folderListener.onPhotoClick(imagePath, folder, folderSize);
                    }
                });

        } else {
//
                GalleryLinearViewHolder viewHolder = (GalleryLinearViewHolder) holder;
                viewHolder.textView1.setText(folder);
                viewHolder.textView1.setText(folder + newline + "(" + folderSize + ")");
                Glide.with(context).load(folders.getFirstImage()).into(viewHolder.imageView1);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        folderListener.onPhotoClick(imagePath, folder, folderSize);
                    }
                });

        }
//
    }

//    @Override
//    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder, int position) {
//         Log.d("realme", "onBindViewHolder: "   + imagePath);
////        if (isFolder){
////            holder.textView.setVisibility(View.VISIBLE);
////            holder.imageView.setVisibility(View.GONE);
////        }
////          else {
////              holder.textView.setVisibility(View.GONE);
////             holder.imageView.setVisibility(View.VISIBLE);
////    }
//    }

    @Override
    public int getItemCount() {
        return imageOfArray.size();
    }

    public class GalleryGridViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public GalleryGridViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.galleryImage);
            textView = itemView.findViewById(R.id.folderName);


        }
    }

    private class GalleryLinearViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView1;
        TextView textView1;

        public GalleryLinearViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.LinearImage);
            textView1 = itemView.findViewById(R.id.linearFolderName);


        }
    }
}
