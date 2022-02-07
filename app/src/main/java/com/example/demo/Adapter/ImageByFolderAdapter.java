package com.example.demo.Adapter;

import static androidx.core.view.ViewCompat.setTransitionName;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.demo.Listners.FolderListener;
import com.example.demo.Listners.PhotosClickListener;
import com.example.demo.Model.ImageByFolderModel;
import com.example.demo.R;

import java.util.ArrayList;

public class ImageByFolderAdapter extends RecyclerView.Adapter<ImageByFolderAdapter.FolderViewHolder> {
        ArrayList<ImageByFolderModel> imageList ;
        Context context ;
        PhotosClickListener photosClickListener;

    public ImageByFolderAdapter(ArrayList<ImageByFolderModel> imageList, Context context, PhotosClickListener photosClickListener) {
        this.imageList = imageList;
        this.context = context;
        this.photosClickListener = photosClickListener;
    }

    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.folder_layout,parent,false);

        return new FolderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder holder, int position) {
        ImageByFolderModel imageByFolderModel = imageList.get(position);
        Glide.with(context).load(imageByFolderModel.getPicturePath()).apply(new RequestOptions().centerCrop()).placeholder(R.drawable.giphy).into(holder.imageView);
        Log.d("Ajay", "onBindViewHolder: "+imageByFolderModel.getPicturePath());
//        setTransitionName(holder.imageView, String.valueOf(position) + "_image");
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            photosClickListener.PhotoClickListener(holder,position,imageList);
            }
        });



    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class FolderViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public FolderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.photos);

        }
    }
}
