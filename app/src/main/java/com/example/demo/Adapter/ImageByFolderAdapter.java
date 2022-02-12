package com.example.demo.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.demo.Listners.PhotosClickListener;
import com.example.demo.Model.ImageByFolderModel;
import com.example.demo.R;

import java.util.ArrayList;

public class ImageByFolderAdapter extends RecyclerView.Adapter<ImageByFolderAdapter.FolderViewHolder> {
    ArrayList<ImageByFolderModel> imageList;
    Context context;
    PhotosClickListener photosClickListener;
    public  Boolean isGrid;

    public ImageByFolderAdapter(ArrayList<ImageByFolderModel> imageList, Context context, PhotosClickListener photosClickListener) {
        this.imageList = imageList;
        this.context = context;
        this.photosClickListener = photosClickListener;
        this.isGrid = true;
    }


    public void setFlag(boolean isGrid){
        this.isGrid = isGrid;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.folder_layout, parent, false);

        return new FolderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d("isGrid", "onBindViewHolder: " + isGrid);
        ImageByFolderModel imageByFolderModel = imageList.get(position);
        if (isGrid) {
            holder.cardViewGrid.setVisibility(View.VISIBLE);
            holder.cardViewLinear.setVisibility(View.GONE);
            Glide.with(context).load(imageByFolderModel.getPicturePath()).apply(new RequestOptions().centerCrop()).placeholder(R.drawable.giphy).into(holder.imageView2);
            holder.imageName.setText(imageList.get(position).getPictureName());

        } else {
            holder.cardViewLinear.setVisibility(View.VISIBLE);
            holder.cardViewGrid.setVisibility(View.GONE);
            Glide.with(context).load(imageByFolderModel.getPicturePath()).apply(new RequestOptions().centerCrop()).placeholder(R.drawable.giphy).into(holder.imageView1);

        }
        Log.d("Ajay", "onBindViewHolder: " + imageByFolderModel.getPicturePath());
//        setTransitionName(holder.imageView, String.valueOf(position) + "_image");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photosClickListener.PhotoClickListener(holder, position, imageList);
            }
        });


    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class FolderViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewGrid, cardViewLinear;
        ImageView imageView1;
        ImageView imageView2;
        TextView imageName;

        public FolderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.imageOfLinear);
            imageView2 = itemView.findViewById(R.id.photos);
            imageName = itemView.findViewById(R.id.imageName);
            cardViewGrid = itemView.findViewById(R.id.cardViewGrid);
            cardViewLinear = itemView.findViewById(R.id.cardViewLinear);

        }
    }
}
